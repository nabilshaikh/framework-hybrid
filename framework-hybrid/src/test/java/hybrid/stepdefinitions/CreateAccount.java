package hybrid.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import hybrid.base.Browser;
import hybrid.helper.CommonMethods;

public class CreateAccount {
	
	public static WebDriver driver;
	Browser browser = new Browser(null);
	CommonMethods cm = PageFactory.initElements(driver, CommonMethods.class);
	CreateAccount createaccount = PageFactory.initElements(driver, CreateAccount.class);
	
	//driver = browser.getDriver();
	
	
	
}