package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.Duration;
import java.util.Random;

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
import pages.SellerItemPage;

public class SellerStepsNC {
	public static WebDriver driver;
	public static SellerItemPage sip;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@BeforeAll
	public static void setup(){
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		sip = new SellerItemPage(driver);
		driver = new ChromeDriver();

	}

	@AfterAll
	public static void teardown(){
		driver.quit();
	}
//------------------------------ General Seller Steps -----------------------------------------

	@Given("A test seller account is loaded")
	public void a_test_seller_account_is_loaded() {
		driver.get("http://localhost:8081/home.html");
		js.executeScript("sessionStorage.setItem('activeUser', '{\"username\":\"tinendo\",\"password\":\"tinendo\",\"id\":2,\"name\":\"tinendo\",\"accountType\":2,\"balance\":0}');\r\n");
	}

//------------------------------ Seller Inventory feature -------------------------------------

	@Given("seller is on the seller inventory page")
	public void seller_is_on_the_seller_inventory_page() {
	    driver.get("http://localhost:8081/sellerInventory.html");
	}

	@Then("seller inventory is displayed")
	public void seller_inventory_is_displayed() {
		new WebDriverWait(driver, Duration.ofSeconds(4))
		.until(ExpectedConditions.titleContains("Item Page"));
		assertEquals("Item Page", driver.getTitle());
	}

	@When("the seller selects item to view")
	public void the_seller_selects_item_to_view() {
		
		new WebDriverWait(driver, Duration.ofSeconds(4))
		.until(ExpectedConditions.visibilityOfElementLocated(By.id("viewButt0")));

		WebElement viewItemBtn = driver.findElement(By.id("viewButt0"));
		viewItemBtn.click();

	}

	@Then("seller can see item information")
	public void seller_can_see_item_information() {
		new WebDriverWait(driver, Duration.ofSeconds(4))
		.until(ExpectedConditions.visibilityOfElementLocated(By.id("itemTable")));

		WebElement itemTable = driver.findElement(By.id("itemTable"));
		assertTrue(itemTable.isDisplayed());
	}

	String randomNum;

	@When("edits the item and submits")
	public void edits_the_item_and_submits() {
		Random rand = new Random();
		int upperbound = 60;
		int random = rand.nextInt(upperbound);
		randomNum = String.valueOf(random);

		WebElement editBtn = driver.findElement(By.id("butt"));
		WebElement costInput = driver.findElement(By.id("newCost"));
		WebElement submitbtn = driver.findElement(By.id("submitbtn"));

	    editBtn.click();
	    
	    costInput.clear();
	    costInput.sendKeys(randomNum);
	    submitbtn.click();
		
	}

	@Then("the edited value is now visible")
	public void the_edited_value_is_now_visible() {
		new WebDriverWait(driver, Duration.ofSeconds(4))
		.until(ExpectedConditions.alertIsPresent());
		
		String alertMessage = driver.switchTo().alert().getText();
        assertEquals("Update Success!", alertMessage);
	}
}
