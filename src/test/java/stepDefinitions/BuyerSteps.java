<<<<<<< HEAD
package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BuyerPage;
import runner.AchieveTestSuite;

public class BuyerSteps {
	
	public static WebDriver driver = AchieveTestSuite.driver;
	public static BuyerPage bp = AchieveTestSuite.bp;
	
	@BeforeAll
	public static void setup() {
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();
		bp = new BuyerPage(driver);
	}
	
	@AfterAll
	public static void teardown() {
//		driver.quit();
	}
	
//----------GENERAL TEST ACCOUNT LOADING STEP ------------------------------------
	
	@Given("test buyer account is loaded")
	public void test_buyer_account_is_loaded() {
	    driver.get("http://localhost:8081/homePage.html");
	    bp.js.executeScript("sessionStorage.setItem('activeUser' , JSON.stringify({\"id\":1,\"username\":\"josh\",\"password\":\"josh\",\"name\":\"josh\",\"accountType\":\"1\",\"balance\":\"0\"}) );");
	    driver.navigate().refresh();
	}
	
//--------------------Home Page Functionality Steps ------------------------------
	

	@Given("a buyer is on the home page")
	public void a_buyer_is_on_the_home_page() {
	    driver.navigate().to("http://localhost:8081/homePage.html");
	}

	@When("the buyer clicks the browse store button")
	public void the_buyer_clicks_the_browse_store_button() {
		bp.browseProductsBtn.click();
	}

	@Then("The buyer is redirected and can see the item catalogue")
	public void the_buyer_is_redirected_and_can_see_the_item_catalogue() {
	    
		new WebDriverWait(driver, Duration.ofSeconds(10))
		.until(ExpectedConditions.titleContains("Product Page"));
		
		assertEquals("Product Page", driver.getTitle());
	}

	@When("the buyer adds to their balance")
	public void the_buyer_adds_to_their_balance() {
		bp.balanceInput.sendKeys("100");
	    bp.addBalanceBtn.click();
	}

	@Then("the new balance should be displayed")
	public void the_new_balance_should_be_displayed() {
		
		new WebDriverWait(driver, Duration.ofSeconds(10))
		.until(ExpectedConditions.visibilityOf(bp.addBalanceSuccess));
		
	    assertTrue(bp.addBalanceSuccess.isDisplayed());
	}
	
	@When("the buyer presses submit support ticket")
	public void the_buyer_presses_submit_support_ticket() {
	    bp.submitSupportTicketBtn.click();
	}

	@Then("buyer is redirected to submit ticket page")
	public void buyer_is_redirected_to_submit_ticket_page() {
		new WebDriverWait(driver, Duration.ofSeconds(10))
		.until(ExpectedConditions.titleContains("New Ticket"));
		
		assertEquals("New Ticket", driver.getTitle());
	}
}
=======
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
	
}

@When("the Buyer clicks the CartButton")
public void the_buyer_clicks_the_cart_button() {
	
}

@Then("The Element should be added to the Cart")
public void the_element_should_be_added_to_the_cart() {
	
}


@Given("a Buyer is on the CartListPage")
public void a_buyer_is_on_the_cart_list_page() {
	
}

@When("the Buyer clicks the RemoveCartButton")
public void the_buyer_clicks_the_remove_cart_button() {
	
}

@Then("The Item should be removed from the Buyer Cart")
public void the_item_should_be_removed_from_the_buyer_cart() {
	
}

}
>>>>>>> origin/trevor_rowand
