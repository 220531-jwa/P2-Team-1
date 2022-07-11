package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pages.CartListPage;
import runner.AchieveTestSuite;

public class BuyerCheckout {
    public static WebDriver driver = AchieveTestSuite.driver;
    public static CartListPage clp = AchieveTestSuite.cartListPage;
    
    @Given("a Buyer is on their Cart page with at least one item in the Cart")
    public void a_buyer_is_on_their_cart_page_with_at_least_item_in_the_cart() {
        driver.get("http://localhost:8081/CartListPage.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;


    }
    @When("the Buyer clicks the Checkout button")
    public void the_buyer_clicks_the_checkout_button() {

    }
    @Then("the Cart Total will be removed from the Buyer's balance and they will see the Checkout message")
    public void the_cart_total_will_be_removed_from_the_buyer_s_balance_and_they_will_see_the_checkout_message() {

    }
}
