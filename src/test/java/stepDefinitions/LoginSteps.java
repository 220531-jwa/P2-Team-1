package stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.HomePage;
import pages.CartListPage;
import pages.CreateAccountPage;
import pages.ItemPage;
import pages.LoginPage;
import runner.AchieveTestSuite;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {

    public static WebDriver driver;
	public static HomePage bp;
	public static CartListPage cartListPage;
	public static ItemPage itemPage;
	public static CreateAccountPage cap;
	public static LoginPage lp;

	@BeforeAll
	public static void setup(){
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();
		bp = new HomePage(driver);
		cap = new CreateAccountPage(driver);
		lp = new LoginPage(driver);
		cartListPage = new CartListPage(driver);
		itemPage = new ItemPage(driver);
	}

	@AfterAll
	public static void teardown(){
		driver.quit();
	}
//Buyer Login
    @Given("a Buyer is on the Login screen and has a valid account")
    public void a_buyer_is_on_the_login_screen_and_has_a_valid_account() {
        driver.get("http://localhost:8081/login.html");
    }
    @When("the Buyer enters their {string} and {string} and clicks the Login button")
    public void the_buyer_enters_their_and_and_clicks_the_login_button(String username, String password) {
        lp.loginUser.sendKeys(username);
        lp.loginPass.sendKeys(password);
        lp.loginButton.click();
    }
    @Then("the Buyer reaches the Buyer homepage")
    public void the_buyer_reaches_the_buyer_homepage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe("http://localhost:8081/homePage.html"));
        assertEquals("http://localhost:8081/homePage.html", driver.getCurrentUrl());
    }

//Seller Login
	@Given("a Seller is on the Login screen and has a valid account")
	public void a_seller_is_on_the_login_screen_and_has_a_valid_account() {
		driver.get("http://localhost:8081/login.html");
	}
	@When("the Seller enters their {string} and {string} and clicks the Login button")
	public void the_seller_enters_their_and_and_clicks_the_login_button(String username, String password) {
		lp.loginUser.sendKeys(username);
		lp.loginPass.sendKeys(password);
		lp.loginButton.click();
	}
	@Then("the Seller reaches the Seller homepage")
	public void the_seller_reaches_the_seller_homepage() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe("http://localhost:8081/sellerHomePage.html"));
		assertEquals("http://localhost:8081/sellerHomePage.html", driver.getCurrentUrl());
	}
//Admin Login
	@Given("an Admin is on the Login screen and has a valid account")
	public void an_admin_is_on_the_login_screen_and_has_a_valid_account() {
		driver.get("http://localhost:8081/login.html");
 	}
	@When("the Admin enters their {string} and {string} and clicks the Login button")
	public void the_admin_enters_their_and_and_clicks_the_login_button(String username, String password) {
		lp.loginUser.sendKeys(username);
		lp.loginPass.sendKeys(password);
		lp.loginButton.click();
	}
	@Then("the Admin reaches the Admin homepage")
	public void the_admin_reaches_the_admin_homepage() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe("http://localhost:8081/adminHome.html"));
		assertEquals("http://localhost:8081/adminHome.html", driver.getCurrentUrl());
	}
}
