# aws-sdk-examples
AWS Java SDK Examples

# Package -> com.aws.iam.acccesskey

This package includes the classes to Create and Manager Access key for a user

- CreateAccessKeyForAUser
	This class creates a access key for a user using method `createAccessKeyForAUser(IamClient iam, String awsUserName)`
	which takes username and IAM client as an argument

- ManagerAWSAccessKeyForUser
	This class manages the access key performs following functions:
		- List All Access key for a User
		- Get Last Accessed Time for a access key
		- Delete Access key
		- Activate Access key
		- Deactivate Access Key

# Package -> com.aws.iam.account.alias

This package includes the code sample to manage aws account alias

# Package -> com.aws.iam.users

This package includes the code samples to manager AWS Users