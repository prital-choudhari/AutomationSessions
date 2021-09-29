package com.qa.democart.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	private Properties prop;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initalize driver
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver initDriver(Properties prop) {
		this.prop = prop;

		String browserName = prop.getProperty("browser");
		System.out.println("Browser is" + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver();

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remotedriver("chrome");
			} else {
				tlDriver.set(new ChromeDriver());
			}

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
		} else {
			System.out.println("Please pass the correct browser name" + browserName);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();

		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	private void init_remotedriver(String browserName) {
		System.out.println("Running test on remote with browser :" + browserName);
		DesiredCapabilities caps = DesiredCapabilities.chrome();

		try {
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), caps));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initalize properties on signle env
	 * 
	 * @return
	 */

	public Properties initProperties() {
		Properties prop = null;
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop = new Properties();
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * Take Screenshots
	 * 
	 * @return
	 */
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
