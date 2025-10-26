package pageEvents;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.AbstractClass;

public class SalesPageEvents {
	WebDriver driver;
	JavascriptExecutor jse;
	Actions a;
	AbstractClass waitClass;

	public SalesPageEvents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor) driver;
		 a = new Actions(driver);
		 waitClass = new AbstractClass(driver);

	}

	@FindBy(xpath = "//div[@role='navigation']/ul/li //img[@src = 'https://saas-fun-1199.my.salesforce.com/logos/Salesforce/EasySales/logo.png?v=254.4']")
	WebElement salesButton;

	@FindBy(xpath = "//one-app-nav-bar-item-root/a/span[text()='Accounts']")
	WebElement accountButton;

	@FindBy(xpath = "//div[@title='New']")
	WebElement newButton;

	@FindBy(xpath = "//input[@name ='Name']")
	WebElement accountName;

	@FindBy(xpath = "//input[@name ='Website']")
	WebElement webSite;

	@FindBy(xpath = "//button[@aria-label = 'Type']")
	WebElement type;

//    @FindBy(xpath = "//div[@aria-label='Type']")
//    List<WebElement> typeList;
//    
//    @FindBy(xpath = "//lightning-base-combobox-item[@data-value='Competitor']")
//    WebElement typeValue;

	@FindBy(xpath = "//label[text()='Description']/following-sibling::div/textarea")
	WebElement descrption;
	@FindBy(xpath ="//input[@name='Phone']")
	WebElement phone;
	@FindBy(xpath = "//div/label[text()='Billing Country']/following-sibling::div/lightning-base-combobox")
	WebElement billingCountry;
	
	@FindBy(xpath = "//div[@aria-label='Billing Country'] //lightning-base-combobox-item/span/span")
	List<WebElement> billingCountryList;
	@FindBy(xpath = "//label[text()='Billing Street']/following-sibling::div/textarea")
	WebElement billingStreet;
	@FindBy(xpath = "//label[text()='Billing City']/parent::div/div/input")
	WebElement billingCity;
	@FindBy( xpath = "//label[text()='Billing Zip/Postal Code']/parent::div/div/input")
	WebElement billingPostalCode;
	@FindBy(xpath ="//label[text()='Billing State/Province']/parent::div/div/lightning-base-combobox")
	WebElement billingState;
	@FindBy(xpath ="//label[text()='Billing State/Province']/parent::div/div/lightning-base-combobox/div/div/div[2]/lightning-base-combobox-item/span[2]")
    List<WebElement> billingStateList;
	
	@FindBy(xpath = "//div/label[text()='Shipping Country']/following-sibling::div/lightning-base-combobox")
	WebElement shippingCountry;
	@FindBy(xpath = "//div[@aria-label='Shipping Country'] //lightning-base-combobox-item/span/span")
	List<WebElement> shippingCountryList;
	@FindBy(xpath = "//label[text()='Shipping Street']/following-sibling::div/textarea")
	WebElement shippingStreet;
	@FindBy(xpath = "//label[text()='Shipping City']/parent::div/div/input")
	WebElement shippingCity;
	@FindBy( xpath = "//label[text()='Shipping Zip/Postal Code']/parent::div/div/input")
	WebElement shippingPostalCode;
	@FindBy(xpath ="//label[text()='Shipping State/Province']/parent::div/div/lightning-base-combobox")
	WebElement shippingState;

	@FindBy(xpath = "//button[@name='SaveEdit']")
	WebElement saveButton;
	
	@FindBy(xpath = "//span[text()='Account Name']")
	WebElement accountName1;
	
	/////

	public void createAccount(HashMap<String, String> data) {
		waitClass.waitUntillElementisClickable(accountButton);
		
		jse.executeScript("arguments[0].click();", accountButton);
		// accountButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitClass.waitUntillVisibilityOfElement(accountName1);
		newButton.click();
		accountName.sendKeys(data.get("AccountName"));
		webSite.sendKeys(data.get("WebSite"));
		type.click();
		String s2 = data.get("Type");
		driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='" + s2 + "']")).click();
		descrption.sendKeys(data.get("Description"));
		phone.sendKeys(data.get("Phone"));
		billingCountry.click();
		for(WebElement e : billingCountryList)
		{
			if(e.getText().equalsIgnoreCase(data.get("Billing Country")))
			{
				//a.scrollToElement(e);
				jse.executeScript("arguments[0].scrollIntoView(true)", e);
				e.click();
				break;
			}
			
		}
		billingStreet.sendKeys(data.get("Billing Street"));
		billingCity.sendKeys(data.get("Billing City"));
		billingPostalCode.sendKeys(data.get("BillingZipCode"));
		billingState.click();
		for(WebElement e :billingStateList)
		{
			if(e.getText().equalsIgnoreCase(data.get("BillingState")))
			{
				jse.executeScript("arguments[0].scrollIntoView(true)", e);
				e.click();
				break;
			}
		}
		shippingCountry.click();
		for(WebElement e :shippingCountryList)
		{
			if(e.getText().equalsIgnoreCase(data.get("ShippingCountry")))
			{
				jse.executeScript("arguments[0].scrollIntoView(true)", e);
				e.click();
				break;
			}
		}
		shippingStreet.sendKeys(data.get("Shipping Street"));
		shippingCity.sendKeys(data.get("Shipping City"));
		shippingPostalCode.sendKeys(data.get("ShippingZipCode"));
		saveButton.click();
		waitClass.waitUntillElementisClickable(accountButton);
		jse.executeScript("arguments[0].click();", accountButton);
		
	}

}
