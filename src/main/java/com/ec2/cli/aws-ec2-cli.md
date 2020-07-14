# EC2 CLI Command - To manage EC2 Instances

## List EC2 Instances - Commands

### List EC2 Instances
Describes the specified instances or all instances.
If you specify instance IDs, the output includes information for only the specified instances. 
If you specify filters, the output includes information for only those instances that meet the filter criteria. 
If you do not specify instance IDs or filters, the output includes information for all instances, which can affect performance. 
**If you specify an instance ID that is not valid, an error is returned**. 
If you specify an instance that you do not own, it is not included in the output.
Recently terminated instances might appear in the returned results. This interval is usually less than one hour

	- aws ec2 describe-instances
	- With EC2 Instance ID: 
		- aws ec2 describe-instances --instance-id
	- With Filter Option
	    - Through Instance Type: aws ec2 describe-instances --filters Name=instance-type,Values=t2.micro
	    - Through Tag Key Value: aws ec2 describe-instances --filters "Name=tag-key,Values=Name"
	    - Through Tag Value : aws ec2 describe-instances --filters "Name=tag-value,Values=TestServer"
	 - To display specfic information - Use Query along with Describe option
	    - aws ec2 describe-instances --query "Reservations[*].Instances[*].{Instance:InstanceId,Subnet:SubnetId}" --output yaml
	    - "Reservations[*].Instances[*].{Instance:InstanceId,Subnet:SubnetId}" this is representation of specific data you want to print
	    - You can combine this with filters as well. Described below:
	        - aws ec2 describe-instances --filters Name=tag-key,Values=Name --query "Reservations[*].Instances[*].{Instance:InstanceId,AZ:Placement.AvailabilityZone,Name:Tags[?Key=='Name']|[0].Value}" --output table

### Check Instance Status
    - aws ec2 describe-instance-status
    - It has same set of filters used in describe-instance

### List instance Type by AWS
    - aws ec2 describe-instance-types
### List Instance offered by Region,AZ
    - aws ec2 describe-instance-type-offerings
    - with provided regions
        - aws ec2 describe-instance-type-offerings --region us-east-2
    - with availibilty zone
        - aws ec2 describe-instance-type-offerings --region us-east-2 --location-type availability-zone --filters Name=location,Values=us-east-2a
### To get the Attribute for Instance
    - aws ec2 describe-instance-attribute --instance-id i-025bf3f6de0cc6e8a --attribute instanceInitiatedShutdownBehavior
    - below are the attributes
        - instanceType
        - kernel
        - ramdisk
        - userData
        - disableApiTermination
        - instanceInitiatedShutdownBehavior
        - rootDeviceName
        - blockDeviceMapping
        - productCodes
        - sourceDestCheck
        - groupSet
        - ebsOptimized
        - sriovNetSupport
        - enaSupport
	  
## Key Pairs Commands

## Describe\List Key-Pair

    - aws ec2 describe-key-pairs
    - aws ec2 describe-key-pairs --key-names keyname
    - aws ec2 describe-key-pairs --key-id keyID

## Create Key-Pair

    - aws ec2 create-key-pair --key-name testKeyPair

## Delete Key-Pair
    
    - aws ec2 delete-key-pair --key-name testKeyPair 




## Appendix
Reference: Filter have other options. Please check https://awscli.amazonaws.com/v2/documentation/api/latest/reference/ec2/describe-instances.html for more options
	
		
	