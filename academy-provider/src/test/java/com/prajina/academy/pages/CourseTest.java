package com.prajina.academy.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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
	
	private static final String COURSE_URL = "http://localhost:4200/course";
	
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
	
	@FindBy(id="inputCourseName")
	private WebElement inputCourseName;
	
	@FindBy(id="inputDescription")
	private WebElement inputDescription;
	
	@FindBy(id="inputStatus")
	private WebElement inputStatus;
	
	@FindBy(id="inputVersion")
	private WebElement inputVersion;
	
	@FindBy(id="inputContact")
	private WebElement inputContact;
	
	@FindBy(id="clearBtn")
	private WebElement clearBtn;
	
	@FindBy(id="courseDialogCloseBtn")
	private WebElement courseDialogCloseBtn;
	
	@FindBy(id="courseDialogContentDiv")
	private WebElement courseDialogContentDiv;
	
	@FindBy(id="courseDialogSaveBtn")
	private WebElement courseDialogSaveBtn;
	
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
		wait = new WebDriverWait(driver, 5);
	}
	
	
	@Test
	public void testCoursePageElements() {
		assertTrue(isElementDisplayed("courseName", courseName));
		assertTrue(isElementDisplayed("description", description));
		assertTrue(isElementDisplayed("version", version));
		assertTrue(isElementDisplayed("status", status));
		assertTrue(isElementDisplayed("contact", contact));
	}
	
	@Test
	public void givenCoursePage_whenClickAddCourse_thenOpenCourseDialog() {	
		
		driver.findElement(By.id("addBtn")).click();
		
		String expectedUrl = COURSE_URL;
		String currentUrl = driver.getCurrentUrl();
		
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		assertTrue(isElementDisplayed("courseDialogContentDiv", courseDialogContentDiv), "Course List div is not displayed");
		assertTrue(isElementEnabled("courseDialogCloseBtn", courseDialogCloseBtn));
		assertFalse(isElementEnabled("courseDialogSaveBtn", courseDialogSaveBtn));
	}
	
	@Test
	public void givenCoursePage_whenValidInput_thenCreateCourse() {	
		
		enterFormData("Java 17", "Java 17 Desc", "v1", "active", "java@praj.com");
		save();
		
		String expectedUrl = COURSE_URL;
		String currentUrl = driver.getCurrentUrl();
		
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		assertTrue(isElementDisplayed("resultDiv", resultDiv), "Course List div is not displayed");
	}
	
	@Test
	public void givenCoursePage_whenEmptyRequiredInputFields_thenDisableAddBtnAndEnableClearBtn() {	
		
		enterFormData("", "Java 17 Desc", "v1", "active", "java@praj.com");
		assertFalse(isElementEnabled("courseDialogSaveBtn", courseDialogSaveBtn));
		assertTrue(isElementEnabled("courseDialogCloseBtn", courseDialogCloseBtn));
		
		enterFormData(null, "Java 17 Desc", "v1", "active", "java@praj.com");
		assertFalse(isElementEnabled("courseDialogSaveBtn", courseDialogSaveBtn));
		assertTrue(isElementEnabled("courseDialogCloseBtn", courseDialogCloseBtn));
		
		
		enterFormData("Java 17", "Java 17 Desc", "", "active", "java@praj.com");
		assertFalse(isElementEnabled("courseDialogSaveBtn", courseDialogSaveBtn));
		assertTrue(isElementEnabled("courseDialogCloseBtn", courseDialogCloseBtn));
		
		enterFormData("Java 17", "Java 17 Desc", null, "active", "java@praj.com");
		assertFalse(isElementEnabled("courseDialogSaveBtn", courseDialogSaveBtn));
		assertTrue(isElementEnabled("courseDialogCloseBtn", courseDialogCloseBtn));
		
		enterFormData("", "Java 17 Desc", "", "active", "java@praj.com");
		assertFalse(isElementEnabled("courseDialogSaveBtn", courseDialogSaveBtn));
		assertTrue(isElementEnabled("courseDialogCloseBtn", courseDialogCloseBtn));
	}
	
	public void enterFormData(String testName, String testDesc, String testVersion, String testStatus, String testContact) {
        if (courseForm.isDisplayed()) {
        	if(testName != null) {
        		inputCourseName.sendKeys(testName);
            }
            if(testDesc != null) {
            	inputDescription.sendKeys(testDesc);
            }
            if(testVersion != null) {
            	inputVersion.sendKeys(testVersion);
            }
            if(testStatus != null) {
            	inputStatus.sendKeys(testStatus);
            }
            if(testContact != null) {
            	inputContact.sendKeys(testContact);
            }
        }
    }
	
	private void save() {
		courseDialogSaveBtn.click();
	}
	
	private void clear() {
		courseDialogCloseBtn.click();
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
