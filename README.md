# Prajina's Academy
A bootstrapped learning app

### Tech Stack
1. Java 1.8.x 
https://www.oracle.com/java/technologies/downloads/#java8
2. Eclipse IDE 
Download Eclipse ** Enterprise Java and Web Developer Tools **
https://www.eclipse.org/downloads/packages/installer
3. Maven 3.x 
https://maven.apache.org/download.cgi?Preferred=ftp://ftp.osuosl.org/pub/apache/
4. Spring Web MVC 4.x or 5.x (Not Boot) 
5. AngularJS 1.x.x (rec. 1.6.x) 
6. Jetty/Tomcat (rec. Jetty Maven Plugin) 

### Steps
1. Create a maven project preferably using maven-archtypes-webapp (from apache) or create the directory structure manually
2. Update pom.xml to add the following dependencies (from maven central repo)
	1. Servlet API - javax.servlet-api
	2. Spring Framework
3. Create spring configuration - spring-mvc-servlet.xml or create Spring Initialize class (see AcademyInitializer.java) using java class MvcConfiguration insted of XmlFilename-servlet.xml
4. Update web.xml (under src/main/webapp/WEB-INF)
 	- 1. DispatcherServlet  is the root Servlet for any Servlet defined in your application. It will redirect the request made to the application to the appropriate controller based on the URL.
 	- 2. spring-mvc is the name of the root servlet (defined in spring configuration created in step . Spring container will look for the configuration (xml or class) with this name.
5. Create Controllers	
6. Create beans
7. Create service
8. Run on server - Note javax.servlet was replaced with jakarta.servlet in Tomcat 10+. Chose the dependency base don your tomcat version
	
	


### Common Errors
1. Error at "servlet-name" line in web.xml
 cvc-id.3: A field of identity constraint 'web-common-servlet-name-uniqueness' matched element 'web-app', but this element does not have a simple type.
 
 Solution: Cause by old xmlns. Change
xmlns = "http://java.sun.com/xml/ns/j2ee" 
TO
xmlns = "http://Java.sun.com/xml/ns/j2ee" 
OR
xmlns = "http://JAVA.sun.com/xml/ns/j2ee" 

2. Refer https://dzone.com/articles/spring-0 for common Spring errors

For Tomcat 10.x, which is based on Servlet 5.0, JSP 3.0, EL 4.0, WS 2.0 and JASIC 2.0, you should use jakarta.* imports
Tomcat 9 is the last javax.servlet version

Dec 11, 2021 9:09:40 AM org.apache.catalina.core.StandardContext loadOnStartup
SEVERE: Servlet [prajina-academy] in web application [/PrajinaAcademy] threw load() exception
java.lang.ClassNotFoundException: javax.servlet.http.HttpServlet
	at org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClassLoaderBase.java:1444)
	at org.apache.catalina.loader.WebappClassLoaderBase.loadClass(WebappClassLoaderBase.java:1252)
	at java.base/java.lang.ClassLoader.defineClass1(Native Method)
	at java.base/java.lang.ClassLoader.d


https://javabynataraj.blogspot.com/2021/09/cvc-id3-field-of-identity-constraint.html

### References
https://howtodoinjava.com/spring5/webmvc/spring5-mvc-hibernate5-example/ - Good pom.xml
http://websystique.com/springmvc/spring-mvc-4-angularjs-example/
https://www.baeldung.com/spring-mvc-view-resolver-tutorial - ModelAndViewResolver

https://www.eclipse.org/webtools/jst/components/ws/1.5/tutorials/InstallTomcat/InstallTomcat.html
https://dzone.com/articles/spring-mvc-example-for-user-registration-and-login-1
https://www.youtube.com/watch?v=F5MQdmJfWcA&t=67s
https://howtodoinjava.com/spring5/webmvc/spring5-mvc-hibernate5-example/
https://stackoverflow.com/questions/26676782/when-use-abstractannotationconfigdispatcherservletinitializer-and-webapplication

