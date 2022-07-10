package steps.BuyerSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.createAccountPage;
import runner.AchieveTestSuite;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateAccountSteps {
    private WebDriver driver = AchieveTestSuite.driver;
    private createAccountPage createAccountPage = AchieveTestSuite.createAccountPage;
    @Given("a User is on the Create an Account page")
    public void a_user_is_on_the_create_an_account_page() {
        driver.get("http://localhost:8081/createAccount.html");
    }
    @When("the User enters their {string}, {string}, and {string} and clicks the Create Account button")
    public void the_user_enters_their_and_and_clicks_the_create_account_button(String username, String password, String name) {
        createAccountPage.createUsername.sendKeys(username);
        createAccountPage.createPassword.sendKeys(password);
        createAccountPage.createName.sendKeys(name);
        createAccountPage.createAccountButton.click();
    }
    @Then("the User will be redirected to the Login screen")
    public void the_user_will_be_redirected_to_the_login_screen() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe("http://localhost:8081/loginPage.html"));
        assertEquals("http://localhost:8081/loginPage.html", driver.getCurrentUrl());
    }

}
