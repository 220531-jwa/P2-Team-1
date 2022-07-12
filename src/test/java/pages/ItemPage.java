
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage {

	private WebDriver driver;
	
	public ItemPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "/html/body/div/ul/button[1]") //add in proper xpath for the button
	public WebElement pageAddButton;
	
	@FindBy(id = "itemData")
	public WebElement itemData;

	@FindBy(id = "toCart")
	public WebElement toCartButton;
	
}