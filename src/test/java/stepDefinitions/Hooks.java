package stepDefinitions;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.Before;

public class Hooks {
	public WebDriver driver;
	@Before
	public void Declare_driver() {
		System.setProperty("webdriver.chrome.driver", data.Configure_data.browser_driver_path);
	}

}
