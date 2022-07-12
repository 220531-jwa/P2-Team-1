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

import pages.BuyerPage;
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
}
