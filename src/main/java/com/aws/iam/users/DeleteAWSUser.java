/**
 *
 */
package com.aws.iam.users;

import com.aws.utility.AWSUtilty;

import software.amazon.awssdk.http.HttpStatusCode;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateUserRequest;
import software.amazon.awssdk.services.iam.model.CreateUserResponse;
import software.amazon.awssdk.services.iam.model.DeleteUserRequest;
import software.amazon.awssdk.services.iam.model.DeleteUserResponse;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.Tag;

/**
 * @author himanshu.gupta
 *
 */
public class DeleteAWSUser {
	
	public static String AWS_USER = "testawsusernew";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final Region globalRegion = AWSUtilty.getGlobalRegion();
		final IamClient iam = AWSUtilty.getIamClientForRegion(globalRegion);
		try {
			
			final DeleteUserRequest deleteUserRequest = DeleteUserRequest.builder().userName(AWS_USER).build();

			final DeleteUserResponse deleteUserResponse = iam.deleteUser(deleteUserRequest);

			if (deleteUserResponse.sdkHttpResponse().statusCode() == HttpStatusCode.OK) {
				System.out.println("User has been Deleted");
			}
		} catch (final IamException iamException) {
			iamException.printStackTrace();
		}

	}

}
