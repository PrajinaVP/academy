package com.prajina.academy.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.prajina.academy.config.WebDriverConfig;

public class LoginTest {
	
	private static final String LOGIN_URL = "http://localhost:4200";
	
	WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(id="username")
	private WebElement username;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(id="loginBtn")
	private WebElement loginBtn;
	
	@FindBy(id="welcomeDiv")
	private WebElement welcomeDiv;
	
	@FindBy(id="welcomeMsg")
	private WebElement welcomeMsg;
	
	@FindBy(id="errorUserNameDiv")
	private WebElement errorUserNameDiv;
	
	@FindBy(id="errorPasswordDiv")
	private WebElement errorPasswordDiv;
	
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
		driver.navigate().to(LOGIN_URL);
		wait = new WebDriverWait(driver, 10);
	}
	
	@Test
	public void givenLoginPage_whenValidUsernamePassword_thenOpenWelcomePage() {	
		doLogin("philonoist", "philonoist");
		String expectedUrl = LOGIN_URL + "/";
		String currentUrl = driver.getCurrentUrl();
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		//assertTrue(welcomeDiv.isDisplayed(), "Welcome div is not displayed");
	}
	
	@Test
	public void givenLoginPage_whenInValidUsernameValidPassword_thenErrorOnLoginPage() {	
		doLogin("", "philonoist");
		
		assertFalse(isElementPresent("welcomeDiv"), "Welcome div is displayed incorrectly.");
		assertTrue(errorUserNameDiv.isDisplayed(), "Error div is not displayed");
	}
	
	@Test
	public void givenLoginPage_whenInValidUsernameInValidPassword_thenErrorOnLoginPage() {	
		doLogin("", "");
		
		assertFalse(isElementPresent("welcomeDiv"), "Welcome div is displayed incorrectly.");
		assertTrue(errorUserNameDiv.isDisplayed(), "Error div is not displayed");
		assertTrue(errorPasswordDiv.isDisplayed(), "Error div is not displayed");
	}
	
	@Test
	public void givenLoginPage_whenNullUsernameValidPassword_thenErrorOnLoginPage() {	
		doLogin(null, "philonoist");
		
		assertFalse(isElementPresent("welcomeDiv"), "Welcome div is displayed incorrectly.");
		assertTrue(errorUserNameDiv.isDisplayed(), "Error div is not displayed");
		assertFalse(errorPasswordDiv.isDisplayed(), "Error div is not displayed");
	}
	
	@Test
	public void givenLoginPage_whenNullUsernameNullPassword_thenErrorOnLoginPage() {	
		doLogin(null, null);
		assertFalse(isElementPresent("welcomeDiv"), "Welcome div is displayed incorrectly.");
		assertTrue(errorUserNameDiv.isDisplayed(), "Error div is not displayed");
		assertFalse(errorPasswordDiv.isDisplayed(), "Error div is not displayed");
	}
	private void doLogin(String testUserName, String testPassword) {
		// enter password
		if (testUserName != null) {
			username.sendKeys(testUserName);
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
