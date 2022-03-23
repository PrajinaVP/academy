# Preclearance Task 4 - Unit Testing and Web Automation Testing

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

**Common Errors**
1. Webapp Test
`java.lang.NoSuchMethodError: 'com.google.common.collect.ImmutableMap com.google.common.collect.ImmutableMap.of(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)'`

Solution
 `WebDriverManager version 5.1.0`

### jacoco code coverage
- Add Maven plugin for jacoco

	<plugin>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
        <version>0.8.7</version>
        <executions>
            <!-- Prepares the property pointing to the JaCoCo runtime agent which
                is passed as VM argument when Maven the Surefire plugin is executed. -->
            <execution>
                <id>pre-unit-test</id>
                <goals>
                    <goal>prepare-agent</goal>
                </goals>
                <configuration>
                    <!-- Sets the name of the property containing the settings for JaCoCo
                        runtime agent. -->
                    <propertyName>surefireArgLine</propertyName>
                </configuration>
            </execution>
            <!-- Ensures that the code coverage report for unit tests is created
                after unit tests have been run. -->
            <execution>
                <id>post-unit-test</id>
                <phase>test</phase>
                <goals>
                    <goal>report</goal>
                </goals>
                <configuration>
                    <!-- Sets the output directory for the code coverage report. -->
                    <outputDirectory>${project.reporting.outputDirectory}/jacoco-ut</outputDirectory>
                </configuration>
            </execution>
        </executions>
    </plugin>
    
Besides the normal Maven build a site folder will be created. <br />

Inside this folder will be a jacoco-ut folder, which contains the test coverage analysis files. <br />

The index.html can be opened in a browser in order to visually see the test coverage results.  <br />

### References

https://www.javaguides.net/2021/08/angular-crud-example-with-spring-boot.html?msclkid=947610fea5c111ecb691e8edda3fc401

https://www.toolsqa.com/testng/testng-tutorial/ <br />
[Install TestNG](https://testng.org/doc/download.html) <br />
[Selenium 4 Tutorial Videos](https://www.youtube.com/playlist?list=PLhW3qG5bs-L_8bwNnMHdJ1Wq5M0sUmpSH) <br />
[Selenium Webdriver documentation](https://www.selenium.dev/documentation/webdriver/) <br />
[jacoco](https://www.vogella.com/tutorials/Jacoco/article.html) <br />
https://reflectoring.io/spring-boot-web-controller-test/ <br />
https://www.codeusingjava.com/boot/testng <br />
https://www.javainuse.com/spring/springboot_testng <br />
https://testng.org/doc/documentation-main.html <br />
https://www.softwaretestinghelp.com/spring-resttemplate-api-testing/ <br />
https://howtodoinjava.com/spring-boot2/testing/springboottest-annotation/ <br />
[Mockito Tutorial](https://www.tutorialspoint.com/mockito/index.htm)
https://frontbackend.com/java/how-to-stub-a-method-to-return-different-objects-on-subsequent-invocations-using-mockito
https://stacktraceguru.com/unittest/mock-void-method
