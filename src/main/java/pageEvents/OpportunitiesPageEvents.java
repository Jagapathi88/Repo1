package pageEvents;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.AbstractClass;

public class OpportunitiesPageEvents {
	
	WebDriver driver;
	JavascriptExecutor jse;
	AbstractClass waitClass;
	public OpportunitiesPageEvents(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		jse = (JavascriptExecutor) driver;
		waitClass = new AbstractClass(driver);
	}

	
	@FindBy( xpath = "//a/span[text()='Opportunities']")
	WebElement opportunity;
	
	@FindBy(xpath = "//div[text()='New']")
	WebElement newButton;
	
	@FindBy( xpath = "//input[@name ='Name']")
	WebElement opportunityName;
	
	@FindBy( xpath = "//input[contains(@placeholder,'Search Accounts')]")
	WebElement accountName;
	
	@FindBy(xpath ="//div/ul[@role ='group']/li/lightning-base-combobox-item/span[2]/span/span")
	List<WebElement> requiredAccountName;
	
	@FindBy(xpath = "//input[@name='CloseDate']")
	WebElement closeDate;
	@FindBy(xpath = "//div[@part='overlay dropdown calendar']/lightning-focus-trap/slot/div/div/h2")
	WebElement monthName;
	@FindBy(xpath = "//div[@part='overlay dropdown calendar']/lightning-focus-trap/slot/div/div/div[1]")
	WebElement leftArrow;
	@FindBy(xpath = "//div[@part='overlay dropdown calendar']/lightning-focus-trap/slot/div/div/div[2]")
	WebElement rightArrow;
	@FindBy(xpath = "//div[@part='overlay dropdown calendar']/lightning-focus-trap/slot/div/div[2]")
	WebElement year;
	@FindBy(xpath = "//div[@part='overlay dropdown calendar']/lightning-focus-trap/slot/div/div[2]/lightning-select/div/div/select/option")
	List<WebElement> listYears;
	@FindBy(xpath = "//div[@part='overlay dropdown calendar']/lightning-focus-trap/slot/table/tbody/tr/td/span")
	List<WebElement> listOfDates;
	@FindBy(xpath = "//input[@name = 'Amount']")
	WebElement amount;
	@FindBy(xpath = "//label[text()='Description']/parent::lightning-textarea //div/textarea")
	WebElement descrption;
	@FindBy(xpath ="//button[@aria-label='Stage']")
	WebElement stage;
	@FindBy(xpath = "//div[@aria-label='Stage']/lightning-base-combobox-item/span[2]/span")
	List<WebElement> listOfStages;
	@FindBy(xpath = "//input[@name='Probability']")
	WebElement probability;
	@FindBy(xpath ="//button[@aria-label='Forecast Category']")
	WebElement forcastCategory;
	@FindBy(xpath = "//div[@aria-label='Forecast Category']/lightning-base-combobox-item/span[2]/span")
	List<WebElement> listForcastCategory;
	@FindBy(xpath = "//button[@name='SaveEdit']")
    WebElement saveButton;
	
	@FindBy(xpath = "//span[text()='Opportunity Name']")
	WebElement OppName;
	
	public void clickOnOpportunityButton()
	{
		jse.executeScript("arguments[0].click();", opportunity);
		//opportunity.click();
	}
	
	public void clickOnNewButton()
	{
		waitClass.waitUntillVisibilityOfElement(OppName);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newButton.click();
	}
	
	public void fillAboutDetails(String oppname, String accname,String Amount, String descrptiondata, String month1, String day1)
	{
		opportunityName.sendKeys(oppname);
		accountName.click();
		for(WebElement e:requiredAccountName)
		{
			if(e.getText().equalsIgnoreCase(accname))
			{
				e.click();
				break;
			}
		}
		
		selectDate(month1,day1);
		amount.sendKeys(Amount);
		descrption.sendKeys(descrptiondata);
		
	}
	
	public void fillStatusDetails(String stageName, String forcastname)
	{
		jse.executeScript("arguments[0].scrollIntoView(true);", stage);
       stage.click();
       for(WebElement e: listOfStages)
       {
    	   if(e.getText().equalsIgnoreCase(stageName))
    	   {
    		   e.click();
    		   break;
    	   }
       }
       probability.sendKeys("");
       forcastCategory.click();
       for(WebElement e: listForcastCategory)
       {
    	   if(e.getText().equalsIgnoreCase(forcastname))
    	   {
    		   e.click();
    		   break;
    	   }
       }
       
	}
	public void saveForm()
	{
		saveButton.click();
	}
	
	public void selectDate(String monthname, String monthno)
	{
		closeDate.click();
		if(monthName.getText().equalsIgnoreCase(monthname))
		{
			for(WebElement e:listOfDates)
			{
				if(e.getText().equalsIgnoreCase(monthno))
				{
					e.click();
					break;
				}
			}
		}else
		{outerLoop:
			for(int i=1;i<=12;i++)
			{
				
				leftArrow.click();
				if(monthName.getText().equalsIgnoreCase(monthname))
				{
					for(WebElement e:listOfDates)
					{
						if(e.getText().equalsIgnoreCase(monthno))
						{
							e.click();
							break outerLoop;
						}
					}
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
}
