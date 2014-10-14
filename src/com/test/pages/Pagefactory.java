package com.test.pages;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Pagefactory {

	private static WebDriverProvider driverProvider = new WebDriverProvider() {

		@Override
		public boolean saveScreenshotTo(String arg0) {
			return true;
		}

		@Override
		public void initialize() {

		}

		@Override
		public WebDriver get() {

			return getDriver();
		}

		@Override
		public void end() {

		}
	};

	public WebDriverProvider getDriverProvider() {
		return driverProvider;
	}
	public static ChromeOptions setChromeOption()
	{
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--allow-running-insecure-content=true");
	System.setProperty("webdriver.chrome.driver","D:\\Softwares\\chromedriver.exe");
	return options;
	}
	private static WebDriver driver=new ChromeDriver(setChromeOption());

	public static WebDriver getDriver() {
		driver.manage().window().maximize();
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		Pagefactory.driver = driver;
	}

	@SuppressWarnings("static-access")
	@AfterStories
	public void closeDriver() {
		this.driver.quit();
	}

}
