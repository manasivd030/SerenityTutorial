Introduction to Serenity BDD:
- Serenity BDD is an open source test automation framework formely known as Thucydides.
- Integrated with popular libraries such as Junit, Selenium, Appium, JBehave, Cucumber etc.
- Features included are Webdriver, Handling, Synchronization, screenshots, parallel testing, Data driven testing, cross-browsing testing, logging etc.
- Integrated with popular build tool such as Maven, Gradle, Ant, also can be integrated with REST-Assured to create API test automation without any hassle
- Provide reports as well in a structured way.

Setup the BDD project and configure pom.xml file
- Create simple maven project and delete the resource directory.
- https://serenity-bdd.github.io/docs/tutorials/first_test follow the steps

Note:
- Serenity BDD use failsafe plugin to execute BDD tests
- net.serenity.bdd.maven.plugin integration for reports.

Initial structure of the project:
src/test/java(package)
-> com.studentapp.testbase : initilize class
-> com.studentapp.cucumber : Runner file
-> com.studentapp.cucumber.serenity : resuable methods
-> com.studentapp.cucumber.steps : step files
-> com.studentapp.junit : Junit class files
-> com.studentapp.utils : Utilities

src/test/resources
-> feature files (cucumber feature files)
-> testdata : csv file

Here replace RestAssured.given will be replaced with SerenityRest.given(): It is a wrapper out of REST Assured
and Runner class will always have @RunWith which generates a report in target folder

Basic CRUD operation Tests
- We will fetch the student information using get requests
- We will update the student information using put requests
- We will delete the information of student using delete requests

Prioritizing test order in JUnit: In Junit, there's no such like priority how we have in TestNG, so we have to rename the testcase in such a way that it executes in an alphabetical order
@FixMethodOrder(MethodSorter.NAME_ASCENDING)

StudentCRUD.class
path("findAll{it.firstName=''}.get(0)))

There are many ways to create resuable methods:
1. Creating objects of Serenity class
2. Creating static class of resuable method and call using class name
3. Create a step annotation to add it in a report

Build data driven Test model using Serenity BDD feature
There are some rules to be followed:
1. The class having logic of creating multiple student above that class declare @RunWith(SerenityParamaterizedRunner.class)
2. Create student.csv file
3. Data to add in csv file
FIRSTNAME, LASTNAME, EMAIL, PROGRAMME, COURSES
Mark, Taylor, test444@gmail.com, Computer Science, Java
4. Use annotations:
@RunTestDatafrom("csv path") : Add the absolute path from the property and default it will go the resource folder to fetch the data
5. To map the CSV data we need to use the getter and setter method

 To increase the performance using concurrent thread but HOW???????????????????
 Note: @Concurrent can be used only with parametrized test or with data driver.
if for some of the testcase which does not have parametrized then how to use @concurrent, so it can be achieved using maven -failsafe plugin
Also if you have dependant test and if you try to execute the test parallel, then there are high chance of failure of testcases.

Maven failsafe feature:
<forkcount> 2 -> number of JVM processes that should be run on the machine
<parallel>method 
<threadcount>10
<reusefork>true/false
true: It will check how many JVM processes are used and how can we reuse them
false: It will use JVM process on the basis of fork count
Note: You can check the JVM processes usage in task manager

GHERKINS: It is a programming language used by cucumber developers to define the tests that allows product teams to describe needs for new product.

     Features           |         Descriptions
1.   Scenarios          |       Simple scenario
2.   Scenario Outline   |       Required user to provide testdata in the "Example" section
3.   Data Tables        |       Requires user to provide test data for test step
4.   Scenarios context  |       Sharing of values between test steps
5.   Cucumber Data types|       Data types handled by cucumber
6.   Cucumber regex     |       Usage of regular expression in cucumber scenarios
7.   Cucumber hooks     |       Execute the additional code in test scenario

*Tagging scenarios in Cucumber
To organize the feature with its tests in a scenario
When i want to run the particular tagged feature through maven
@smoke 
Goal: clean verify -Dcucumber.options="--tags@smoke"


