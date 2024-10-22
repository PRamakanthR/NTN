package hooks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import factory.driverfactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class myHooks {
	WebDriver driver;
	@Before
	public void setUp(){
		driverfactory.initializeBrowser("EDGE");
		driver=driverfactory.getDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
	}
	// kjlsklkgf

	@After
	public void tearDown() {
		driverfactory.quitDriver();
	}
}
