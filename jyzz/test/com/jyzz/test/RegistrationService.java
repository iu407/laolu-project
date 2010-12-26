package com.jyzz.test;

import java.util.List;

import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;

public class RegistrationService {
	public RegistrationService() throws ConsumerException {
		super();
	}

	/**
	 * @param args
	 * @throws ConsumerException 
	 */
	public static DiscoveryInformation performDiscoveryOnUserSuppliedIdentifier(String identifier) throws ConsumerException {
		DiscoveryInformation ret = null; 
		ConsumerManager consumerManager = getConsumerManager(); 

		try {
			List discoveries =  consumerManager.discover(identifier);
			ret = consumerManager.associate(discoveries); 

		} catch (DiscoveryException e) {
			e.printStackTrace();
			String message = "Error occurred during discovery!"; 

		}
		return ret;
	}
	
	public static ConsumerManager getConsumerManager() throws ConsumerException{
		 return new ConsumerManager();
	}

}
