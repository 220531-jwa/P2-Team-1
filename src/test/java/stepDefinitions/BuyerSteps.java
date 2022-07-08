package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.CartListPage;
import pages.ItemPage;
import runner.CartRunner;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BuyerSteps {
public static WebDriver driver = CartRunner.driver;
public static CartListPage cartListPage = CartRunner.cartListPage;
public static ItemPage itemPage = CartRunner.itemPage;

@BeforeAll
public static void setup() {
	File chrome = new File("src/test/resources/chromedriver.exe");
	System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
	driver = new ChromeDriver();
	cartListPage = new CartListPage(driver);
	itemPage = new ItemPage(driver);
}

@AfterAll
public static void teardown() {
//	driver.quit();
}

@Given("a Buyer is on the ItemPage")
public void a_buyer_is_on_the_item_page() {
	driver.get("http://localhost:8081/ItemPage.html");
	
}

@When("the Buyer clicks the CartButton")
public void the_buyer_clicks_the_cart_button() {
	itemPage.pageAddButton.click();
}

@Then("The Element should be added to the Cart")
public void the_element_should_be_added_to_the_cart() {
	
}


@Given("a Buyer is on the CartListPage")
public void a_buyer_is_on_the_cart_list_page() {
	driver.get("http://localhost:8081/CartListPage");
}

@When("the Buyer clicks the RemoveCartButton")
public void the_buyer_clicks_the_remove_cart_button() {
	cartListPage.pageDeleteButton.click();
}

@Then("The Item should be removed from the Buyer Cart")
public void the_item_should_be_removed_from_the_buyer_cart() {
	
}

}
