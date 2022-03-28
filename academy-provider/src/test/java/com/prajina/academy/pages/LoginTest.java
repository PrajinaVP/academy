package com.prajina.academy.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.prajina.academy.config.WebDriverConfig;

public class LoginTest {
	
	private static final String LOGIN_URL = "http://localhost:4200";
	
	WebDriver driver;
	
	@FindBy(id="userName")
	private WebElement userName;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(id="loginBtn")
	private WebElement loginBtn;
	
	@FindBy(id="welcomeDiv")
	private WebElement welcomeDiv;
	
	@FindBy(id="welcomeMsg")
	private WebElement welcomeMsg;
	
	@FindBy(id="errorDiv")
	private WebElement errorDiv;
	
	@FindBy(id="errorMsg")
	private WebElement errorMsg;
	
	@BeforeSuite
	public void setup() {
		//Create driver
		driver = new WebDriverConfig().ChromeWebDriver();
		PageFactory.initElements(driver, this);
	}
	
	@AfterSuite
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
	@BeforeTest
	public void beforeEach() {
		// Open test page
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		driver.navigate().to(LOGIN_URL);
	}
	
	@Test
	public void givenLoginPage_whenValidUsernamePassword_thenOpenWelcomePage() {	
		doLogin("philonoist", "philonoist");
		String expectedUrl = LOGIN_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		assertTrue(welcomeDiv.isDisplayed(), "Welcome div is not displayed");
		
		String expectedMsg = "Welcome philonoist!";
		String actualMsg = welcomeMsg.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}
	
	@Test
	public void givenLoginPage_whenValidUsernameInvalidPassword_thenErrorOnLoginPage() {	
		doLogin("philonoist", "invalid");
		String expectedUrl = LOGIN_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		assertFalse(isElementPresent("welcomeDiv"), "Welcome div is displayed incorrectly.");
		assertFalse(isElementPresent("welcomeMsg"), "Welcome message is displayed incorrectly.");
		assertTrue(errorDiv.isDisplayed(), "Error div is not displayed");
		
		String expectedMsg = "Invalid Login Credentials!";
		String actualMsg = errorMsg.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}
	
	@Test
	public void givenLoginPage_whenInValidUsernameValidPassword_thenErrorOnLoginPage() {	
		doLogin("invalid", "philonoist");
		String expectedUrl = LOGIN_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		assertFalse(isElementPresent("welcomeDiv"), "Welcome div is displayed incorrectly.");
		assertFalse(isElementPresent("welcomeMsg"), "Welcome message is displayed incorrectly.");
		assertTrue(errorDiv.isDisplayed(), "Error div is not displayed");
		
		String expectedMsg = "Invalid Login Credentials!";
		String actualMsg = errorMsg.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}
	
	@Test
	public void givenLoginPage_whenInValidUsernameInValidPassword_thenErrorOnLoginPage() {	
		doLogin("invalid", "invalid");
		
		assertFalse(isElementPresent("welcomeDiv"), "Welcome div is displayed incorrectly.");
		assertFalse(isElementPresent("welcomeMsg"), "Welcome message is displayed incorrectly.");
		assertTrue(errorDiv.isDisplayed(), "Error div is not displayed");
		
		String expectedMsg = "Invalid Login Credentials!";
		String actualMsg = errorMsg.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}
	
	@Test
	public void givenLoginPage_whenNullUsernameValidPassword_thenErrorOnLoginPage() {	
		doLogin(null, "philonoist");
		String expectedUrl = LOGIN_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		assertFalse(isElementPresent("welcomeDiv"), "Welcome div is displayed incorrectly.");
		assertFalse(isElementPresent("welcomeMsg"), "Welcome message is displayed incorrectly.");
		assertTrue(errorDiv.isDisplayed(), "Error div is not displayed");
		
		String expectedMsg = "Invalid Login Credentials!";
		String actualMsg = errorMsg.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}
	
	@Test
	public void givenLoginPage_whenValidUsernameNullPassword_thenErrorOnLoginPage() {	
		doLogin("philonoist", null);
		String expectedUrl = LOGIN_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		assertFalse(isElementPresent("welcomeDiv"), "Welcome div is displayed incorrectly.");
		assertFalse(isElementPresent("welcomeMsg"), "Welcome message is displayed incorrectly.");
		assertTrue(errorDiv.isDisplayed(), "Error div is not displayed");
		
		String expectedMsg = "Invalid Login Credentials!";
		String actualMsg = errorMsg.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}
	
	@Test
	public void givenLoginPage_whenNullUsernameNullPassword_thenErrorOnLoginPage() {	
		doLogin(null, null);
		String expectedUrl = LOGIN_URL;
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		
		assertFalse(isElementPresent("welcomeDiv"), "Welcome div is displayed incorrectly.");
		assertFalse(isElementPresent("welcomeMsg"), "Welcome message is displayed incorrectly.");
		assertTrue(errorDiv.isDisplayed(), "Error div is not displayed");
		
		String expectedMsg = "Invalid Login Credentials!";
		String actualMsg = errorMsg.getText();
		assertEquals(actualMsg, expectedMsg, "Actual message is not the same as the expected message");
	}
	private void doLogin(String testUserName, String testPassword) {
		// enter password
		if (testUserName != null) {
			userName.sendKeys(testUserName);
		}
		if (testPassword != null) {
			password.sendKeys(testPassword);
		}
		// click login button
		loginBtn.click();
	}
	
	private Boolean isElementPresent(String elementId) {
		return driver.findElements(By.id(elementId)).size() > 0;
	}
}
