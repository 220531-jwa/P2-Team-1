package stepDefinitions;

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
    
    @Given("a User is on the Create an Account page")
    public void a_user_is_on_the_create_an_account_page() {
        driver.get("http://localhost:8081/createAccount.html");
    }
    @When("the User enters their {string}, {string}, and {string} and clicks the Create Account button")
    public void the_user_enters_their_and_and_clicks_the_create_account_button(String username, String password, String name) {
        cap.createUsername.sendKeys(username);
        cap.createPassword.sendKeys(password);
        cap.createName.sendKeys(name);
        cap.createAccountButton.click();
    }
    @Then("the User will be redirected to the Login screen")
    public void the_user_will_be_redirected_to_the_login_screen() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe("http://localhost:8081/login.html"));
        assertEquals("http://localhost:8081/login.html", driver.getCurrentUrl());
    }

}
