package stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class BuyerCheckout {
    public static WebDriver driver = AchieveTestSuite.driver;
    public static CartListPage clp = AchieveTestSuite.clp;
    public static LoginPage lp = AchieveTestSuite.lp;
    public static HomePage bp = AchieveTestSuite.bp;
    public static ItemPage ip = AchieveTestSuite.ip;

    @BeforeAll
	public static void setup(){
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();
		clp = new CartListPage(driver);
        lp = new LoginPage(driver);
        bp = new HomePage(driver);
        ip = new ItemPage(driver);
	}

	@AfterAll
	public static void teardown(){
		driver.quit();
	}


    @Given("a Buyer is logged in and has items in their cart and is on the cart page")
    public void a_buyer_is_logged_in_and_has_items_in_their_cart_and_is_on_their_cart_page() {
        driver.get("http://localhost:8081/login.html");
        lp.loginUser.sendKeys("apple");
        lp.loginPass.sendKeys("bottom");
        lp.loginButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.titleIs("Achieve Home Page"));
        bp.browseProductsBtn.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("AddButt0")));
        ip.pageAddButton.click();
        ip.toCartButton.click();
    }
    @When("the Buyer clicks the Checkout button")
    public void the_buyer_clicks_the_checkout_button() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.titleIs("Cart List Page"));
        clp.checkoutButton.click();
    }
    @Then("the Cart Total will be removed from the Buyer's balance and they will see the Checkout message")
    public void the_cart_total_will_be_removed_from_the_buyer_s_balance_and_they_will_see_the_checkout_message() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        String alertMessage = driver.switchTo().alert().getText();
        assertEquals("You have successfully checked out!", alertMessage);
    }
}
