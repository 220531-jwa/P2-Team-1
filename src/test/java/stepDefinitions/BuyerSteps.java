package stepDefinitions;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
		driver.quit();
	}
	
//----------GENERAL TEST ACCOUNT LOADING STEP ------------------------------------
	
	@Given("test buyer account is loaded")
	public void test_buyer_account_is_loaded() {
	    //TODO WRITE CODE FOR JS EXECUTOR TO STORE SESSION OF JOSH
	}
	
//--------------------Home Page Functionality Steps ------------------------------
	

	@Given("a buyer is on the home page")
	public void a_buyer_is_on_the_home_page() {
	    driver.get("http://localhost:8081/homePage.html");
	}

	@When("the buyer clicks the browse store button")
	public void the_buyer_clicks_the_browse_store_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("The buyer is redirected and can see the item catalogue")
	public void the_buyer_is_redirected_and_can_see_the_item_catalogue() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the buyer adds to their balance")
	public void the_buyer_adds_to_their_balance() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the new balance should be displayed")
	public void the_new_balance_should_be_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
