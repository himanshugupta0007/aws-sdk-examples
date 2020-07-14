/**
 *
 */
package com.aws.ec2.utility;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;

/**
 * Utility Class for EC2 helper methods
 *
 * @author himanshu.gupta
 *
 */
public class AWSEC2Utility {

	public static Ec2Client getEC2Cclient(Region region) {
		return Ec2Client.builder().region(region).build();
	}

}
