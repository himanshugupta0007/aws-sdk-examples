/**
 * 
 */
package com.aws.iam.acount.alias;

import com.aws.utility.AWSUtilty;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.ListAccountAliasesRequest;
import software.amazon.awssdk.services.iam.model.ListAccountAliasesResponse;

/**
 * @author himanshu.gupta
 * 
 *         This class is used to List the Alias name used for Account
 */
public class ListAWSAccountAliasName {

	public static void main(String[] args) {

		boolean done = false;
		String newMarker = null;
		Region globalRegion = AWSUtilty.getGlobalRegion();
		IamClient iam = AWSUtilty.getIamClientForRegion(globalRegion);
		try {
			ListAccountAliasesResponse listAccountAliasResponse;
			while (!done) {
				if (newMarker == null) {

					final ListAccountAliasesRequest request = ListAccountAliasesRequest.builder().maxItems(10).build();
					listAccountAliasResponse = iam.listAccountAliases(request);
				} else {

					// using marker in the request help you to provide another set of results if
					// number item retrieves is more than max number of results set
					final ListAccountAliasesRequest request = ListAccountAliasesRequest.builder().marker(newMarker)
							.build();
					listAccountAliasResponse = iam.listAccountAliases(request);
				}
				for (String accountAlias : listAccountAliasResponse.accountAliases()) {
					System.out.println("\nAccount Alias: " + accountAlias);
				}
				if (!listAccountAliasResponse.isTruncated()) {
					done = true;
				} else {
					newMarker = listAccountAliasResponse.marker();
				}
			}
		} catch (IamException e) {
			e.printStackTrace();
		}
	}

}
