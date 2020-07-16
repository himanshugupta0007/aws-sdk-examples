/**
 *
 */
package com.aws.s3;

import java.util.ArrayList;
import java.util.List;

import com.aws.s3.utility.AWSS3Utility;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.Filter;
import software.amazon.awssdk.services.ec2.model.Instance;
import software.amazon.awssdk.services.ec2.model.Reservation;

/**
 * This class is used to create a s3 bucket
 *
 * @author himanshu.gupta
 *
 */
public class CreateS3Bucket {

	

}
