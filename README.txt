Polarity of Sentiments on Twitter Topics
----------------------------------------

Description
------------

Technologies/ Techniques Used:
* Java
* Javascript
* HTML
* CSS
* Maven
* NLP
* Machine Learning
* Spring Framework

This project implements the following functionalities

1. Collects tweets from Twitter Streaming API through Twitter4j library
2. Performs Sentiment Analysis using the LingPipe Logistic Regression Classifier
3. Identifies Trending Stories
4. Extracts Named Entities
5. Extracts positive and negative keywords from tweets

------------------------------------------------------------

Setup
-----

a. Backend
----------

1. Setup the TopicsAnalyzer project as a Maven project in Eclipse
2. Install dependencies from pom.xml
3. Similarly, include the ClassifierBuilder project as a new Maven Project in Eclipse and add dependencies

b. Database
-----------

1. Install MongoDB and run a mongod instance
2. Pass the 'db/scripts/dbscript.js' to the 'mongo' process as a shell command
3. This will create the necessary collections

c. Frontend
-----------

1. Install XAMPP server
2. Create a folder in the .htdocs folder and copy the contents of 'front-end' to the newly created folder



Execution
----------

Project
---------

Create a new Twitter Application and obtain the necessary keys
Set the value of these keys in the TwitterObject class located in TwitterReader.java file
Run the ProjectServer.java file in TopicsAnalyzer project as a java application
This will start a server at localhost:9998
Start the XAMPP server and migrate to the folder created to hold the front-end files
The index screen will load and you can use the query text field to send queries to the server

ClassifierBuilder
-----------------
To build the classifier, uncomment the lines as mentioned in ClassifierBuilder/PolarityTest.java and run the program

Tests
-----

To run tests, run the PolarityTest.java file as a java application in eclipse
