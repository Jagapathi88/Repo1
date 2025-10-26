package base;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Base {

	public  Properties p;
	public static WebDriver driver;
	public ExtentReports reports;
	public ExtentSparkReporter sparkReporter;
	public static ExtentTest test;
	
	@BeforeTest
	public void beforeTest() 
	{
		reports = new ExtentReports();
		sparkReporter =new ExtentSparkReporter(new File(System.getProperty("user.dir")+"\\reports.report.html"));
		reports.attachReporter(sparkReporter);
		reports.setSystemInfo("Os", System.getProperty("os-version"));
		reports.setSystemInfo("Tester", "Jagapathi");
		sparkReporter.config().setReportName("SalesForce test report");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setTheme(Theme.DARK);
		
	}
	
	public static String captureScreenshots()  
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"\\screenshots\\image.png";
		try {
			FileUtils.copyFile(src, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	
	
	public String[]  dataProvider() throws IOException
	{
		File src = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\Testdata.xlsx");
		FileInputStream file = new FileInputStream(src);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int rows = sheet.getPhysicalNumberOfRows();
		int columns = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println(rows + " "+ columns);
		String[] data = new String[rows-1];
		for(int i=1;i<rows;i++)
		{
			String s =sheet.getRow(i).getCell(0).getStringCellValue();
			data[i-1] = s;
		}
		return data;
	}
	
	public String  propertiesLoad(String propertyKey) throws Exception  {
		p = new Properties();
		FileInputStream pFile = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\java\\utils\\Global.properties"));
		p.load(pFile);
	return 	p.getProperty(propertyKey);
	}
	
	public void setUpBrowser() throws Exception
	{
		
		String browser =propertiesLoad("browser");
		String url = propertiesLoad("url");
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}else if (browser.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	}
	
	
	@BeforeMethod
	public void beforeMethod(ITestContext context, ITestResult result, Method m) throws Exception
	{
		test = reports.createTest(m.getName());
		
		setUpBrowser();
	}
	
	@AfterMethod
	public void afterMethod()
	{
		driver.quit();
	}
	
	@AfterTest
	public void afterTest() throws IOException
	{
		reports.flush();
		Desktop.getDesktop().browse(new File(System.getProperty("user.dir")+"\\reports.report.html").toURI());
	}
}
