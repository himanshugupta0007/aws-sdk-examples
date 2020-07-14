/**
 *
 */
package com.aws.ec2.keypair;

import com.aws.ec2.utility.AWSEC2Utility;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeKeyPairsRequest;
import software.amazon.awssdk.services.ec2.model.DescribeKeyPairsResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.KeyPairInfo;

/**
 * This class is used to list all Key Pair used for EC2
 *
 * @author himanshu.gupta
 *
 */
public class DescribeKeyPair {

	/**
	 * Describes the key pair
	 *
	 * @param ec2Cclient
	 * @param instanceFilter
	 */
	private static void describeKeyPair(Ec2Client ec2Cclient) {

		DescribeKeyPairsRequest describeKeyPairRequest = null;

		try {
			// build the request
			// describeKeyPairRequest =
			// DescribeKeyPairsRequest.builder().keyNames("aws-training").build();
			describeKeyPairRequest = DescribeKeyPairsRequest.builder().build();
			// receive the response
			final DescribeKeyPairsResponse describeKeyPairResponse = ec2Cclient
					.describeKeyPairs(describeKeyPairRequest);
			if (describeKeyPairResponse.hasKeyPairs()) {

				for (final KeyPairInfo keyPair : describeKeyPairResponse.keyPairs()) {
					System.out.println("\n****************************************");
					System.out.println("\nKey Pair Name: " + keyPair.keyName());
					System.out.println("\nKey Pair ID: " + keyPair.keyPairId());
					System.out.println("\nKey Pair FingerPrint: " + keyPair.keyFingerprint());
					System.out.println("\n****************************************");
				}
			} else {
				System.out.println("No Key pair Found account");
			}
		} catch (final Ec2Exception e) {
			System.err.println(e.awsErrorDetails().errorMessage());
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		describeKeyPair(AWSEC2Utility.getEC2Cclient(Region.US_EAST_1));

	}

}
