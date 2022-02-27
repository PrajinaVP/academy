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

public class ModuleTest {
	
	private static final String MODULE_URL = "http://localhost:8080/module/view";
	
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
		driver.navigate().to(MODULE_URL);
		wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	}
	
	@Test
	public void testModulePageElements() {
		assertTrue(isElementDisplayed("moduleForm", moduleForm));
		assertTrue(isElementDisplayed("moduleName", moduleName));
		assertTrue(isElementDisplayed("description", description));
		assertTrue(isElementDisplayed("version", version));
		assertTrue(isElementDisplayed("status", status));
		assertTrue(isElementDisplayed("contact", contact));
		assertTrue(isElementDisplayed("submitBtn", submitBtn));
		assertTrue(isElementDisplayed("clearBtn", clearBtn));
	}
	
	@Test
	public void givenCoursePage_whenValidInput_thenCreateModule() {	
		
		enterFormData("module name", "Module description", "v1", "active", "module@praj.com");
		submit();
		String expectedUrl = MODULE_URL;
		String currentUrl = driver.getCurrentUrl();
		
		assertEquals(currentUrl, MODULE_URL, "Actual page url is not the same as expected");
		assertTrue(isElementDisplayed("resultDiv", resultDiv), "Module List div is not displayed");
		assertFalse(isElementEnabled("submitBtn", submitBtn));
	}
	
	@Test
	public void givenCoursePage_whenEmptyRequiredInputFields_thenDisableAddBtnAndEnableClearBtn() {	
		clear();
		enterFormData("", "Module description", "v1", "active", "module@praj.com");
		assertTrue(isElementDisplayed("nameErrorDiv", nameErrorDiv));
		assertTrue(isElementDisplayed("versionErrorDiv", versionErrorDiv));
		assertFalse(isElementEnabled("submitBtn", submitBtn));
		assertTrue(isElementEnabled("clearBtn", clearBtn));
		
		clear();
		enterFormData(null, "Module description", "v1", "active", "module@praj.com");
		assertTrue(isElementDisplayed("nameErrorDiv", nameErrorDiv));
		assertFalse(isElementEnabled("submitBtn", submitBtn));
		assertTrue(isElementEnabled("clearBtn", clearBtn));
		
		clear();
		enterFormData("module name", "Module description", "", "active", "module@praj.com");
		assertTrue(isElementDisplayed("versionErrorDiv", versionErrorDiv));
		assertFalse(isElementEnabled("submitBtn", submitBtn));
		assertTrue(isElementEnabled("clearBtn", clearBtn));
		
		clear();
		enterFormData("module name", "Module description", null, "active", "module@praj.com");
		assertTrue(isElementDisplayed("versionErrorDiv", versionErrorDiv));
		assertFalse(isElementEnabled("submitBtn", submitBtn));
		assertTrue(isElementEnabled("clearBtn", clearBtn));
		
		clear();
		enterFormData("", "Module description", "", "active", "module@praj.com");
		assertTrue(isElementDisplayed("nameErrorDiv", nameErrorDiv));
		assertTrue(isElementDisplayed("versionErrorDiv", versionErrorDiv));
	}
	
	public void enterFormData(String testName, String testDesc, String testVersion, String testStatus, String testContact) {
        if (moduleForm.isDisplayed()) {
        	if(testName != null) {
            	moduleName.sendKeys(testName);
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
