package runner;

import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.Suite;
import org.openqa.selenium.WebDriver;

import pages.BuyerPage;

@Suite
@Tag("CucumberTests")
public class AchieveTestSuite {
	
	public static WebDriver driver;
	public static BuyerPage bp;
}
