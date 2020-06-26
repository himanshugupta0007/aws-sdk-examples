#AWS CLI commands for Access Key Management.

## Create an Access key for a User
	
 - Request on CLI	-> aws iam create-access-key --user-name testUser
 - Response Received -> in the JSON Format

{
    "AccessKey": {
        "UserName": "testUser",
        "AccessKeyId": "********************",
        "Status": "Active",
        "SecretAccessKey": "********************",
        "CreateDate": "2020-06-26T20:29:51+00:00"
    }
}

Important Points to remember:-
	- You can get output in Yaml format as well using '--output yaml'
	- You can create max 2 access keys for a user as per provided quota

## List All Access Keys

 - Request on CLI ->  aws iam list-access-keys --user-name testUser

 - Response Received -> in the JSON Format

{
    "AccessKeyMetadata": [
        {
            "UserName": "testUser",
            "AccessKeyId": "********************",
            "Status": "Active",
            "CreateDate": "2020-06-26T20:29:51+00:00"
        },
        {
            "UserName": "testUser",
            "AccessKeyId": "********************",
            "Status": "Active",
            "CreateDate": "2020-06-13T21:41:22+00:00"
        }
    ]
}

## Delete Access Keys

 - Request on CLI -> aws iam delete-access-keys --access-key-id ******************** --user-name testUser

 - Response Received -> If it is successful, no response will be received for this command

## Get Access Key Last Used

 - Request on CLI -> aws iam get-access-key-last-used --access-key ********************

 - Response Received -> It a access key is new. You find 'N/A' for Service Name and Region

{
    "UserName": "testUser",
    "AccessKeyLastUsed": {
        "LastUsedDate": "2020-06-26T20:36:00+00:00",
        "ServiceName": "iam",
        "Region": "us-east-1"
    }
}

## Update Access Key - Activate or Deactivate

	### Deactivate 
		- Request -> aws iam update-access-key --access-key ******************** --status Inactive --user-name testUser
		- Response -> If successful you will get no response otherwise an error
	
	### Actvate 
		- Request -> aws iam update-access-key --access-key ******************** --status Active --user-name testUser
		- Response -> If successful you will get no response otherwise an error

	