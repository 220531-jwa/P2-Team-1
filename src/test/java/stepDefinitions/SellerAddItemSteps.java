package stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CreateItemPage;
import pages.LoginPage;
import pages.SellerItemPage;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SellerAddItemSteps {
    public static WebDriver driver;

    public static CreateItemPage cip;
    public static LoginPage lp;
    JavascriptExecutor js = (JavascriptExecutor) driver;


    @BeforeAll
    public static void setup(){
        File chrome = new File("src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
        driver = new ChromeDriver();
        cip = new CreateItemPage(driver);
        lp = new LoginPage(driver);


    }

    @AfterAll
    public static void teardown(){
        driver.quit();
    }

    @Given("a Seller is logged in and is on the Add an Item page")
    public void a_seller_is_logged_in_and_is_on_the_add_an_item_page() {
        driver.get("http://localhost:8081/home.html");
        js.executeScript("sessionStorage.setItem('activeUser', '{\"username\":\"tinendo\",\"password\":\"tinendo\",\"id\":2,\"name\":\"tinendo\",\"accountType\":2,\"balance\":0}');\r\n");
        driver.get("http://localhost:8081/sellerAddItem.html");
    }
    @When("the Seller enters the {string}, {string}, {string}, and {string} and clicks Submit")
    public void the_seller_enters_the_and_and_clicks_submit(String name, String description, String cost, String inventory) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("gameName")));
        cip.createItemName.sendKeys(name);
        cip.createItemDesc.sendKeys(description);
        cip.createItemCost.sendKeys(cost);
        cip.createItemInventory.sendKeys(inventory);
        cip.createItemSubmitBtn.click();
        
        new WebDriverWait(driver, Duration.ofSeconds(10))
    	.until(ExpectedConditions.titleContains("Item View"));
    }
    @Then("the Seller will see the Item Created message")
    public void the_seller_will_see_the_item_created_message() {
    	
        assertEquals("Item View", driver.getTitle());
    }
    
    @Then("the seller will delete the made item for good measure")
    public void the_seller_will_delete_the_made_item_for_good_measure() {
    	
    	
    	WebElement editBtn = driver.findElement(By.id("butt"));
    	editBtn.click();
    	
    	new WebDriverWait(driver, Duration.ofSeconds(10))
    	.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("deletebtn")));
    	
    	WebElement deletebtn = driver.findElement(By.id("deletebtn"));
    	deletebtn.click();
    	
    	new WebDriverWait(driver, Duration.ofSeconds(10))
    	.until(ExpectedConditions.elementToBeClickable(By.id("realDelete")));
    	
    	WebElement realdeletebtn = driver.findElement(By.id("realDelete"));
    	realdeletebtn.click();
    	
    	new WebDriverWait(driver, Duration.ofSeconds(10))
    	.until(ExpectedConditions.titleContains("Item Page"));
    	
    	assertEquals("Item Page", driver.getTitle());
    	
    	
    }
}
