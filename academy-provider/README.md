# Preclearance Task #3 - Unit Testing and Web Automation Testing

## Tech Stack
 - Java 1.8.x 
https://www.oracle.com/java/technologies/downloads/#java8
 - Eclipse IDE 
Download Eclipse ** Enterprise Java and Web Developer Tools **
https://www.eclipse.org/downloads/packages/installer
 - Maven 3.x 
https://maven.apache.org/download.cgi?Preferred=ftp://ftp.osuosl.org/pub/apache/
 - Spring Web MVC 4.x or 5.x (Not Boot) 
 - Spring Boot 2.X 
 -	TestNG 6.x
 -	Bean Mocking framework, such as Mockito
 -	Selenium 3.x
 
### Steps
- Add the following TestNG dependency to the pom.xml
	
	<!-- Testing Framework TestNG -->
	<dependency>
	    <groupId>org.testng</groupId>
	    <artifactId>testng</artifactId>
	    <version>7.5</version>
	    <scope>test</scope>
	</dependency>
- Create a test class that extends AbstractTestNGSpringContextTests.

To make TestNG test class work with our Spring Boot we should extend AbstractTestNGSpringContextTests. <br />
The AbstractTestNGSpringContextTests is an abstract base class having the ApplicationContext supported in the testNG explicitly.

- [Install TestNG plugin for Spring Boot application.](https://testng.org/doc/download.html)
- Open the Test class and run as TestNG Test 

### Notes
 -	Webapp tests = Selenium
 -	REST service tests = TestNG
 -	Place all test files in a package on the backed app from Task 3. Task 5 will bypass the frontend app.
 
#### What Is TestNG?
TestNG is a testing framework that is inspired by JUnit and NUnit. It’s for the Java programming language. TestNG covers a wider range of test categories like unit, functional, end-to-end, integration, etc. NG in TestNG stands for Next Generation.

It’s an open-source framework that comes under the Apache License. It provides a rich set of annotations that accelerate the test script development.
 
### References
[Install TestNG](https://testng.org/doc/download.html)
https://reflectoring.io/spring-boot-web-controller-test/ <br />
https://www.codeusingjava.com/boot/testng <br />
https://www.javainuse.com/spring/springboot_testng <br />
https://testng.org/doc/documentation-main.html <br />
https://www.softwaretestinghelp.com/spring-resttemplate-api-testing/ <br />
https://howtodoinjava.com/spring-boot2/testing/springboottest-annotation/ <br />
[Mockito Tutorial](https://www.tutorialspoint.com/mockito/index.htm)
https://frontbackend.com/java/how-to-stub-a-method-to-return-different-objects-on-subsequent-invocations-using-mockito
https://stacktraceguru.com/unittest/mock-void-method
