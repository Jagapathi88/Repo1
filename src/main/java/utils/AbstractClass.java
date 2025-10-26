package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractClass {
	
	WebDriverWait wait;
	public AbstractClass(WebDriver driver)
	{
	
		 wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void waitUntillElementisClickable(WebElement ele)
	{
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	public void waitUntillVisibilityOfElement(WebElement ele)
	{
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

}
