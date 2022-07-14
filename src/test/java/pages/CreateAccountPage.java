package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage {
    private WebDriver driver;

    public CreateAccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "un")
    public WebElement createUsername;

    @FindBy(id = "pw")
    public WebElement createPassword;

    @FindBy(id = "name")
    public WebElement createName;

    @FindBy(id = "create")
    public WebElement createAccountButton;

    @FindBy(id = "accType")
    public WebElement createAccType;

}
