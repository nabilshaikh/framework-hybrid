package hybrid.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import hybrid.base.Browser;
import hybrid.helper.CommonMethods;

public class CreateAccount extends Browser{
	
	public CreateAccount(WebDriver driver) {
		super(driver);
	}
	CommonMethods cm = new CommonMethods();
	
	/* ================================= Page Elements ================================= */
	
	public WebElement getFirstname() 
	{
		return cm.findElementByXpath(driver, "//input[@id='firstname']");
	}
	
	public WebElement getLastname() 
	{
		return cm.findElementByXpath(driver, "//input[@id='lastname']");
	}
	
	public WebElement getEmail() 
	{
		return cm.findElementByXpath(driver, "//input[@id='email_address']");
	}
	
	public WebElement getPassword() 
	{
		return cm.findElementByXpath(driver, "//input[@id='password']");
	}
	
	public WebElement getConfirmPassword() 
	{
		return cm.findElementByXpath(driver, "//input[@id='confirmation']");
	}
	
	public WebElement getSubmitBtn() 
	{
		return cm.findElementByXpath(driver, "//button[@title='Register']");
	}
	
	
	/* ==================================== Actions ==================================== */
	
	public void enterFirstname(String inputfname) {
		WebElement firstname = getFirstname();
		cm.enterText(driver, firstname, inputfname);		
	}
	
	public void enterLastname(String inputlname) {
		WebElement lastname = getLastname();
		cm.enterText(driver, lastname, inputlname);		
	}
	
	public void enterEmail(String inputemail) {
		WebElement email = getEmail();
		cm.enterText(driver, email, inputemail);		
	} 
	
	public void enterPassword(String inputpassword) {
		WebElement password = getPassword();
		cm.enterText(driver, password, inputpassword);		
	}
	
	public void enterConfirmPassword(String inputconfpassword) {
		WebElement confpassword = getConfirmPassword();
		cm.enterText(driver, confpassword, inputconfpassword);		
	}
	
	public void clickSubmit() {
		WebElement submit = getSubmitBtn();
		cm.click(driver, submit);	
	}	
	
	
}
