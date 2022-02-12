package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
	WebDriver driver;
	
	public Login(WebDriver driver) {
		this.driver = driver;
	}
	
	By username_field	= By.id("username");
	By password_field	= By.id("password");
	By button_login		= By.className("Login_button__1Lhkh");
	
	public void login_with_credential(String username, String password) {
		driver.findElement(username_field).clear();
		driver.findElement(username_field).sendKeys(username);
		driver.findElement(password_field).clear();
		driver.findElement(password_field).sendKeys(password);
		driver.findElement(button_login).click();
	}
	
}
