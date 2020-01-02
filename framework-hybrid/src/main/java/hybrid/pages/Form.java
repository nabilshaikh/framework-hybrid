package hybrid.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hybrid.base.Browser;
import hybrid.helper.CommonMethods;

public class Form extends Browser{
	
	public Form(WebDriver driver) {
		super(driver);
	}
	CommonMethods cm = new CommonMethods();
	
	/* ================================= Page Elements ================================= */
	
	public WebElement getFname() 
	{
		return cm.findElementByXpath(driver, "//input[@class='firstname']");
	}
	
	public WebElement getLname() 
	{
		return cm.findElementByXpath(driver, "//input[@id='lname']");
	}
	
	public WebElement getCountry() 
	{
		return cm.findElementByXpath(driver, "//input[@name='country']");
	}
	
	public WebElement getSubject() 
	{
		return cm.findElementByXpath(driver, "//textarea[@name='subject']");
	}
	
	public WebElement getSubmitBtn() 
	{
		return cm.findElementByXpath(driver, "//input[@type='submit']");
	}

	
	/* ==================================== Actions ==================================== */
	
	public void enterFirstname(String inputfname) {
		WebElement firstname = getFname();
		cm.enterText(driver, firstname, inputfname);		
	}
	
	public void enterLastname(String inputlname) {
		WebElement lastname = getLname();
		cm.enterText(driver, lastname, inputlname);		
	}
	
	public void enterCountry(String inputcountry) {
		WebElement country = getCountry();
		cm.enterText(driver, country, inputcountry);		
	}
	
	public void enterSubject(String inputsubject) {
		WebElement subject = getSubject();
		cm.enterText(driver, subject, inputsubject);		
	}
	
	public void clickSubmitBtn() {
		/*WebElement submit = getSubmitBtn();
		cm.click(driver, submit);*/
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']"))).click();
	}
	
	

}
