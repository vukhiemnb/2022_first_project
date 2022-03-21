package stepDefinitions;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
	public WebDriver driver;
	@Before
	public void Declare_driver() {
		WebDriverManager.chromedriver().setup();
	}

}
