package runner;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.Suite;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import pages.*;


@Suite
@Tag("CucumberTests")
@IncludeTags("CucumberTests")
public class AchieveTestSuite {

	public static WebDriver driver;
	public static HomePage bp;
	public static CartListPage clp;
	public static ItemPage ip;
	public static CreateAccountPage cap;
	public static LoginPage lp;
	public static CreateItemPage cip;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@BeforeAll
	public static void setup(){
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();
		bp = new HomePage(driver);
		cap = new CreateAccountPage(driver);
		lp = new LoginPage(driver);
		clp = new CartListPage(driver);
		ip = new ItemPage(driver);
		cip = new CreateItemPage(driver);
	}

	@AfterAll
	public static void teardown(){
		driver.quit();
	}

}
