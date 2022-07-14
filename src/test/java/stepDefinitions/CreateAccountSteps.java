package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.Duration;
import java.util.Random;

import org.junit.jupiter.api.Tag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CreateAccountPage;

public class CreateAccountSteps {
	public static WebDriver driver;
	public static CreateAccountPage cap;

	@BeforeAll
	public static void setup(){
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();
		cap = new CreateAccountPage(driver);
	}

	@AfterAll
	public static void teardown(){
		driver.quit();
	}

	@Given("a User is on the Create an Account page")
	public void a_user_is_on_the_create_an_account_page() {
		driver.get("http://localhost:8081/createAccount.html");
	}

	@When("the User enters a random username and password and name and chooses the Buyer option and clicks the Create Account button")
	public void theUserEntersARandomUsernameAndPasswordAndNameAndChoosesTheBuyerOptionAndClicksTheCreateAccountButton() {
		Random rand = new Random();
		int upperbound = 100000;
		int random = rand.nextInt(upperbound);
		String testchars = String.valueOf(random);

		cap.createUsername.sendKeys(testchars);
		cap.createPassword.sendKeys(testchars);
		cap.createName.sendKeys("deleteme");
		Select stat = new Select(cap.createAccType);
		stat.selectByVisibleText("Buyer");
		cap.createAccountButton.click();
	}
	@Then("the Buyer will be redirected to the Login screen")
	public void theBuyerWillBeRedirectedToTheLoginScreen() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe("http://localhost:8081/login.html"));
		assertEquals("http://localhost:8081/login.html", driver.getCurrentUrl());
	}
}
