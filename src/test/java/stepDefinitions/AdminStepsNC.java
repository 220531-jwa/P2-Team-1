package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
//-----------------------------Admin General Steps ------------------------------------

	@Given("Admin account is loaded")
	public void admin_account_is_loaded() {
	    driver.get("http://localhost:8081/homePage.html");
	    js.executeScript("sessionStorage.setItem('activeUser', '{\"username\":\"admin\",\"password\":\"admin\",\"id\":3,\"name\":\"admin\",\"accountType\":3,\"balance\":0}');\r\n");

	}


//-----------------------------Admin Ticket Steps -------------------------------------


	@Given("Admin is on view tickets page")
	public void admin_is_on_view_tickets_page() {
	    driver.get("http://localhost:8081/adminAllTickets.html");
	}
	@When("Admin selects single ticket")
	public void admin_selects_single_ticket() {
		new WebDriverWait(driver, Duration.ofSeconds(4))
		.until(ExpectedConditions.visibilityOfElementLocated(By.id("id1")));

	    WebElement tickID = driver.findElement(By.id("id1"));
	    tickID.click();

	}
	@Then("they are taken to that ticket page")
	public void they_are_taken_to_that_ticket_page() {
		new WebDriverWait(driver, Duration.ofSeconds(4))
		.until(ExpectedConditions.titleContains("Admin Ticket Response"));


		assertEquals("Admin Ticket Response", driver.getTitle());
	}

	@When("Admin changes status")
	public void admin_changes_status() {
		new WebDriverWait(driver, Duration.ofSeconds(4))
		.until(ExpectedConditions.visibilityOfElementLocated(By.id("newStatus")));

		WebElement selector = driver.findElement(By.id("newStatus"));

		Select stat = new Select(selector);
		stat.selectByVisibleText("Resolved");

		WebElement statusUpdate = driver.findElement(By.id("statusBtn"));
		statusUpdate.click();

	}
	@Then("the new status is displayed")
	public void the_new_status_is_displayed() {

		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
		String alertMessage = driver.switchTo().alert().getText();
        assertEquals("Ticket has been updated successfully!", alertMessage);

	}
}
