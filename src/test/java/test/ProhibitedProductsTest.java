package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.ShoppingCartPage;
import utilities.ReadExcelFile;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import base.Base;

public class ProhibitedProductsTest extends Base {

	@BeforeClass
	public void setupClass() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
	}

	@BeforeMethod
	public void setupMethod() {
		Base.initialization();
		ShoppingCartPage = new ShoppingCartPage();
		ShoppingCartPage.openUrl("ShoppingCartLink");

	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in the report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception

			String screenshotPath = Base.takeScreenShot(result.getName());
			System.out.println(screenshotPath);
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); // to add screenshot in the
																							// report

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());
		}
		driver.quit();
	}

	@AfterClass()
	public void tearDownClass() {
		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report
		extent.flush();
		//extent.close();
	}
	
	
	@DataProvider // to read valid data from excel sheet
	public Object[][] readprohibitedDataFomExcelFile() throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/main/java/testData";
		String fileName = "Test_Data.xlsx";
		String sheetName = "prohibitedData";
		return ReadExcelFile.readExcel(filePath, fileName, sheetName);
	} 
	
	@Test(dataProvider = "readprohibitedDataFomExcelFile")
	public void verifyAddprohibitedItem(String Itemurl) {
		extentTest = extent.startTest("verifyAddprohibitedItem");
		ShoppingCartPage.addProhipetedItem(Itemurl);
		String Actual= ShoppingCartPage.getApologyMessage();
		String Expected= "we apologize, store is not available at this moment";	
		System.out.println(Actual);
		Assert.assertTrue(Actual.contains(Expected));
	}

	
}
