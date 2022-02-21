package stepDefinitions;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.Test_accounts;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Login;

public class Login_steps {
	public static WebDriver driver;
	
	@Given("Open Browser")
	public void open_browser() {
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	}

	@Given("Access Login page")
	public void access_login_page() {
	    driver.get(data.Given_variables.login_page);
	}

	@When("Enter valid credential on login page")
	public void enter_valid_credential_on_login_page() {
	    Login init_login	= new Login(driver);
	    init_login.login_with_credential(Test_accounts.admin_username, Test_accounts.admin_password);
	}

	@When("Click button login on login page")
	public void click_button_login_on_login_page() {
		Login init_login	= new Login(driver);
		init_login.click_button_login();
	}

	@Then("Login successfully")
	public void login_successfully_and_redirect_to_home_page() {
	    
	}
	
	@Then("redirect to home page")
	public void redirect_to_home_page() {
	    WebDriverWait wait_redirect_to_login_page	= new WebDriverWait(driver,Duration.ofSeconds(60));
	    String current_url;
	    try {
		    wait_redirect_to_login_page.until(ExpectedConditions.urlMatches(data.Given_variables.homepage_url));
		    current_url	= driver.getCurrentUrl();
		} catch (Exception e) {
			current_url = "null";
		}
	    Assert.assertEquals(current_url,data.Given_variables.homepage_url);
	}
	
	@When("^Enter username as \"(.*)\" and password as \"(.*)\"")
	public void enter_username_as_and_password_as(String username_input, String password_input) {
		Login call_login	= new Login(driver);
		call_login.login_with_credential(username_input, password_input);
	}

	@Then("^Error message with content \"(.*)\" is displayed")
	public void error_message_with_content_is_displayed(String msg_input) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    int count_message = driver.findElements(By.xpath("//div[contains(text(),'"+msg_input+"')]")).size();
	    System.out.println("msg_input: "+msg_input);
	    System.out.println("count_message: " + count_message);
	    Assert.assertTrue(count_message>0);
	}
	
	@Then("^Take screenshot with name as \"(.*)\"")
	public void take_snap_shot_with_name_as(String name) throws Exception {
		Thread.sleep(1000);
		Reusable_functions.take_screenshot(driver, "D:\\Auto Test\\test images", name);		
	}
	
	@After
	public void close_window() throws Exception {
		driver.quit();
	}
}
