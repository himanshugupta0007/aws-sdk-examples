/**
 *
 */
package com.aws.iam.accesskey;

import software.amazon.awssdk.http.HttpStatusCode;
import software.amazon.awssdk.http.SdkHttpResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.AccessKeyMetadata;
import software.amazon.awssdk.services.iam.model.DeleteAccessKeyRequest;
import software.amazon.awssdk.services.iam.model.DeleteAccessKeyResponse;
import software.amazon.awssdk.services.iam.model.GetAccessKeyLastUsedRequest;
import software.amazon.awssdk.services.iam.model.GetAccessKeyLastUsedResponse;
import software.amazon.awssdk.services.iam.model.IamException;
import software.amazon.awssdk.services.iam.model.ListAccessKeysRequest;
import software.amazon.awssdk.services.iam.model.ListAccessKeysResponse;
import software.amazon.awssdk.services.iam.model.StatusType;
import software.amazon.awssdk.services.iam.model.UpdateAccessKeyRequest;
import software.amazon.awssdk.services.iam.model.UpdateAccessKeyResponse;

/**
 * @author himanshu.gupta
 *
 *         This class is helps to understand how to manage Access key for any
 *         user
 *
 */
public class ManageAWSAccessKeysForUser {

	private static final String AWS_USER_NAME = "<USE_YOUR_USERNAME>";

	/**
	 * This method activates the access key for a user
	 *
	 * @param accessKeyId
	 * @param iam
	 */
	private static void activateAccessKey(String accessKeyId, IamClient iam) {

		// build request to deactivate the access key
		final UpdateAccessKeyRequest updateAccessKeyRequest = UpdateAccessKeyRequest.builder().accessKeyId(accessKeyId)
				.status(StatusType.ACTIVE).build();
		// send request to deactive the key
		final UpdateAccessKeyResponse statusResponse = iam.updateAccessKey(updateAccessKeyRequest);
		final SdkHttpResponse sdkResponse = statusResponse.sdkHttpResponse();
		if (sdkResponse.statusCode() == HttpStatusCode.OK) {
			System.out.println("Access Key Successfully Activate");
		}

	}

	/**
	 * This method deactivates the Access key for a provided user also, deleted the
	 * deactivated access key
	 *
	 * @param accessKeyId
	 * @param iam
	 */
	private static void deactivateAccessKey(String accessKeyId, IamClient iam) {

		// build request to deactivate the access key
		final UpdateAccessKeyRequest updateAccessKeyRequest = UpdateAccessKeyRequest.builder().accessKeyId(accessKeyId)
				.status(StatusType.INACTIVE).build();

		// send request to deactive the key
		final UpdateAccessKeyResponse statusResponse = iam.updateAccessKey(updateAccessKeyRequest);
		final SdkHttpResponse sdkResponse = statusResponse.sdkHttpResponse();
		if (sdkResponse.statusCode() == HttpStatusCode.OK) {
			System.out.println("\nAccess Key Successfully Deactivate");
			System.out.println("\nLets delete this access key");
			// delete access key
			deleteAccessKey(accessKeyId, iam);

		}
	}

	/**
	 * This method deletes the access key for a user
	 *
	 * @param accessKeyId
	 * @param iam
	 */
	private static void deleteAccessKey(String accessKeyId, IamClient iam) {

		// builds the delete access key request using access key
		final DeleteAccessKeyRequest deleteAccessKeyRequest = DeleteAccessKeyRequest.builder().accessKeyId(accessKeyId)
				.build();
		// sends the delete request using IAM Client
		final DeleteAccessKeyResponse deleteAccessKeyResponse = iam.deleteAccessKey(deleteAccessKeyRequest);
		final SdkHttpResponse deleteResponse = deleteAccessKeyResponse.sdkHttpResponse();
		if (deleteResponse.statusCode() == HttpStatusCode.OK) {
			System.out.println("Access " + accessKeyId + " Key Delete Successfully");
		}
	}

	/**
	 * Provides the last access time for access key
	 *
	 * @param accessKeyId
	 * @param iam
	 * @return
	 */
	private static String getLastAccessedTimeStamp(String accessKeyId, IamClient iam) {
		// build a request to get the timestamp for last accessed on provided access key
		final GetAccessKeyLastUsedRequest request = GetAccessKeyLastUsedRequest.builder().accessKeyId(accessKeyId)
				.build();
		// send command to retirve information for access keys
		final GetAccessKeyLastUsedResponse response = iam.getAccessKeyLastUsed(request);
		return response.accessKeyLastUsed().lastUsedDate() != null
				? response.accessKeyLastUsed().lastUsedDate().toString()
				: "No Last Time Stamp Found";
	}

	private static void listAndManageAwsUserAccessKeys(IamClient iam, String awsUserName) {

		try {
			boolean done = false;
			String newMarker = null;

			while (!done) {
				ListAccessKeysResponse response;

				if (newMarker == null) {
					// build the request using username provided.
					// setting max item results to 10
					final ListAccessKeysRequest request = ListAccessKeysRequest.builder().userName(awsUserName)
							.maxItems(10).build();

					/*
					 * The results of listAccessKeys are paged (with a default maximum of 100
					 * records per call). You can call getIsTruncated on the returned
					 * ListAccessKeysResult object to see if the query returned fewer results then
					 * are available. If so, then call setMarker on the ListAccessKeysRequest and
					 * pass it back to the next invocation of listAccessKeys.
					 */
					response = iam.listAccessKeys(request);
				} else {

					// using marker in the request help you to provide another set of results if
					// number item retrieves is more than max number of results set
					final ListAccessKeysRequest request = ListAccessKeysRequest.builder().userName(awsUserName)
							.marker(newMarker).build();
					response = iam.listAccessKeys(request);
				}

				for (final AccessKeyMetadata acessKeyData : response.accessKeyMetadata()) {
					final String lastAccessed = getLastAccessedTimeStamp(acessKeyData.accessKeyId(), iam);
					System.out.format("\nUsername: %s", awsUserName);
					System.out.format("\nRetrieved access key: %s", acessKeyData.accessKeyId());
					System.out.format("\nLast Accessed on: %s", lastAccessed);
					System.out.format("\nAccess Key Status: %s", acessKeyData.status());
					if (acessKeyData.status().equals(StatusType.ACTIVE)) {
						System.out.println("\n Lets Deactivate this access key.....");
						// calls the method to deactivate the access key
						deactivateAccessKey(acessKeyData.accessKeyId(), iam);
					} else if (acessKeyData.status().equals(StatusType.INACTIVE)) {
						System.out.println("\n Lets Activate this access key.....");
						// calls the method to deactivate the access key
						activateAccessKey(acessKeyData.accessKeyId(), iam);
					}
				}

				if (!response.isTruncated()) {
					done = true;
				} else {
					newMarker = response.marker();
				}
			}
		} catch (final IamException iamException) {
			System.err.println(iamException.awsErrorDetails().errorMessage());
		}

	}

	public static void main(String[] args) {

		// set the region to global by default
		final Region region = Region.AWS_GLOBAL;

		// Initialize the IAM client for the initialized region
		final IamClient iam = IamClient.builder().region(region).build();

		// list and manage user's access key
		listAndManageAwsUserAccessKeys(iam, AWS_USER_NAME);
	}

}
