package runner;
import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.Suite;
import org.openqa.selenium.WebDriver;

import pages.ItemPage;
import pages.CartListPage;
@Suite
@Tag("CucumberTests")
public class CartRunner {
	public static WebDriver driver;
	public static CartListPage cartListPage;
	public static ItemPage itemPage;
}
