package stepDefinitions;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.CartListPage;
import pages.ItemPage;
import runner.AchieveTestSuite;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Rowand_CartSteps {
public static WebDriver driver = AchieveTestSuite.driver;
public static CartListPage clp = AchieveTestSuite.clp;
public static ItemPage ip = AchieveTestSuite.ip;
JavascriptExecutor js = (JavascriptExecutor) driver;

@BeforeAll
public static void setup() {
	File chrome = new File("src/test/resources/chromedriver.exe");
	System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
	driver = new ChromeDriver();
	clp = new CartListPage(driver);
	ip = new ItemPage(driver);

}

@AfterAll
public static void teardown() {
	driver.quit();
}

@Given("the test buyer account is loaded")
public void the_test_buyer_account_is_loaded() {
	 js.executeScript("sessionStorage.setItem('activeUser', '{\"username\":\"josh\",\"password\":\"josh\",\"id\":1,\"name\":\"josh\",\"accountType\":1,\"balance\":3457}');\r\n");
}

@And("a Buyer is on the ItemPage")
public void a_buyer_is_on_the_item_page() {
	driver.get("http://localhost:8081/ItemPage.html");
}

@When("the Buyer clicks the CartButton")
public void the_buyer_clicks_the_cart_button() {
	ip.pageAddButton.click();
	ip.toCartButton.click();
	
}

@Then("The Element should be added to the Cart")
public void the_element_should_be_added_to_the_cart() {
	assertNotEquals(clp.cartBody, null);
}


@And("a Buyer is on the CartListPage")
public void a_buyer_is_on_the_cart_list_page() {
	driver.get("http://localhost:8081/CartListPage");
}

@When("the Buyer clicks the RemoveCartButton")
public void the_buyer_clicks_the_remove_cart_button() {
	clp.pageDeleteButton.click();
}

@Then("The Item should be removed from the Buyer Cart")
public void the_item_should_be_removed_from_the_buyer_cart() {
	assertEquals(clp.cartBody, null);
}

}
