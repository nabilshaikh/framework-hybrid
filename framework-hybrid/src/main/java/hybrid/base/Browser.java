package hybrid.base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import hybrid.helper.PropertiesFile;

public class Browser 
{
	public static WebDriver driver;
	
	public Browser(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public WebDriver getDriver() throws IOException
	{
		File file = new File(System.getProperty("user.dir") + "\\"
				+ PropertiesFile.getProperty("driverpath") + "\\" + PropertiesFile.getProperty("driverfile"));
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		driver = new InternetExplorerDriver();
						
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability("requireWindowFocus", true);
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability("nativeEvents", false);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
		capabilities.setCapability("unexpectedAlertBehaviour", "accept");
		capabilities.setCapability("ignoreProtectedModeSettings", true);
		capabilities.setCapability("disable-popup-blocking", true);
		

		return driver;
	}
	
	public void launchApplication(WebDriver driver, String url) 
	{
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public static void waitTillElementExist(String tag) {
		WebDriverWait wait = new WebDriverWait(driver, 45);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(tag)));
		wait.until(ExpectedConditions.elementToBeClickable(By.id(tag)));

	}
	
	
	public static boolean waitForPageReady() {

		boolean flag = false;
		while (!flag) {
			try {

				ExpectedCondition<Boolean> isPageReady = new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						return ((JavascriptExecutor) driver).executeScript("return document.readyState")
								.equals("complete");
					}
				};
				Wait<WebDriver> wait = new WebDriverWait(driver, 120);
				flag = wait.until(isPageReady);
				return flag;
			}

			catch (Throwable error) {
				System.out.println("in catch block");
				return flag;

			}
		}
		return flag;
	}
	
	
	public static void ieKiller() throws Exception 
	{
		final String KILL = "taskkill /F /IM ";
		String processName = "IEDriverServer.exe ";
		String postProcess = "/T";
		Runtime.getRuntime().exec(KILL + processName + postProcess);
	}
		
}
