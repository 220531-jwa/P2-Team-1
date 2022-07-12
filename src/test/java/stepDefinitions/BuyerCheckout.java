package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartListPage;
import runner.AchieveTestSuite;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyerCheckout {
    public static WebDriver driver = AchieveTestSuite.driver;
    public static CartListPage clp = AchieveTestSuite.cartListPage;

    

    @Given("a Buyer is on their Cart page")
    public void a_buyer_is_on_their_cart_page() {
        driver.get("http://localhost:8081/CartListPage.html");
    }
    @When("the Buyer clicks the Checkout button")
    public void the_buyer_clicks_the_checkout_button() {
        clp.checkoutButton.click();
    }
    @Then("the Cart Total will be removed from the Buyer's balance and they will see the Checkout message")
    public void the_cart_total_will_be_removed_from_the_buyer_s_balance_and_they_will_see_the_checkout_message() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        String alertMessage = driver.switchTo().alert().getText();
        assertEquals("You have successfully checked out!", alertMessage);
    }
}
