/**
 *
 */
package com.aws.iam.users;

import com.aws.utility.AWSUtilty;

import software.amazon.awssdk.http.HttpStatusCode;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.Tag;
import software.amazon.awssdk.services.iam.model.UpdateUserRequest;
import software.amazon.awssdk.services.iam.model.UpdateUserResponse;

/**
 * @author himanshu.gupta
 *
 */
public class UpdateAWSUser {

	public static String AWS_USER = "testawsuser";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final Region globalRegion = AWSUtilty.getGlobalRegion();
		final IamClient iam = AWSUtilty.getIamClientForRegion(globalRegion);
		try {

			Tag.builder().key("user-type").value("test-user").build();
			final UpdateUserRequest updateUserRequest = UpdateUserRequest.builder().userName(AWS_USER)
					.newUserName("testawsusernew").build();
			final UpdateUserResponse updateUserResponse = iam.updateUser(updateUserRequest);

			if (updateUserResponse.sdkHttpResponse().statusCode() == HttpStatusCode.OK) {
				System.out.println("User has been created");
			}
		} catch (final IamException iamException) {
			iamException.printStackTrace();
		}

	}

}
