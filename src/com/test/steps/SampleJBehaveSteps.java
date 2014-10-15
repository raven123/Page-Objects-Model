package com.test.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import com.test.pages.GoogleSearchPage;
import com.test.pages.Pagefactory;

public class SampleJBehaveSteps {
	
	private GoogleSearchPage home;
	private Pagefactory page =new Pagefactory();
	
	
	
	
	@Given("I am on google home page")
	public void iamOnGoogleHomePage() {
		home=new GoogleSearchPage(page.getDriverProvider());
		home.fetchURL();
      
	}

	@When("I perform search for $hello")
	public void iSearchFor(String searchKeyword)
	{
		home.performSearch(searchKeyword);
	}
	
	@Then("I should get search results")
	public void iShouldGetSearchresults()
	{
		Assert.assertTrue(home.checkSearchResults());
	}
	
	
	
	/**
	 * The required libraries in the Page Object model of Jbehave are : 
	 * Jbehave-web-selenium-3.5
	 * Jbehave-core-3.92
	 * paranamer2.4
	 * xstream 1.45
	 * xpp3_min-1.14c
	 * commons.lang-2.6
	 * freemarker-2.3.19
	 * plexus-utils-3.0.10
	 * Jbehave-Junit_runner-1.1.2
	 * selenium-api-2.9.0
	 * selenium-standalaone-server-2.42.2
	 * and also include Junit4 standard libraries
	 * 
	 * 
	 */
}
