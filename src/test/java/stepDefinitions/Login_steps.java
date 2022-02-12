package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import data.Test_accounts;
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
	    
	}

	@Then("Login successfully and redirect to home page")
	public void login_successfully_and_redirect_to_home_page() {
	    
	}
}
