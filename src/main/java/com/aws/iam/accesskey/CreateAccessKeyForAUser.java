/**
 *
 */
package com.aws.iam.accesskey;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.AccessKey;
import software.amazon.awssdk.services.iam.model.CreateAccessKeyRequest;
import software.amazon.awssdk.services.iam.model.CreateAccessKeyResponse;

/**
 * @author himanshu.gupta
 * 
 *         This class creates a access key for a user
 *
 */
public class CreateAccessKeyForAUser {

	private static final String AWS_USER_NAME = "<USE_YOUR_USERNAME>";

	private static void createAccessKeyForAUser(IamClient iam, String awsUserName) {
		// build a create access key request for user
		// provide the usernae=me
		final CreateAccessKeyRequest createAccessKeyForUser = CreateAccessKeyRequest.builder().userName(AWS_USER_NAME)
				.build();

		// using IAM client to send request to create a new access key at AWS account
		// for provided user
		final CreateAccessKeyResponse createAccesskeyResponse = iam.createAccessKey(createAccessKeyForUser);
		final AccessKey newAccessKeyCreated = createAccesskeyResponse.accessKey();
		System.out.println("Access key ID: " + newAccessKeyCreated.accessKeyId());
		System.out.println("Secret Key: " + newAccessKeyCreated.secretAccessKey());
		System.out.println("User Name for which it is created: " + newAccessKeyCreated.userName());
		System.out.println("Created Date: " + newAccessKeyCreated.createDate());

	}

	public static void main(String[] args) {

		// set the region to global by defaulr
		final Region region = Region.AWS_GLOBAL;

		// Initialize the IAM client for the initialized region
		final IamClient iam = IamClient.builder().region(region).build();

		// create access key ID for provided users
		createAccessKeyForAUser(iam, AWS_USER_NAME);
	}

}
