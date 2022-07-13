package runner;

import java.io.File;

import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.Suite;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import pages.BuyerPage;
import pages.CartListPage;
import pages.CreateAccountPage;
import pages.ItemPage;
import pages.LoginPage;



@Suite
@Tag("CucumberTests")
@IncludeTags("CucumberTests")
public class AchieveTestSuite {

	public static WebDriver driver;
	public static BuyerPage bp;
	public static CartListPage clp;
	public static ItemPage ip;
	public static CreateAccountPage cap;
	public static LoginPage lp;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@BeforeAll
	public static void setup(){
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();
		bp = new BuyerPage(driver);
		cap = new CreateAccountPage(driver);
		lp = new LoginPage(driver);
		clp = new CartListPage(driver);
		ip = new ItemPage(driver);
	}

	@AfterAll
	public static void teardown(){
		driver.quit();
	}
}
