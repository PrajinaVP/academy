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

public class ModuleTest {
	
	private static final String MODULE_URL = "http://localhost:4200/module";
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(id="moduleForm")
	private WebElement moduleForm;
	
	@FindBy(id="moduleName")
	private WebElement moduleName;
	
	@FindBy(id="description")
	private WebElement description;
	
	@FindBy(id="status")
	private WebElement status;
	
	@FindBy(id="version")
	private WebElement version;
	
	@FindBy(id="contact")
	private WebElement contact;
	
	@FindBy(id="saveBtn")
	private WebElement saveBtn;
	
	@FindBy(id="closeBtn")
	private WebElement closeBtn;
	
	@FindBy(id="submitBtn")
	private WebElement submitBtn;
	
	@FindBy(id="clearBtn")
	private WebElement clearBtn;

	@FindBy(id="moduleDialogContentDiv")
	private WebElement moduleDialogContentDiv;	
	
	@FindBy(id="resultDiv")
	private WebElement resultDiv;
	
	@FindBy(id="nameErrorDiv")
	private WebElement nameErrorDiv;
	
	@FindBy(id="versionErrorDiv")
	private WebElement versionErrorDiv;
	
	@FindBy(id="inputModuleName")
	private WebElement inputModuleName;
	
	@FindBy(id="inputDescription")
	private WebElement inputDescription;
	
	@FindBy(id="inputStatus")
	private WebElement inputStatus;
	
	@FindBy(id="inputVersion")
	private WebElement inputVersion;
	
	@FindBy(id="inputContact")
	private WebElement inputContact;
	
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
		driver.navigate().to(MODULE_URL);
		wait = new WebDriverWait(driver, 5);
	}
	
	@Test
	public void testModulePageElements() {
		assertTrue(isElementDisplayed("moduleName", moduleName));
		assertTrue(isElementDisplayed("description", description));
		assertTrue(isElementDisplayed("version", version));
		assertTrue(isElementDisplayed("status", status));
		assertTrue(isElementDisplayed("contact", contact));
	}
	
	@Test
	public void givenModulePage_whenClickAddModule_thenOpenModuleDialog() {	
		
		driver.findElement(By.id("addBtn")).click();
		
		String expectedUrl = MODULE_URL;
		String currentUrl = driver.getCurrentUrl();
		
		assertEquals(currentUrl, expectedUrl, "Actual page url is not the same as expected");
		assertTrue(isElementDisplayed("moduleDialogContentDiv",moduleDialogContentDiv), "Module List div is not displayed");
		assertTrue(isElementEnabled("closeBtn", closeBtn));
		assertFalse(isElementEnabled("saveBtn", saveBtn));
	}
	
	@Test
	public void givenModulePage_whenValidInput_thenCreateModule() {	
		enterFormData("module name", "Module description", "v1", "active", "module@praj.com");
		save();
		String currentUrl = driver.getCurrentUrl();
		
		assertEquals(currentUrl, MODULE_URL, "Actual page url is not the same as expected");
		assertTrue(isElementDisplayed("resultDiv", resultDiv), "Module List div is not displayed");
	}
	
	@Test
	public void givenModulePage_whenEmptyRequiredInputFields_thenDisableAddBtnAndEnableClearBtn() {	

		enterFormData("", "Module description", "v1", "active", "module@praj.com");
		assertFalse(isElementEnabled("saveBtn", saveBtn));
		assertTrue(isElementEnabled("closeBtn", closeBtn));
		

		enterFormData(null, "Module description", "v1", "active", "module@praj.com");
		assertFalse(isElementEnabled("saveBtn", saveBtn));
		assertTrue(isElementEnabled("closeBtn", closeBtn));
	
		enterFormData("module name", "Module description", "", "active", "module@praj.com");
		assertFalse(isElementEnabled("saveBtn", saveBtn));
		assertTrue(isElementEnabled("closeBtn", closeBtn));
		
		enterFormData("module name", "Module description", null, "active", "module@praj.com");
		assertFalse(isElementEnabled("saveBtn", saveBtn));
		assertTrue(isElementEnabled("closeBtn", closeBtn));
		
		enterFormData("", "Module description", "", "active", "module@praj.com");
		assertFalse(isElementEnabled("saveBtn", saveBtn));
		assertTrue(isElementEnabled("closeBtn", closeBtn));
	}
	
	public void enterFormData(String testName, String testDesc, String testVersion, String testStatus, String testContact) {
        if (moduleForm.isDisplayed()) {
        	if(testName != null) {
        		inputModuleName.sendKeys(testName);
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
		saveBtn.click();
	}
	
	private void close() {
		closeBtn.click();
	}
	
	private void clear() {
		closeBtn.submit();
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
