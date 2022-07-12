package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BuyerPage;
import pages.CartListPage;
import pages.CreateAccountPage;
import pages.ItemPage;
import pages.LoginPage;

public class CreateAccountSteps {
	public static WebDriver driver;
	public static BuyerPage bp;
	public static CartListPage cartListPage;
	public static ItemPage itemPage;
	public static CreateAccountPage cap;
	public static LoginPage lp;

	@BeforeAll
	public static void setup(){
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();
		bp = new BuyerPage(driver);
		cap = new CreateAccountPage(driver);
		lp = new LoginPage(driver);
		cartListPage = new CartListPage(driver);
		itemPage = new ItemPage(driver);
	}
	
	@AfterAll
	public static void teardown(){
		driver.quit();
	}
    
    @Given("a User is on the Create an Account page")
    public void a_user_is_on_the_create_an_account_page() {
        driver.get("http://localhost:8081/createAccount.html");
    }
    @When("the User enters a random username and password and name and presses create account")
    public void the_use_enters_a_random_username_and_password_and_name_and_presses_create_account() {
    	
    	Random rand = new Random();
    	int upperbound = 100000;
    	int random = rand.nextInt(upperbound);
    	String testchars = String.valueOf(random);
    	
        cap.createUsername.sendKeys(testchars);
        cap.createPassword.sendKeys(testchars);
        cap.createName.sendKeys(testchars);
        cap.createAccountButton.click();
    }
    @Then("the User will be redirected to the Login screen")
    public void the_user_will_be_redirected_to_the_login_screen() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe("http://localhost:8081/login.html"));
        assertEquals("http://localhost:8081/login.html", driver.getCurrentUrl());
    }

}
