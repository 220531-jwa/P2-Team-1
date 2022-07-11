package runner;

import java.io.File;

import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.Suite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import pages.BuyerPage;
import pages.CartListPage;
import pages.ItemPage;
import pages.LoginPage;
import pages.createAccountPage;


@Suite
@Tag("CucumberTests")
@IncludeTags("CucumberTests")
public class AchieveTestSuite {
	
	public static WebDriver driver;
	public static BuyerPage bp;
	public static CartListPage cartListPage;
	public static ItemPage itemPage;
	public static createAccountPage cap;
	public static LoginPage lp;

	@BeforeAll
	public static void setup(){
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();
		bp = new BuyerPage(driver);
		cap = new createAccountPage(driver);
		lp = new LoginPage(driver);
		cartListPage = new CartListPage(driver);
		itemPage = new ItemPage(driver);
	}

	@AfterAll
	public static void teardown(){
		driver.quit();
	}
}
