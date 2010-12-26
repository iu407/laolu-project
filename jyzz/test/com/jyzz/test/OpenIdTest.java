package com.jyzz.test;

import org.openid4java.consumer.ConsumerException;
import org.openid4java.discovery.DiscoveryInformation;

public class OpenIdTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String userSuppliedIdentifier = "http://manneting.openid.cn/";
		try {
			DiscoveryInformation discoveryInformation =  RegistrationService.performDiscoveryOnUserSuppliedIdentifier(userSuppliedIdentifier);
			
		} catch (ConsumerException e) {
			e.printStackTrace();
		} 
	}

}
