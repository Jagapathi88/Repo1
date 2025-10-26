package pageTest;

import java.util.HashMap;
import java.util.Properties;

import org.testng.annotations.Test;

import base.Base;
import pageEvents.LoginPageEvents;
import pageEvents.OpportunitiesPageEvents;
import utils.DataProviderclass;

public class OpportunityCreationTest extends Base {
	OpportunitiesPageEvents oppCreation;
	LoginPageEvents login;

	@Test(dataProvider = "dataProvider", dataProviderClass = DataProviderclass.class)
	public void f(HashMap<String, String> h) throws Exception {
		
		String userName = propertiesLoad("username");
		String passkey = propertiesLoad("passowrd");
		login = new LoginPageEvents(driver);
		login.login(userName, passkey);
		oppCreation = new OpportunitiesPageEvents(driver);
		oppCreation.clickOnOpportunityButton();
		
		oppCreation.clickOnNewButton();
		oppCreation.fillAboutDetails(h.get("opportunityName"), h.get("requiredAccountName"), h.get("amount"),
				h.get("descrption"),h.get("month"),h.get("day"));
		oppCreation.fillStatusDetails(h.get("stage"), h.get("forcast"));
		oppCreation.saveForm();
	}
}
