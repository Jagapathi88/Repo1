package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviderclass {

	@DataProvider
	public Object[] dataProvider() throws IOException
	{
		List<HashMap<String, String>> data = JsonReader.getdata("opp");
		
		Object[] obj = {data.get(0),data.get(1),data.get(2),data.get(3)};
		return obj;
	}
	
	@DataProvider
	public Object[] dataProviderForAccount() throws IOException
	{
		List<HashMap<String,String>> accountData = JsonReader.getdata("accountData");
		Object[] obj = {accountData.get(0),accountData.get(1)};
		
		return obj;
	}
}
