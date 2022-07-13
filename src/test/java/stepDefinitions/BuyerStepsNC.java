package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartListPage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.ItemPage;
import pages.LoginPage;
import pages.NewTicketPage;

public class BuyerStepsNC {
	public static WebDriver driver;
	public static HomePage hp;
	public static CartListPage cartListPage;
	public static ItemPage ip;
	public static CreateAccountPage cap;
	public static LoginPage lp;
	public static NewTicketPage ntp;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@BeforeAll
	public static void setup(){
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();
		hp = new HomePage(driver);
		cap = new CreateAccountPage(driver);
		lp = new LoginPage(driver);
		cartListPage = new CartListPage(driver);
		ip = new ItemPage(driver);
		ntp = new NewTicketPage(driver);

	}
	@AfterAll
	public static void teardown(){
		driver.quit();
	}

//---------------------------------- General Steps --------------------------------------------
	@Given("a buyer is on the home page")
	public void a_buyer_is_on_the_home_page() {
		//driver gets home page
	    driver.get("http://localhost:8081/homePage.html");
	}
	
	@Given("test buyer account is loaded")
	public void test_buyer_account_is_loaded() {
		//test buyer account is directly put into the session storage (josh)
	    js.executeScript("sessionStorage.setItem('activeUser', '{\"username\":\"josh\",\"password\":\"josh\",\"id\":1,\"name\":\"josh\",\"accountType\":1,\"balance\":3457}');\r\n");
	}

// --------------------------------- Submit New Ticket ----------------------------------------

	@Given("a buyer is on the submit new ticket page")
	public void a_buyer_is_on_the_submit_new_ticket_page() {
	    driver.get("http://localhost:8081/newTicket.html");
	}
	@When("the buyer enters fields and presses submit")
	public void the_buyer_enters_fields_and_presses_submit() {
	    ntp.cNTSubj.sendKeys("Cucumber Test Ticket Subject");
	    ntp.cNTDesc.sendKeys("Cucumber Test Ticket Description");
	    ntp.cNTSubmitBtn.click();
	}
	@Then("buyer is redirected to view ticket")
	public void buyer_is_redirected_to_view_ticket() {
		new WebDriverWait(driver, Duration.ofSeconds(10))
		.until(ExpectedConditions.titleContains("View Ticket"));

		assertEquals("View Ticket", driver.getTitle());
	}
	@Then("the ticket in question is displayed")
	public void the_ticket_in_question_is_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    assertTrue(ntp.ticketText.isDisplayed());
	}
	
	@When("the buyer enters nothing and presses submit")
	public void the_buyer_enters_nothing_and_presses_submit() {
	    ntp.cNTSubmitBtn.click();
	}
	@Then("an error message should appear")
	public void an_error_message_should_appear() {
	    assertTrue(ntp.errormsg.isDisplayed());
	}
//------------------------------- View All Tickets Feature --------------------------------
	@When("the buyer clicks view all tickets")
	public void the_buyer_clicks_view_all_tickets() {
	    hp.allTickBtn.click();
	}
	@Then("they are redirected and can see their tickets")
	public void they_are_redirected_and_can_see_their_tickets() {
		new WebDriverWait(driver, Duration.ofSeconds(5))
		.until(ExpectedConditions.visibilityOf(hp.ticketTable));
		
		assertTrue(hp.ticketTable.isDisplayed());
	}
	
// ------------------------------ Browse Items Feature ------------------------------------
	

	@When("the buyer clicks the browse store button")
	public void the_buyer_clicks_the_browse_store_button() {
		hp.browseProductsBtn.click();
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
	
// ------------------------------ Add Balance Feature --------------------------------------
	
	@When("the buyer enters number and adds balance")
	public void the_buyer_enters_number_and_adds_balance() {
	    hp.balanceInput.sendKeys("500");
	    hp.addBalanceBtn.click();
	}
	@Then("balance added message appears")
	public void balance_added_message_appears() {
		new WebDriverWait(driver, Duration.ofSeconds(5))
		.until(ExpectedConditions.visibilityOf(hp.balAlert));
		
		assertTrue(hp.balAlert.isDisplayed());
	}
	
// ------------------------------ Logout Feature --------------------------------------------
	
	@When("the buyer presses logout")
	public void the_buyer_presses_logout() {
		hp.logoutBtn.click();
	}
	@Then("the user is logged out")
	public void the_user_is_logged_out() {
		new WebDriverWait(driver, Duration.ofSeconds(5))
		.until(ExpectedConditions.titleContains("Achieve Login"));
		
		assertEquals("Achieve Login", driver.getTitle());
	}
}
