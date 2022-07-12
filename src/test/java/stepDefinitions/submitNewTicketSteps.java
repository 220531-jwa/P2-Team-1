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
import pages.BuyerPage;
import pages.CartListPage;
import pages.CreateAccountPage;
import pages.ItemPage;
import pages.LoginPage;
import pages.NewTicketPage;

public class submitNewTicketSteps {
	public static WebDriver driver;
	public static BuyerPage bp;
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
		bp = new BuyerPage(driver);
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
}
