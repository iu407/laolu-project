package com.prs.jy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openid4java.OpenIDException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.AuthSuccess;
import org.openid4java.message.ParameterList;
import org.openid4java.message.ax.AxMessage;
import org.openid4java.message.ax.FetchRequest;
import org.openid4java.message.ax.FetchResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/openid")
public class SampleConsumer {

	public ConsumerManager manager;

	public SampleConsumer() throws ConsumerException {
		// instantiate a ConsumerManager object
		manager = new ConsumerManager();
	}

	@RequestMapping(value="/login",method=RequestMethod.POST )
	public String authRequest(@RequestParam("openid_identifier") String userSuppliedString,
	                              HttpServletRequest httpReq,
	                              HttpServletResponse httpResp)
	            throws IOException
	    {
	        try
	        {
	            // configure the return_to URL where your application will receive
	            // the authentication responses from the OpenID provider
	            String returnToUrl = "http://localhost:8080/jyzz";

	            // --- Forward proxy setup (only if needed) ---
	            // ProxyProperties proxyProps = new ProxyProperties();
	            // proxyProps.setProxyName("proxy.example.com");
	            // proxyProps.setProxyPort(8080);
	            // HttpClientFactory.setProxyProperties(proxyProps);

	            // perform discovery on the user-supplied identifier
	            List discoveries = manager.discover(userSuppliedString);

	            // attempt to associate with the OpenID provider
	            // and retrieve one service endpoint for authentication
	            DiscoveryInformation discovered = manager.associate(discoveries);

	            // store the discovery information in the user&#x27;s session
	            httpReq.getSession().setAttribute("openid-disc", discovered);

	            // obtain a AuthRequest message to be sent to the OpenID provider
	            AuthRequest authReq = manager.authenticate(discovered, returnToUrl);

	            // Attribute Exchange example: fetching the &#x27;email&#x27; attribute
	            FetchRequest fetch = FetchRequest.createFetchRequest();
	            fetch.addAttribute("email",
	                    // attribute alias
	                    "http://schema.openid.net/contact/email",   // type URI
	                    true);                                      // required

	            // attach the extension to the authentication request
	            authReq.addExtension(fetch);


	            if (! discovered.isVersion2() )
	            {
	                // Option 1: GET HTTP-redirect to the OpenID Provider endpoint
	                // The only method supported in OpenID 1.x
	                // redirect-URL usually limited ~2048 bytes
	                httpResp.sendRedirect(authReq.getDestinationUrl(true));
	                return null;
	            }
	            else
	            {
	                // Option 2: HTML FORM Redirection (Allows payloads &gt;2048 bytes)
	            	
	                RequestDispatcher dispatcher =  httpReq.getSession().getServletContext().getRequestDispatcher("formredirection.jsp");
	                httpReq.setAttribute("parameterMap", authReq.getParameterMap());
	                httpReq.setAttribute("destinationUrl", authReq.getDestinationUrl(false));
	                dispatcher.forward(httpReq, httpResp);
	            }
	        }
	        catch (OpenIDException e)
	        {
	        } catch (ServletException e) {
				e.printStackTrace();
			}

	        return null;
	    }

	// --- processing the authentication response ---
	public Identifier verifyResponse(HttpServletRequest httpReq)
	    {
	        try
	        {
	            // extract the parameters from the authentication response
	            // (which comes in as a HTTP request from the OpenID provider)
	            ParameterList response =
	                    new ParameterList(httpReq.getParameterMap());

	            // retrieve the previously stored discovery information
	            DiscoveryInformation discovered = (DiscoveryInformation)
	                    httpReq.getSession().getAttribute("openid-disc");

	            // extract the receiving URL from the HTTP request
	            StringBuffer receivingURL = httpReq.getRequestURL();
	            String queryString = httpReq.getQueryString();
	            if (queryString != null && queryString.length() > 0)
	                receivingURL.append("?").append(httpReq.getQueryString());

	            // verify the response; ConsumerManager needs to be the same
	            // (static) instance used to place the authentication request
	            VerificationResult verification = manager.verify(
	                    receivingURL.toString(),
	                    response, discovered);

	            // examine the verification result and extract the verified identifier
	            Identifier verified = verification.getVerifiedId();
	            if (verified != null)
	            {
	                AuthSuccess authSuccess = (AuthSuccess) verification.getAuthResponse();

	                if (authSuccess.hasExtension(AxMessage.OPENID_NS_AX))
	                {
	                    FetchResponse fetchResp = (FetchResponse) authSuccess
	                            .getExtension(AxMessage.OPENID_NS_AX);

	                    List emails = fetchResp.getAttributeValues("email");
	                    String email = (String) emails.get(0);
	                }

	                return verified;  // success
	            }
	        }
	        catch (OpenIDException e)
	        {
	            // present error to the user
	        }

	        return null;
	    }
}
