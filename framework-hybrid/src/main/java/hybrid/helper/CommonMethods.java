package hybrid.helper;


import java.util.concurrent.TimeUnit;
import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods 
{
	public WebElement findElementByXpath(WebDriver driver, String xpathexpression)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathexpression)));
			return element;
		}
		catch (org.openqa.selenium.NoSuchElementException nsee)
		{
			System.out.println(xpathexpression + " not found on wait by Xpath");
		}
		return null;
	}
	
	public WebElement findElementById(WebDriver driver, String id)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id)));
			return element;
		}
		catch (org.openqa.selenium.NoSuchElementException nsee)
		{
			System.out.println(id + " not found on wait by ID ");
		}
		return null;
	}
	
	
	@SuppressWarnings("deprecation")
	public WebElement fluentWait(final WebDriver driver, final WebElement el) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return el;
			}
		});

		return foo;
	}
	
	
	public void enterText(WebDriver driver, WebElement el, String value) {

		fluentWait(driver, el);
		int attempt = 1;
		while (attempt <= 5) {
			try {

				new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(el));
				Point eloc = el.getLocation();
				int winHeight = driver.manage().window().getSize().height;
				if ((eloc.y - (winHeight - 50)) > 0) {
					if (el.getAttribute("id").toString().length() > 0) {
						scrolltoview(driver, "ID", el.getAttribute("id").toString());
					} else {
						scrolltoview(driver, "Class", el.getAttribute("class").toString().split(" ")[0]);
					}
				}
				
				System.out.println("Entering Text = " + value + " in " + el.getText());
				//highlight(driver, el);
				el.sendKeys(value);
				//unhighlight(driver, el);
				break;
			} catch (Exception sre) {

				System.out.println("Attempt " + attempt + " to click on Element " + el.toString() + " Failed");
				attempt = attempt + 1;

			}

		}
	}
	
	
	public void scrolltoview(WebDriver driver, String identifier, String id) {

		JavascriptExecutor ex = (JavascriptExecutor) driver;

		try {

			if (identifier.equalsIgnoreCase("ID")) {
				ex.executeScript("document.getElementById('" + id + "').scrollIntoView(0)");

			} else if (identifier.equalsIgnoreCase("Class")) {
				ex.executeScript("getElementsByClassName('" + id + "').scrollIntoView(0)");
			}
		}

		catch (Exception e) {
			e.getMessage();
		}

	}
	
	
	public void click(WebDriver driver, WebElement el) {
		// base.waitTillElementExist(el);
		fluentWait(driver, el);
		int attempt = 1;
		while (attempt <= 4) {
			try {
				new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(el));

				Point eloc = el.getLocation();
				int winHeight = driver.manage().window().getSize().height;

				if ((eloc.y - (winHeight - 50)) > 0) {
					if (el.getAttribute("id").toString().length() > 0) {
						scrolltoview(driver, "ID", el.getAttribute("id").toString());
					} else if (el.getAttribute("class").toString().split(" ")[0].length() == 0) {
						scrolltoview(driver, "Class", el.getAttribute("class").toString().split(" ")[1]);

					} else {
						scrolltoview(driver, "Class", el.getAttribute("class").toString().split(" ")[0]);
					}
				}
				
				
				//Log.info 
				System.out.println(("Clicking on Button " + el.getText()));
				//highlight(driver, el);
				el.click();
				//unhighlight(driver, el);
				break;

			} catch (Exception ex) {
				System.out.println("Attempt " + attempt + " to click on Element " + el.getText() + " Failed");
				attempt = attempt + 1;

			}
			
		}
	}
	
	
		
}
