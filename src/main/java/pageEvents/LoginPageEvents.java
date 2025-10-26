package pageEvents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageEvents {
	WebDriver driver;
	public LoginPageEvents(WebDriver driver)
	{
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='username']")
	WebElement userName;
	
	@FindBy(xpath = "//input[@id='password']")
	WebElement password;
	
	@FindBy(xpath = "//input[@id='Login']")
	WebElement loginButton;


	public void login(String name, String passkey)
	{
		userName.sendKeys(name);
		password.sendKeys(passkey);
		loginButton.click();
	}

}
