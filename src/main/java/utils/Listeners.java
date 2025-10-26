package utils;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import base.Base;

public class Listeners extends Base implements ITestListener{

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
			String path =Base.captureScreenshots();
			
		
		Base.test.addScreenCaptureFromPath(path, result.getName())
		.pass("success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
	}

	
}
