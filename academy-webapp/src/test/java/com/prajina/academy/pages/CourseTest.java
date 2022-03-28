package com.prajina.academy.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.prajina.academy.config.WebDriverConfig;

public class CourseTest {
	
	private static final String COURSE_URL = "http://localhost:8080/course/view";
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(id="courseForm")
	private WebElement courseForm;
	
	@FindBy(id="courseName")
	private WebElement courseName;
	
	@FindBy(id="description")
	private WebElement description;
	
	@FindBy(id="status")
	private WebElement status;
	
	@FindBy(id="version")
	private WebElement version;
	
	@FindBy(id="contact")
	private WebElement contact;
	
	@FindBy(id="submitBtn")
	private WebElement submitBtn;
	
	@FindBy(id="clearBtn")
	private WebElement clearBtn;
	
	@FindBy(id="resultDiv")
	private WebElement resultDiv;
	
	@FindBy(id="nameErrorDiv")
	private WebElement nameErrorDiv;
	
	@FindBy(id="versionErrorDiv")
	private WebElement versionErrorDiv;
	
	@BeforeSuite
	public void setup() {
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
		driver.navigate().to(COURSE_URL);
		wait = new WebDriverWait(driver,Duration.ofSeconds(500));
	}
	
	@Test
	public void testCoursePageElements() {
		assertTrue(isElementDisplayed("courseForm", courseForm));
		assertTrue(isElementDisplayed("courseName", courseName));
		assertTrue(isElementDisplayed("description", description));
		assertTrue(isElementDisplayed("version", version));
		assertTrue(isElementDisplayed("status", status));
		assertTrue(isElementDisplayed("contact", contact));
		assertTrue(isElementDisplayed("submitBtn", submitBtn));
		assertTrue(isElementDisplayed("clearBtn", clearBtn));
	}
	
	@Test
	public void givenCoursePage_whenValidInput_thenCreateCourse() {	
		
		enterFormData("Java 17", "Java 17 Desc", "v1", "active", "java@praj.com");
		submit();
		String expectedUrl = COURSE_URL;
		String currentUrl = driver.getCurrentUrl();
		
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		assertTrue(isElementDisplayed("resultDiv", resultDiv), "Course List div is not displayed");
		assertFalse(isElementEnabled("submitBtn", submitBtn));
	}
	
	@Test
	public void givenCoursePage_whenEmptyRequiredInputFields_thenDisableAddBtnAndEnableClearBtn() {	
		clear();
		enterFormData("", "Java 17 Desc", "v1", "active", "java@praj.com");
		assertTrue(isElementDisplayed("nameErrorDiv", nameErrorDiv));
		assertTrue(isElementDisplayed("versionErrorDiv", versionErrorDiv));
		assertFalse(isElementEnabled("submitBtn", submitBtn));
		assertTrue(isElementEnabled("clearBtn", clearBtn));
		
		enterFormData(null, "Java 17 Desc", "v1", "active", "java@praj.com");
		assertTrue(isElementDisplayed("nameErrorDiv", nameErrorDiv));
		assertFalse(isElementEnabled("submitBtn", submitBtn));
		assertTrue(isElementEnabled("clearBtn", clearBtn));
		
		clear();
		
		enterFormData("Java 17", "Java 17 Desc", "", "active", "java@praj.com");
		assertTrue(isElementDisplayed("versionErrorDiv", versionErrorDiv));
		assertFalse(isElementEnabled("submitBtn", submitBtn));
		assertTrue(isElementEnabled("clearBtn", clearBtn));
		
		clear();
		
		enterFormData("Java 17", "Java 17 Desc", null, "active", "java@praj.com");
		assertTrue(isElementDisplayed("versionErrorDiv", versionErrorDiv));
		assertFalse(isElementEnabled("submitBtn", submitBtn));
		assertTrue(isElementEnabled("clearBtn", clearBtn));
		
		clear();
		
		enterFormData("", "Java 17 Desc", "", "active", "java@praj.com");
		assertTrue(isElementDisplayed("nameErrorDiv", nameErrorDiv));
		assertTrue(isElementDisplayed("versionErrorDiv", versionErrorDiv));
	}
	
	public void enterFormData(String testName, String testDesc, String testVersion, String testStatus, String testContact) {
        if (courseForm.isDisplayed()) {
        	if(testName != null) {
            	courseName.sendKeys(testName);
            }
            if(testDesc != null) {
            	description.sendKeys(testDesc);
            }
            if(testVersion != null) {
            	version.sendKeys(testVersion);
            }
            if(testStatus != null) {
            	status.sendKeys(testStatus);
            }
            if(testContact != null) {
            	contact.sendKeys(testContact);
            }
        }
    }
	
	private void submit() {
		submitBtn.submit();
	}
	
	private void clear() {
		clearBtn.submit();
	}
	
	private boolean isElementDisplayed(String elementId, WebElement webElement) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementId)));
        return webElement.isDisplayed();
	} 
	
	private boolean isElementEnabled(String elementId, WebElement webElement) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementId)));
        return webElement.isEnabled();
	} 
	 
}
