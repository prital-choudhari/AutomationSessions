package com.qa.democart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.democart.Factory.DriverFactory;
import com.qa.democart.pages.LoginPage;

public class BaseTest {

	DriverFactory df;
	WebDriver driver;
	Properties prop;
	LoginPage loginPage;
	
	@BeforeTest
	public void setup() {
		
		df = new DriverFactory();
		prop = df.initProperties();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
