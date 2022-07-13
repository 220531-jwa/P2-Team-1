package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SellerItemPage {
	private WebDriver driver;
	public static JavascriptExecutor js;

	public SellerItemPage(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="//*[@id=\"sellerData\"]/li[1]")
	public WebElement sellerData;
	
	@FindBy(id="viewButt0")
	public WebElement viewItemBtn;
	
	@FindBy(id="itemTable")
	public WebElement itemTable;
	
	@FindBy(id="butt")
	public WebElement editBtn;
	
	@FindBy(id="newCost")
	public WebElement costInput;
	
	@FindBy(xpath="//*[@id=\"editsTable\"]/tbody/tr[5]/td/button")
	public WebElement submitbtn;
	
	@FindBy(xpath="//*[@id=\"itemTable\"]/tbody/tr[3]/td")
	public WebElement currentCost;
	
}
