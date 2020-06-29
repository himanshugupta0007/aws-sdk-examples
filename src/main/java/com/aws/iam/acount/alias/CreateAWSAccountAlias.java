/**
 * 
 */
package com.aws.iam.acount.alias;

import com.aws.utility.AWSUtilty;

import software.amazon.awssdk.http.HttpStatusCode;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateAccountAliasRequest;
import software.amazon.awssdk.services.iam.model.CreateAccountAliasResponse;
import software.amazon.awssdk.services.iam.model.IamException;

/**
 * @author himanshu.gupta
 * 
 *         This is class creates the alias name for an account configured on
 *         your system. And you can override the configuration as well for
 *         different account
 *
 */
public class CreateAWSAccountAlias {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Region globalRegion = AWSUtilty.getGlobalRegion();
		IamClient iam = AWSUtilty.getIamClientForRegion(globalRegion);
		try {
			CreateAccountAliasRequest createAccountAliasRequest = CreateAccountAliasRequest.builder()
					.accountAlias("awstraininguser").build();
			CreateAccountAliasResponse createAccountAliasResponse = iam.createAccountAlias(createAccountAliasRequest);

			if (createAccountAliasResponse.sdkHttpResponse().statusCode() == HttpStatusCode.OK) {
				System.out.println("Alias has been created");
			}
		} catch (IamException iamException) {
			iamException.printStackTrace();
		}

	}

}
