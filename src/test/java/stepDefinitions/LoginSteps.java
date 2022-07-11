package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import runner.AchieveTestSuite;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {
    private WebDriver driver = AchieveTestSuite.driver;
    private LoginPage lp = AchieveTestSuite.lp;
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
