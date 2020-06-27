/**
 *
 */
package com.aws.iam.users;

import com.aws.iam.utility.AWSIAMUtilty;

import software.amazon.awssdk.http.HttpStatusCode;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateUserRequest;
import software.amazon.awssdk.services.iam.model.CreateUserResponse;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.Tag;

/**
 * @author himanshu.gupta
 *
 */
public class CreateAWSUser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final Region globalRegion = AWSIAMUtilty.getGlobalRegion();
		final IamClient iam = AWSIAMUtilty.getIamClientForRegion(globalRegion);
		try {

			final Tag tags = Tag.builder().key("user-type").value("test-user").build();
			final CreateUserRequest createUserRequest = CreateUserRequest.builder().userName("testawsuser").tags(tags)
					.build();

			final CreateUserResponse createUserResponse = iam.createUser(createUserRequest);

			if (createUserResponse.sdkHttpResponse().statusCode() == HttpStatusCode.OK) {
				System.out.println("User has been created");
			}
		} catch (final IamException iamException) {
			iamException.printStackTrace();
		}

	}

}
