package stepDefinitions;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AdminTicketPage;

public class AdminStepsNC {
	public static WebDriver driver;
	public static AdminTicketPage atp;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@BeforeAll
	public static void setup(){
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		atp = new AdminTicketPage(driver);
		driver = new ChromeDriver();

	}

	@AfterAll
	public static void teardown(){
		driver.quit();
	}
	
//-----------------------------Admin Ticket Steps -------------------------------------
	
	@Given("Admin account is loaded")
	public void admin_account_is_loaded() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Given("Admin is on view tickets page")
	public void admin_is_on_view_tickets_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@When("Admin selects single ticket")
	public void admin_selects_single_ticket() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("they are taken to that ticket page")
	public void they_are_taken_to_that_ticket_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
}
