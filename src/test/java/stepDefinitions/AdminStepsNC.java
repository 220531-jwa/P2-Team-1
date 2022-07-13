package stepDefinitions;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import pages.AdminTicketPage;

public class AdminStepsNC {
	public static WebDriver driver;
	public static AdminTicketPage atp;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@BeforeAll
	public static void setup(){
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		atp = new AdminTicketPage(driver);
		driver = new ChromeDriver();

	}

	@AfterAll
	public static void teardown(){
		driver.quit();
	}
}
