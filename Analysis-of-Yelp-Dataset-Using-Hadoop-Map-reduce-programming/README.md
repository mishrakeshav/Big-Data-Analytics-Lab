# Hadoop-Map-reduce-programming
Includes implementation of reduce side join, memory side join

Hadoop map-reduce to derive some statistics from Yelp Dataset.
The dataset files are as follows and columns are separate using ‘::’ 
business.csv.
review.csv.
user.csv.


Dataset Description.
The dataset comprises of three csv files, namely user.csv, business.csv and review.csv.  

Business.csv file contain basic information about local businesses. 
Business.csv file contains the following columns "business_id"::"full_address"::"categories"

'business_id': (a unique identifier for the business)
'full_address': (localized address), 
'categories': [(localized category names)]  

review.csv file contains the star rating given by a user to a business. Use user_id to associate this review with others by the same user. Use business_id to associate this review with others of the same business. 

review.csv file contains the following columns "review_id"::"user_id"::"business_id"::"stars"
 'review_id': (a unique identifier for the review)
 'user_id': (the identifier of the reviewed business), 
 'business_id': (the identifier of the authoring user), 
 'stars': (star rating, integer 1-5),the rating given by the user to a business

user.csv file contains aggregate information about a single user across all of Yelp
user.csv file contains the following columns "user_id"::"name"::"url"
user_id': (unique user identifier), 
'name': (first name, last initial, like 'Matt J.'), this column has been made anonymous to preserve privacy 
'url': url of the user on yelp




=======================================================================================================================================
Note that all hadoop jars are compiled targeting a runtime version of Java 7
========================================================================================================================================


Running programs on hadoop cluster
=======================================================================================================================================
Part 1
============================================================================================================================================
 Package name: BigData
 Class name  : BusinessCategoryPA.java
 Jar name    : BusinessCategoryPA.jar
	
	hadoop jar BusinessCategoryPA.jar BigData.BusinessCategoryPA <location of business.csv> /outputBusinessCategoryPA_v5/
	
	I ran the program on UTD's hadoop cluster using following command in the directory => /NameOfTheDirectory/
        hadoop jar BusinessCategoryPA.jar BigData.BusinessCategoryPA /NameOfTheDirectory/business.csv  /NameOfTheDirectory/outputBusinessCategoryPA_v5/

(3)To display the contents of an output file

	hadoop fs -cat /outputBusinessCategoryPA_v5/*
	
	I ran the program on hadoop cluster using following command in the directory => /NameOfTheDirectory/
	hadoop fs -cat /NameOfTheDirectory/outputBusinessCategoryPA_v5/*

=============================================================================================================================================
Part 2
=============================================================================================================================================
 Package name: BigData
 Class name  : TopTenRatedBusiness.java
 Jar name    : TopTenRatedBusiness.jar

    hadoop jar TopTenRatedBusiness.jar BigData.TopTenRatedBusiness <location of review.csv> /outputTopTenRatedBusiness_v2/
    
	I ran the program on hadoop cluster using following command in the directory => /NameOfTheDirectory/
	hadoop jar TopTenRatedBusiness.jar BigData.TopTenRatedBusiness /NameOfTheDirectory/review.csv /NameOfTheDirectory/outputTopTenRatedBusiness_v2/

(3)To display the contents of an output file
	
	hadoop fs -cat /outputTopTenRatedBusiness_v2/*
	
	I ran the program on hadoop cluster using following command in the directory => /NameOfTheDirectory/
        hadoop fs -cat /NameOfTheDirectory/outputTopTenRatedBusiness_v2/*

=========================================================================================================================================================================
Part 3
========================================================================================================================================================================
 Package name: BigData
 Class name  : TopRatedBusinessData.java
 Jar name    : TopRatedBusinessData.jar

     
	hadoop jar TopRatedBusinessData.jar BigData.TopRatedBusinessData <location of review.csv> <location of business.csv> /outputTBD1_v9/ /outputTBD2_v9/

	I ran the program on hadoop cluster using following command in the directory => /NameOfTheDirectory/
        hadoop jar TopRatedBusinessData.jar BigData.TopRatedBusinessData /NameOfTheDirectory/review.csv /asp160730/business.csv /NameOfTheDirectory/outputTBD1_v9/ /NameOfTheDirectory/outputTBD2_v9/
  
  (3)To display the contents of an output file
    
	hadoop fs -cat /outputTBD2_v9/*
	
	I ran the program on UTD's hadoop cluster using following command in the directory => /NameOfTheDirectory/
        hadoop fs -cat /NameOfTheDirectory/outputTBD2_v9/*

==============================================================================================================================
Question 4
==============================================================================================================================
 Package name: BigData
 Class name  : UsersAndRating.java
 Jar name    : UsersAndRating.jar

	hadoop jar UsersAndRating.jar BigData.UsersAndRating <location of review.csv> <location of business.csv> /outputUsersAndRating_v4/

	I ran the program on hadoop cluster using following command in the directory => /NameOfTheDirectory/
	hadoop jar UsersAndRating.jar BigData.UsersAndRating /NameOfTheDirectory/review.csv /NameOfTheDirectory/business.csv /NameOfTheDirectory/outputUsersAndRating_v4/

 (3)To display the contents of an output file
    
	hadoop fs -cat /outputUsersAndRating_v4/*
	
	I ran the program on hadoop cluster using following command in the directory => /NameOfTheDirectory/
	hadoop fs -cat /NameOfTheDirectory/outputUsersAndRating_v4/*
