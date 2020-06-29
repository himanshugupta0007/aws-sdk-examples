/**
 * 
 */
package com.aws.utility;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;

/**
 * @author himan
 *
 */
public class AWSUtilty {

	public static Region getGlobalRegion() {
		return Region.AWS_GLOBAL;
	}

	public static IamClient getIamClientForRegion(Region region) {
		return IamClient.builder().region(region).build();
	}

}
