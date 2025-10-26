package pageTest;

import java.util.HashMap;

import org.testng.annotations.Test;

import base.Base;
import pageEvents.LoginPageEvents;
import pageEvents.SalesPageEvents;
import utils.DataProviderclass;

public class CreateAccountTest extends Base {

	@Test(dataProvider = "dataProviderForAccount", dataProviderClass = DataProviderclass.class)
	public void loginTest(HashMap<String, String> data) throws Exception {

		String userName = propertiesLoad("username");
		String passkey = propertiesLoad("passowrd");
		LoginPageEvents login = new LoginPageEvents(driver);
		login.login(userName, passkey);
		SalesPageEvents sales = new SalesPageEvents(driver);
		sales.createAccount(data);
	}
}
