package stepDefinitions;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	driver.get("http://localhost:8081/homePage.html");
	 js.executeScript("sessionStorage.setItem('activeUser', '{\"username\":\"josh\",\"password\":\"josh\",\"id\":1,\"name\":\"josh\",\"accountType\":1,\"balance\":3457}');\r\n");
}

@Given("a Buyer is on the ItemPage")
public void a_buyer_is_on_the_item_page() {
	driver.get("http://localhost:8081/ItemPage.html");
}

@When("the Buyer clicks the CartButton")
public void the_buyer_clicks_the_cart_button() {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.elementToBeClickable(By.id("AddButt0")));

	ip.pageAddButton.click();
	ip.toCartButton.click();

}

@Then("The Element should be added to the Cart")
public void the_element_should_be_added_to_the_cart() {
	assertNotEquals(clp.cartBody, null);
}


@Given("An Item exists in the Cart")
public void an_item_exists_in_the_cart() {
	driver.get("http://localhost:8081/ItemPage.html");

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.elementToBeClickable(By.id("AddButt0")));
	ip.pageAddButton.click();

}
@Given("the buyer is on the CartListPage")
public void the_buyer_is_on_the_cart_list_page() {
	ip.toCartButton.click();
}

@When("the Buyer clicks on the RemoveCartButton by an Item")
public void the_buyer_clicks_on_the_remove_cart_button_by_an_item() {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.elementToBeClickable(By.id("RemoveButt0")));
	WebElement pageDeleteButton = driver.findElement(By.id("RemoveButt0"));
	pageDeleteButton.click();

	clp.pageDeleteButton.click();
}

@Then("The Item should be removed from the Buyer Cart")
public void the_item_should_be_removed_from_the_buyer_cart() throws InterruptedException {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.wait(5000);
	WebElement cartBody = driver.findElement(By.id("CartBody"));
	assertEquals(cartBody, null);
}

}
