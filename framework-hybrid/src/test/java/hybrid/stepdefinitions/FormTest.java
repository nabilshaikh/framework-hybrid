package hybrid.stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hybrid.base.Browser;
import hybrid.helper.PropertiesFile;
import hybrid.pages.Form;

public class FormTest {
	
	public static WebDriver driver;
	String username;
	String password;
	String url;
	Browser browser = new Browser(null);
	
	@Given("^Open application$")
	public void open_app() throws IOException, InterruptedException
	{
		driver = browser.getDriver();
		url = PropertiesFile.getProperty("app_url");
		browser.launchApplication(driver, url);
		Thread.sleep(2000);
	}
	
	@When("^I populate all mandatory fields and submit the form$")
	public void populate_all_details() throws IOException, InterruptedException
	{
		Form form = PageFactory.initElements(driver, Form.class);
		form.enterFirstname("hello");
		form.enterLastname("world");
		form.enterCountry("Bharat");
		form.enterSubject("Subject");
		form.clickSubmitBtn();
	}
	
	@Then("^Form should get submitted$")
	public void form_submission() throws IOException, InterruptedException
	{
		System.out.println("Form Subnmitted !");
		driver.quit();
	}
	
	
	
}
