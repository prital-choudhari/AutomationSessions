package com.qa.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementutil;

	
	//Private by locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotPwdLink = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By header = By.xpath("//div[@id='logo']");
	
	//constructor
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementutil = new ElementUtil(driver);
	}
	
	//page actions / page methods / functionality and behaviour / no assertion
	
	@Step("Getting login page title")
	public String getLoginPageTitle() {
		return elementutil.waitTogetExactPageTitle(5, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Step("Getting login page header")
	public String getPageHeaderText() {
		return driver.findElement(header).getText();
	}

	@Step("Check forgot password link exist")
	public boolean isForgotPwdLinkExist() {
		return driver.findElement(forgotPwdLink).isDisplayed();
	}
	
	@Step("Login to the application with following details username is : {0} and password is : {1}")
	public void doLogin(String un, String pwd) {
		driver.findElement(emailId).sendKeys(un);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();
	}
}
