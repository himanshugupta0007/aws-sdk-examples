/**
 * 
 */
package com.aws.iam.acount.alias;

import com.aws.iam.utility.AWSIAMUtilty;

import software.amazon.awssdk.http.HttpStatusCode;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.CreateAccountAliasRequest;
import software.amazon.awssdk.services.iam.model.CreateAccountAliasResponse;
import software.amazon.awssdk.services.iam.model.DeleteAccountAliasRequest;
import software.amazon.awssdk.services.iam.model.DeleteAccountAliasResponse;
import software.amazon.awssdk.services.iam.model.IamException;

/**
 * @author himanshu.gupta
 * 
 *         This is class deletes the alias name for an account configured on
 *         your system. And you can override the configuration as well for
 *         different account
 *
 */
public class DeleteAWSAccountAlias {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Region globalRegion = AWSIAMUtilty.getGlobalRegion();
		IamClient iam = AWSIAMUtilty.getIamClientForRegion(globalRegion);
		try {
			DeleteAccountAliasRequest deleteAccountAliasRequest = DeleteAccountAliasRequest.builder()
					.accountAlias("awstraininguser").build();
			DeleteAccountAliasResponse deleteAccountAliasResponse = iam.deleteAccountAlias(deleteAccountAliasRequest);

			if (deleteAccountAliasResponse.sdkHttpResponse().statusCode() == HttpStatusCode.OK) {
				System.out.println("Alias has been delete");
			}
		} catch (IamException iamException) {
			iamException.printStackTrace();
		}

	}

}
