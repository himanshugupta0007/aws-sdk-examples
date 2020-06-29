/**
 * 
 */
package com.aws.iam.users;

import java.util.List;
import java.util.Map;

import com.aws.utility.AWSUtilty;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.GetAccountSummaryRequest;
import software.amazon.awssdk.services.iam.model.GetAccountSummaryResponse;
import software.amazon.awssdk.services.iam.model.GetUserRequest;
import software.amazon.awssdk.services.iam.model.GetUserResponse;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.Tag;
import software.amazon.awssdk.services.iam.model.User;

/**
 * @author himan
 *
 */
public class GetAWSUserWithUserName {

	public static String AWS_USER = "testawsuser";
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final Region globalRegion = AWSUtilty.getGlobalRegion();
		final IamClient iam = AWSUtilty.getIamClientForRegion(globalRegion);
		try {
			
			final GetUserRequest getUserRequest = GetUserRequest.builder().userName(AWS_USER).build();
			GetUserResponse userResponse = iam.getUser(getUserRequest);
			User userData= userResponse.user();
			System.out.println("User Name: "+ userData.userName());
			System.out.println("User ID: "+ userData.userId());
			System.out.println("User Create Date: "+ userData.createDate());
			if(userData.hasTags())
			{
				for(Tag tagData: userData.tags())
				{
					System.out.println("Tag Key: "+ tagData.key()+" Value: "+ tagData.value());
				}
				
			}
		} catch (final IamException e) {
			e.printStackTrace();
		}

	}

}
