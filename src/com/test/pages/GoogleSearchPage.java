package com.test.pages;

import java.util.List;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchPage extends AbstractPage {

	public GoogleSearchPage(WebDriverProvider driverProvider) {
		super(driverProvider);
	}

	private Pagefactory page = new Pagefactory();

	public void fetchURL() {
		get("http://www.google.co.in");
		System.out.println("Google home page opened");
	}

	public void performSearch(String searchText) {
		findElement(By.id("gbqfq")).sendKeys(searchText);
		findElement(By.xpath("//*[@id='gbqfb']/span")).click();
	}

	@SuppressWarnings("static-access")
	public boolean checkSearchResults() {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(page.getDriver(), 10);
		wait.until(ExpectedConditions.visibilityOf(findElement(By.id("rso"))));
		WebElement table = findElement(By.id("rso"));
		List<WebElement> resultList = table.findElements(By.tagName("li"));
		System.out.println("size of result list: " + resultList.size());
		if (resultList.size() > 0) {
			flag = true;
		}
		return flag;
	}

}
