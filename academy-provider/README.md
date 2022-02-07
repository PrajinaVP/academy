### Useful MySQL
Navigate to MYSQL bin directory C:\Program Files\MySQL\MySQL Server 8.0\bin


```
mysql -uyourUserName -p
```


NOTE:
When you include spring-boot-starter-security then the login page will automatically be shown.

To remove this login page-

If you use Maven then removing this dependency and rebuild the project. Already there are some answers for this. Just remove the block (pom.xml):
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
If anyone uses Gradle then, just remove the following block from dependencies block(build.gradle) and reload build.gradle:
dependencies {
    //implementation 'org.springframework.boot:spring-boot-starter-security'
    ...
}
If you do not want to change the config file like pom.xml or build.gradle then source code level change will be the best suit for you. To do this, need to update the main class with, change @SpringBootApplication annotation with @SpringBootApplication(exclude = {SecurityAutoConfiguration.class}). This excludes parameter will remove Security configuration.
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SpringCacheApplication {

    public static void main(String[] args) {
    ...
    }
    ...
}
Moreover, if you want to keep the login page, then you can log in with generated security password. You can find it into console, looks like: Using generated security password: d408ce6f-470d-4**4-950a-81**9651f321
{
"usename" : "user",
"password": "d408ce6f-470d-4**4-950a-81**9651f32"
}
For more information, you can read Spring Security.https://docs.spring.io/spring-boot/docs/2.0.0.M4/reference/html/boot-features-security.html

### Open Api/ Swagger docs
http://localhost:8081/swagger-ui/index.html
http://localhost:8081/api-docs

### References
https://www.springboottutorial.com/spring-boot-crud-rest-service-with-jpa-hibernate <br />
https://www.baeldung.com/spring-boot-start#:~:text=Spring%20Boot%20Tutorial%20%E2%80%93%20Bootstrap%20a%20Simple%20Application,View.%20...%205%20Security.%20...%20More%20items...%20
https://knpcode.com/spring/spring-data-jpa-pagination-sorting-example/#PagingandSorting <br />
https://tenmilesquare.com/resources/software-development/spring-boot-jpa-relationship-quick-guide/#:~:text=The%20entity%20with%20the%20foreign%20key%20in%20its,by%20the%20artist%20entity%20on%20the%20ranking%20object <br />
https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion <br />
https://springdoc.org/