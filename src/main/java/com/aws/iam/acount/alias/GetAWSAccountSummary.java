/**
 *
 */
package com.aws.iam.acount.alias;

import java.util.Map;

import com.aws.iam.utility.AWSIAMUtilty;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.GetAccountSummaryRequest;
import software.amazon.awssdk.services.iam.model.GetAccountSummaryResponse;
import software.amazon.awssdk.services.iam.model.IamException;

/**
 * @author himanshu.gupta
 * 
 *         This class provides the summary of account configured on your system
 *
 */
public class GetAWSAccountSummary {

	public static void main(String[] args) {

		final Region globalRegion = AWSIAMUtilty.getGlobalRegion();
		final IamClient iam = AWSIAMUtilty.getIamClientForRegion(globalRegion);
		try {
			GetAccountSummaryResponse accountSummaryResponse;
			final GetAccountSummaryRequest request = GetAccountSummaryRequest.builder().build();
			accountSummaryResponse = iam.getAccountSummary(request);
			final Map summary = accountSummaryResponse.summaryMap();
			System.out.println(summary);
		} catch (final IamException e) {
			e.printStackTrace();
		}

	}
}
