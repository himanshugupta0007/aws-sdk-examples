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

	### Important Points to remember:-
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

# Account Alias Details

## Create account alias

	- Request -> aws iam create-account-alias --alais-name testusernew
	- Response -> aws iam create-account-alias

## Delete Account Alias

	- Request -> aws iam delete-account-alias --alais-name testusernew
	- Response -> aws iam delete-account-alias

## List Alias for account

	- Request -> aws iam list-account-alias --alais-name testusernew
	- Response -> 
		
		{
    		"AccountAliases": [
        		"trainingusernew"
    		]
		}

## Get Account Summary Information

	- Request -> aws iam get-account-summary
	- Response -> 
		
		{
    		"SummaryMap": {
        			""
        		}
        }
	
# User Commands

## List the users for account
	
		- Request -> aws iam list-users
		- Response -> 
			
		{
    		"Users": [
        		{
		            "Path": "/",
		            "UserName": "testUser",
		            "UserId": "*******************",
		            "Arn": "*********************",
		            "CreateDate": "2020-02-11T19:57:58+00:00",
		            "PasswordLastUsed": "**********************"
        		}
    		]
		}

## To get the user information
		
		- Request -> aws iam get-user --user-name awstraininguser
		- Response ->
			
			{
		    	"User": {
		        	"Path": "/",
			        "UserName": "testUser",
			        "UserId": "*********************O",
			        "Arn": "arn:aws:iam::327131238009:user/testUser",
			        "CreateDate": "2020-02-11T19:57:58+00:00",
			        "PasswordLastUsed": "2020-06-27T19:02:47+00:00",
			        "Tags": [
			            		{
			                		"Key": "user-type",
			                		"Value": "training-user"
			            		}
			    		    ]
		    			}
			}
	

## Create a new user

		- Request -> aws iam get-user --user-name awstraininguser

## Update a user
		- Request -> aws iam update-user --user-name awstestuser --new-user-name awstestuser1

## Delete a user
		- Request -> aws iam delete-user --user-name awstestuser1
	