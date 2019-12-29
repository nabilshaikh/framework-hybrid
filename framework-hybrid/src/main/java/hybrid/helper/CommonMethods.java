package hybrid.helper;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.google.common.base.Function;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
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
	
	
/*	public void enterText(WebDriver driver, WebElement el, String value) {

				new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(el));
				highlight(driver, el);
				el.sendKeys(value);
				unhighlight(driver, el);
	}*/
	
	
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
	

	
/*	public void click(WebDriver driver, WebElement el) {
				
				new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(el));
				highlight(driver, el);
				el.click();
				unhighlight(driver, el);
	}	*/
	
	public void captureScreenshot(WebDriver driver, String screenshotName) throws InterruptedException, AWTException {

		try
		{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./ScreenShots/"+screenshotName+".png"));
			System.out.println("Screenshot Taken");
		}

		catch(Exception e){

			System.out.println("Exception while taking Screenshot"+e.getMessage());
		}

	}
	
	public static String getScreenshot(WebDriver driver) throws InterruptedException, AWTException 
	{	
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir")+"/ScreenShots/"+System.currentTimeMillis()+".png";
			File destination = new File(path);
			
		try
		{		
			FileUtils.copyFile(source,destination);
		}
		
		catch(IOException e)
		{			
			System.out.println("Exception while taking Screenshot"+e.getMessage());
		}
		
		return path;
	}
	
	public void mouseHover(WebDriver driver, String xpathexpression) throws InterruptedException 
	{

		WebElement hover = driver.findElement(By.xpath(xpathexpression));
		Actions action = new Actions(driver);
		//highlight(driver, hover);
		action.moveToElement(hover).build().perform();
		//unhighlight(driver, hover);
		//Thread.sleep(2000);
		//return action;		
	}
	
	public void highlight(WebDriver driver, WebElement element){
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
		 }
	
	public void unhighlight(WebDriver driver, WebElement element){
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].setAttribute('style', 'border: 1px solid #cccccc');", element);
		 }
	
	public void selDropdownValue(WebDriver driver, String xpathexpression, String dropdown_value) {
		
		WebDriverWait wait = new WebDriverWait(driver, 5);		
		WebElement cate = findElementByXpath(driver, xpathexpression);
		wait.until(ExpectedConditions.visibilityOf(cate));
		List<WebElement> list = cate.findElements(By.tagName("li"));
		for (WebElement li : list) {

			if (li.getText().equalsIgnoreCase(dropdown_value)) {
				//highlight(driver, li);
				li.click();
				//unhighlight(driver, li);
			   }

			}
	}
	
	public void datatableSelectFirstRow(WebDriver driver, String rowxpath, String headerxpath) {

		List<WebElement> row_count1 = driver.findElements(By.xpath(rowxpath));
		for (WebElement row : row_count1)

		{
			WebElement chkbox = row.findElement(By.xpath(rowxpath + "//*[contains(@class,'checkbox')]"));
			click(driver, chkbox);
			break;
		}

	}
		
	public void uploadAttachment(WebDriver driver, String xpathExpression, String filepath) {
		driver.findElement(By.xpath(xpathExpression)).sendKeys(filepath);
	}
	
	
		
}
