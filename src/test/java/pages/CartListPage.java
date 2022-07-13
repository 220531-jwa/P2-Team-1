package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartListPage {
	private WebDriver driver;

	public CartListPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


	@FindBy(id = "RemoveButt") //add in proper xpath for the button
	public WebElement pageDeleteButton;
	@FindBy(id = "checkout")
	public WebElement checkoutButton;
	@FindBy(id="CartBody")
	public WebElement cartBody;
}
