package runner;

import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.Suite;
import org.openqa.selenium.WebDriver;

import pages.BuyerPage;
import pages.CartListPage;
import pages.ItemPage;

@Suite
@Tag("CucumberTests")
public class AchieveTestSuite {
	
	public static WebDriver driver;
	public static BuyerPage bp;
	public static CartListPage cartListPage;
	public static ItemPage itemPage;
}
