package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class CreateItemPage {
    private WebDriver driver;

    public CreateItemPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "gameName")
    public WebElement createItemName;

    @FindBy(id = "desc")
    public WebElement createItemDesc;

    @FindBy(id = "cost")
    public WebElement createItemCost;

    @FindBy(id = "inventory")
    public WebElement createItemInventory;

    @FindBy(id = "submit")
    public WebElement createItemSubmitBtn;
    @FindBy(id = "message")
    public WebElement createItemMessage;
}
