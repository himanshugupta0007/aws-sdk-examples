/**
 *
 */
package com.aws.ec2.keypair;

import com.aws.ec2.utility.AWSEC2Utility;

import software.amazon.awssdk.http.HttpStatusCode;
import software.amazon.awssdk.http.SdkHttpResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DeleteKeyPairRequest;
import software.amazon.awssdk.services.ec2.model.DeleteKeyPairResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;

/**
 * This class is used to delete key pair provided key name
 *
 * @author himanshu.gupta
 *
 */
public class DeleteKeyPair {

	/**
	 * Describes the key pair
	 *
	 * @param ec2Cclient
	 * @param instanceFilter
	 */
	private static void deleteKeyPair(Ec2Client ec2Cclient) {

		DeleteKeyPairRequest deleteKeyPairRequest = null;

		try {
			// build the request
			deleteKeyPairRequest = DeleteKeyPairRequest.builder().keyName("testKeyPair").build();
			// receive the response
			final DeleteKeyPairResponse deleteKeyPairResponse = ec2Cclient.deleteKeyPair(deleteKeyPairRequest);
			final SdkHttpResponse response = deleteKeyPairResponse.sdkHttpResponse();
			if (response.statusCode() == HttpStatusCode.OK) {
				System.out.println("Key has been deleted");
			}

		} catch (final Ec2Exception e) {
			System.err.println(e.awsErrorDetails().errorMessage());
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		deleteKeyPair(AWSEC2Utility.getEC2Cclient(Region.US_EAST_1));

	}

}
