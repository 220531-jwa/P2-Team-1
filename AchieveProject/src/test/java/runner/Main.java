package runner;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.Suite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.createAccountPage;

import java.io.File;
@Suite
@Tag("CucumberTests")
@IncludeTags("CucumberTests")
public class Main {
    public static WebDriver driver;
    public static createAccountPage createAccountPage;
    @BeforeAll
    public static void setup(){
        File chrome = new File("src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
        driver = new ChromeDriver();
        createAccountPage = new createAccountPage(driver);
    }

    @AfterAll
    public static void teardown(){
        driver.quit();
    }
}
