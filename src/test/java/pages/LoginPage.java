package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "un")
    public WebElement loginUser;
    @FindBy(id = "pw")
    public WebElement loginPass;
    @FindBy(id = "login")
    public WebElement loginButton;

}
