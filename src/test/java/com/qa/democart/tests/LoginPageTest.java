package com.qa.democart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.democart.utils.Constants;
import com.qa.democart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest {

	@Description("This is my login page titile test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login Page Title is : " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, Errors.TITILE_ERROR_MESSAGE);
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageHeaderTest() {
		String header = loginPage.getPageHeaderText();
		System.out.println("This is Page Header : " + header);
		Assert.assertEquals(header, Constants.PAGE_HEADER, Errors.HEADER_ERROR_MESSAGE);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
}
