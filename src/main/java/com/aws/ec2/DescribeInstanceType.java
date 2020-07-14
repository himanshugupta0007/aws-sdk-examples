/**
 *
 */
package com.aws.ec2;

import com.aws.ec2.utility.AWSEC2Utility;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstanceTypesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeInstanceTypesResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.InstanceTypeInfo;

/**
 * This class is used to list all EC2 instance Type and also describes the
 * filters as well
 *
 * @author himanshu.gupta
 *
 */
public class DescribeInstanceType {

	/**
	 * Describes the Instance Type
	 *
	 * @param ec2Cclient
	 */
	private static void describeInstanceType(Ec2Client ec2Cclient) {

		String nextToken = null;
		DescribeInstanceTypesRequest describeInstanceTypeRequest = null;

		try {

			do {
				// build the request
				describeInstanceTypeRequest = DescribeInstanceTypesRequest.builder().maxResults(6).nextToken(nextToken)
						.build();
				// receive the response
				final DescribeInstanceTypesResponse describeInstanceTypeResponse = ec2Cclient
						.describeInstanceTypes(describeInstanceTypeRequest);
				if (describeInstanceTypeResponse.instanceTypes().isEmpty()) {
					System.out.println("No Instance TypesReceived");
				} else {
					for (final InstanceTypeInfo instanceType : describeInstanceTypeResponse.instanceTypes()) {
						System.out.println("\n****************************************");
						System.out.println("\nInstance Type: " + instanceType.instanceTypeAsString());
						System.out.println("\nIs Auto Recovery Supported: " + instanceType.autoRecoverySupported());
						System.out.println("\nIs Dedicated Host Supported: " + instanceType.dedicatedHostsSupported());
						System.out.println("\nIs Free Trial Eligible: " + instanceType.freeTierEligible());
						System.out.println("\n****************************************");
					}
				}
				nextToken = describeInstanceTypeResponse.nextToken();
			} while (nextToken != null);

		} catch (final Ec2Exception e) {
			System.err.println(e.awsErrorDetails().errorMessage());
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		describeInstanceType(AWSEC2Utility.getEC2Cclient(Region.US_EAST_1));

	}

}
