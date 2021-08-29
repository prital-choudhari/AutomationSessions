package com.qa.democart.utils;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doSendkeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public String doGetAttribute(By locator, String ateName) {
		return getElement(locator).getAttribute(ateName);
	}

	public boolean doisDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	/****************************** Dropdown *********************************/

	public void doSelectByIndex(By locator, int value) {
		Select op = new Select(driver.findElement(locator));
		op.selectByIndex(value);
	}

	public void doSelectByValue(By locator, String value) {
		Select op = new Select(driver.findElement(locator));
		op.selectByValue(value);
	}

	public void doSelectByVisibleText(By locator, String value) {
		Select op = new Select(driver.findElement(locator));
		op.selectByVisibleText(value);
	}

	public void doGetDropdownOptions(By locator) {
		Select op = new Select(getElement(locator));
		List<WebElement> options = op.getOptions();
		System.out.println(options.size());

		for (WebElement e : options) {
			System.out.println(e.getText());
		}
	}

	/***********************************
	 * Drag And Drop
	 **********************************/
	public void dragAndDrop(By source, By target) {
		Actions act = new Actions(driver);
		act.clickAndHold(getElement(source)).moveToElement(getElement(source)).release(getElement(target)).build()
				.perform();
	}

	/*********************************
	 * Move To Element
	 **********************************/
	public void TwoMenumoveToEle(By parentMenu, By childMenu1, By childMenu2) throws InterruptedException {
		Actions selMenu = new Actions(driver);

		selMenu.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(2000);

		selMenu.moveToElement(getElement(childMenu1)).perform();
		Thread.sleep(2000);

		selMenu.moveToElement(getElement(childMenu2)).perform();
		getElement(childMenu2).click();
	}

	/****************************** Wait Utils ********************************/

	public Alert waitForAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public String getAlertText(int timeout) {
		return waitForAlert(timeout).getText();
	}

	public void acceptAlert(int timeout) {
		waitForAlert(timeout).accept();
	}
	
	public void dismissAlert(int timeout) {
		waitForAlert(timeout).dismiss();
	}
	
	public void sendKeysToAlert(int timeout, String value) {
		waitForAlert(timeout).sendKeys(value);
	}
	
	
	public String waitTogetPageTitle(int timeout, String value) {
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		if(wait.until(ExpectedConditions.titleContains(value))){
			return driver.getTitle();
		}
		return null;
	}
	
	public String waitTogetExactPageTitle(int timeout, String value) {
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		if(wait.until(ExpectedConditions.titleIs(value))){
			return driver.getTitle();
		}
		return null;
	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does notnecessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */

	public WebElement WaitForElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
}
