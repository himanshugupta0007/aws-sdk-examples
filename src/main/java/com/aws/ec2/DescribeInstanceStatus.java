/**
 *
 */
package com.aws.ec2;

import com.aws.ec2.utility.AWSEC2Utility;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstanceStatusRequest;
import software.amazon.awssdk.services.ec2.model.DescribeInstanceStatusResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.Filter;
import software.amazon.awssdk.services.ec2.model.InstanceStatus;

/**
 * This class is used to list all EC2 instance Status and also describes the
 * filters as well
 *
 * @author himanshu.gupta
 *
 */
public class DescribeInstanceStatus {

	/**
	 * Describes the Instance Status present under configured account
	 *
	 * @param ec2Cclient
	 * @param instanceFilter
	 */
	private static void describeInstanceStatus(Ec2Client ec2Cclient, Filter instanceFilter) {

		String nextToken = null;
		DescribeInstanceStatusRequest describeInstanceStatusRequest = null;

		try {

			do {
				if (instanceFilter != null && instanceFilter.hasValues()) {
					// build the request
					describeInstanceStatusRequest = DescribeInstanceStatusRequest.builder().maxResults(6)
							.nextToken(nextToken).filters(instanceFilter).build();
				} else {
					// build the request
					describeInstanceStatusRequest = DescribeInstanceStatusRequest.builder().maxResults(6)
							.nextToken(nextToken).build();
				}
				// receive the response
				final DescribeInstanceStatusResponse describeInstanceStatusResponse = ec2Cclient
						.describeInstanceStatus(describeInstanceStatusRequest);
				if (describeInstanceStatusResponse.instanceStatuses().isEmpty()) {
					System.out.println("No Instance Configured for the account");
				} else {
					for (final InstanceStatus instanceStatus : describeInstanceStatusResponse.instanceStatuses()) {
						System.out.println("\n****************************************");
						System.out.println("\nInstance Availabilty Zone: " + instanceStatus.availabilityZone());
						System.out.println("\nInstance ID: " + instanceStatus.instanceId());
						System.out.println("\nInstance ARN: " + instanceStatus.outpostArn());
						System.out.println("\nInstance State: " + instanceStatus.instanceState());
						System.out.println("\nInstance Status: " + instanceStatus.instanceStatus());
						System.out.println("\nInstance System Status: " + instanceStatus.systemStatus());
						System.out.println("\n****************************************");
					}
				}
				nextToken = describeInstanceStatusResponse.nextToken();
			} while (nextToken != null);

		} catch (final Ec2Exception e) {
			System.err.println(e.awsErrorDetails().errorMessage());
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final Filter instanceFilter = Filter.builder().name("instance-status.status").values("ok")
				.name("availability-zone").values("us-east-1d").build();
		describeInstanceStatus(AWSEC2Utility.getEC2Cclient(Region.US_EAST_1), instanceFilter);

	}

}
