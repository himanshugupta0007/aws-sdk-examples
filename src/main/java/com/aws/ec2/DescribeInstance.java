/**
 *
 */
package com.aws.ec2;

import java.util.ArrayList;
import java.util.List;

import com.aws.ec2.utility.AWSEC2Utility;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.Filter;
import software.amazon.awssdk.services.ec2.model.Instance;
import software.amazon.awssdk.services.ec2.model.Reservation;

/**
 * This class is used to list all EC2 instance and also describes the filters as
 * well
 *
 * @author himanshu.gupta
 *
 */
public class DescribeInstance {

	/**
	 * Describes the Instance present under configured account
	 *
	 * @param ec2Cclient
	 * @param instanceFilter
	 */
	private static void describeInstance(Ec2Client ec2Cclient, Filter instanceFilter) {

		String nextToken = null;
		DescribeInstancesRequest describeRequest = null;

		try {

			do {
				if (instanceFilter != null && instanceFilter.hasValues()) {
					// build the request
					describeRequest = DescribeInstancesRequest.builder().maxResults(6).nextToken(nextToken)
							.filters(instanceFilter).build();
				} else {
					// build the request
					describeRequest = DescribeInstancesRequest.builder().maxResults(6).nextToken(nextToken).build();
				}
				// receive the response
				final DescribeInstancesResponse describeResponse = ec2Cclient.describeInstances(describeRequest);
				if (describeResponse.reservations().isEmpty()) {
					System.out.println("No Instance Configured for the account");
				} else {
					for (final Reservation reservation : describeResponse.reservations()) {
						for (final Instance instance : reservation.instances()) {
							System.out.println("\n****************************************");
							System.out.println("\nInstance ID: " + instance.instanceId());
							System.out.println("\nInstance Image ID: " + instance.imageId());
							System.out.println("\nInstance Type: " + instance.instanceType());
							System.out.println("\nInstance State: " + instance.state().name());
							System.out.println("\nInstance Monitoring State: " + instance.monitoring().state());
							System.out.println("\nInstance Security Group: " + instance.securityGroups().get(0));
							System.out.println("\nInstance DNS NAme: " + instance.publicDnsName());
							System.out.println("\n****************************************");
						}
					}
				}
				nextToken = describeResponse.nextToken();
			} while (nextToken != null);

		} catch (final Ec2Exception e) {
			System.err.println(e.awsErrorDetails().errorMessage());
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final List<String> tag_key_value = new ArrayList<String>();
		tag_key_value.add("Name");
		tag_key_value.add("Env");
		final List<String> tag_value = new ArrayList<String>();
		tag_value.add("Testserver1");
		tag_value.add("Prod");

		final Filter instanceFilter = Filter.builder().name("instance-type").values("t2.micro").name("tag-key")
				.values(tag_key_value).name("tag-value").values(tag_value).build();
		describeInstance(AWSEC2Utility.getEC2Cclient(Region.US_EAST_1), instanceFilter);

	}

}
