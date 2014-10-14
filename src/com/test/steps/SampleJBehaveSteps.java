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
}
