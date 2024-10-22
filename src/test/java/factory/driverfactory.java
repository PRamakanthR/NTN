package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class driverfactory {
	static WebDriver driver=null;

	public static void initializeBrowser(String browser) {
		if(browser.toLowerCase().equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			 driver= new ChromeDriver();
		}
		else if(browser.toLowerCase().equals("edge")) {
			WebDriverManager.edgedriver().setup();
			 driver= new EdgeDriver();
		}
		else if(browser.toLowerCase().equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			 driver= new FirefoxDriver();
		}
		else if(browser.toLowerCase().equals("safari")) {
			 driver= new SafariDriver();
		}
		
	}
	
	public static WebDriver getDriver() {
		return driver;

	} 
	public static void quitDriver() {
		driver.close();
		driver=null;
	} 

}
