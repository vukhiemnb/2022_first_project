package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	@Before
	public void Declare_driver() {
		System.setProperty("webdriver.chrome.driver", data.Configure_data.browser_driver_path);
	}

//	@After
}
