package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	private WebDriver driver;
	public static JavascriptExecutor js;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}

//-------------HomePage FindBys ------------------------------------
	@FindBy(id="balanceInput")
	public WebElement balanceInput;

	@FindBy(xpath="//*[@id=\"addBalance\"]/button")
	public WebElement addBalanceBtn;

	@FindBy(xpath="//*[@id=\"newBalanceAlert\"]")
	public WebElement addBalanceSuccess;

	@FindBy(xpath="/html/body/container[3]/button[1]")
	public WebElement browseProductsBtn;

	@FindBy(xpath="/html/body/container[3]/button[2]")
	public WebElement submitSupportTicketBtn;
	
	@FindBy(xpath="//*[@id=\"newBalanceAlert\"]/p[1]")
	public WebElement balAlert;
	
	@FindBy(id="allTickets")
	public WebElement allTickBtn;
	
	@FindBy(id="supportTicketTable")
	public WebElement ticketTable;
	
	@FindBy(id="logoutButton")
	public WebElement logoutBtn;


}
