/**
 *
 */
package com.aws.ec2.keypair;

import com.aws.ec2.utility.AWSEC2Utility;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.CreateKeyPairRequest;
import software.amazon.awssdk.services.ec2.model.CreateKeyPairResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;

/**
 * This class is used to list all Key Pair used for EC2
 *
 * @author himanshu.gupta
 *
 */
public class CreateKeyPair {

	/**
	 * Describes the key pair
	 *
	 * @param ec2Cclient
	 * @param instanceFilter
	 */
	private static void createKeyPair(Ec2Client ec2Cclient) {

		CreateKeyPairRequest createKeyPairRequest = null;

		try {
			// build the request
			createKeyPairRequest = CreateKeyPairRequest.builder().keyName("NewKeyPair").build();
			// receive the response
			final CreateKeyPairResponse createKeyPairResponse = ec2Cclient.createKeyPair(createKeyPairRequest);
			System.out.println("\n***********************************************");
			System.out.println("\nBelow is your key pair value\n");
			System.out.println(createKeyPairResponse.keyMaterial());
			System.out.println("\nKey Pair Name: \n" + createKeyPairResponse.keyName());
			System.out.println("\nKey Pair ID: \n" + createKeyPairResponse.keyPairId());
			System.out.println("\nKey Pair Fingerprint: \n" + createKeyPairResponse.keyFingerprint());
		} catch (final Ec2Exception e) {
			System.err.println(e.awsErrorDetails().errorMessage());
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		createKeyPair(AWSEC2Utility.getEC2Cclient(Region.US_EAST_1));

	}

}
