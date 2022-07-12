package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BuyerPage;
import pages.CartListPage;
import pages.CreateAccountPage;
import pages.ItemPage;
import pages.LoginPage;

public class BuyerItemListSteps {
	
	public static WebDriver driver;
	public static BuyerPage bp;
	public static CartListPage cartListPage;
	public static ItemPage ip;
	public static CreateAccountPage cap;
	public static LoginPage lp;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@BeforeAll
	public static void setup(){
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();
		bp = new BuyerPage(driver);
		cap = new CreateAccountPage(driver);
		lp = new LoginPage(driver);
		cartListPage = new CartListPage(driver);
		ip = new ItemPage(driver);
	}

	@AfterAll
	public static void teardown(){
		driver.quit();
	}
	
	@Given("test buyer account is loaded")
	public void test_buyer_account_is_loaded() {
		//test buyer account is directly put into the session storage
	    js.executeScript("sessionStorage.setItem('activeUser', '{\"username\":\"josh\",\"password\":\"josh\",\"id\":1,\"name\":\"josh\",\"accountType\":1,\"balance\":3457}');\r\n");
	}
	
	@Given("a buyer is on the home page")
	public void a_buyer_is_on_the_home_page() {
		//driver gets home page
	    driver.get("http://localhost:8081/homePage.html");
	}
	
	@When("the buyer clicks the browse store button")
	public void the_buyer_clicks_the_browse_store_button() {
		bp.browseProductsBtn.click();
	}
	@Then("The buyer is redirected and can see the item catalogue")
	public void the_buyer_is_redirected_and_can_see_the_item_catalogue() {
		new WebDriverWait(driver, Duration.ofSeconds(5))
		.until(ExpectedConditions.titleContains("Item Page"));
		
		assertEquals("Item Page", driver.getTitle());
	}
	@Then("the products table is populated")
	public void the_products_table_is_populated() {
	    assertTrue(ip.itemData.isDisplayed());
	}
}
