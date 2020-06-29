package com.aws.iam.users;

import com.aws.utility.AWSUtilty;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.ListUsersRequest;
import software.amazon.awssdk.services.iam.model.ListUsersResponse;
import software.amazon.awssdk.services.iam.model.User;

/**
 *
 * @author himanshu.gupta
 *
 */
public class ListAWSUsers {

	public static ListUsersResponse listUsers() {

		boolean done = false;
		String newMarker = null;
		final Region globalRegion = AWSUtilty.getGlobalRegion();
		final IamClient iam = AWSUtilty.getIamClientForRegion(globalRegion);
		ListUsersResponse listUserResponse = null;
		try {
			while (!done) {
				if (newMarker == null) {

					final ListUsersRequest listUsersRequest = ListUsersRequest.builder().maxItems(10).build();
					listUserResponse = iam.listUsers(listUsersRequest);
				} else {

					// using marker in the request help you to provide another set of results if
					// number item retrieves is more than max number of results set
					final ListUsersRequest listUsersRequest = ListUsersRequest.builder().marker(newMarker).build();
					listUserResponse = iam.listUsers(listUsersRequest);
				}

				if (!listUserResponse.isTruncated()) {
					done = true;
				} else {
					newMarker = listUserResponse.marker();
				}
			}
		} catch (final IamException e) {
			e.printStackTrace();
		}

		return listUserResponse;

	}

	public static void main(String[] args) {

		final ListUsersResponse userListResponse = listUsers();
		if (userListResponse != null) {
			for (final User accountAlias : userListResponse.users()) {
				System.out.println("\nUser ID: " + accountAlias.userId());
				System.out.println("\nUser Name: " + accountAlias.userName());
				System.out.println("\nCreate Date: " + accountAlias.createDate());
				System.out.println("\nUser ARN: " + accountAlias.arn());
				System.out.println("\nTags: " + accountAlias.tags());
			}
		}

	}

}
