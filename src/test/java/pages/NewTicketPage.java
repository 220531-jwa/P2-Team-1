package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewTicketPage {
	private WebDriver driver;
	public static JavascriptExecutor js;

	public NewTicketPage(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}

	//------------Create Ticket Page FindBys ----------------------------

		@FindBy(id="subj")
		public WebElement cNTSubj;

		@FindBy(id="desc")
		public WebElement cNTDesc;

		@FindBy(xpath="/html/body/button")
		public WebElement cNTSubmitBtn;

		@FindBy(xpath="/html/body/p")
		public WebElement ticketText;
		
		@FindBy(id="errormsg")
		public WebElement errormsg;
}
