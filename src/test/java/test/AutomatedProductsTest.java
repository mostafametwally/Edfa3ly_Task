package test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import base.Base;
import pages.ShoppingCartPage;
import utilities.ReadExcelFile;

public class AutomatedProductsTest extends Base {

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
//		extent.close();
	}

	@DataProvider // to read valid data from excel sheet
	public Object[][] readValidDataFromExcelFile() throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/main/java/testData";
		String fileName = "Test_Data.xlsx";
		String sheetName = "ValidData";
		return ReadExcelFile.readExcel(filePath, fileName, sheetName);
	}

	@Test(dataProvider = "readValidDataFromExcelFile", priority = 1)
	public void verifyAddAutomatedItem_ValidData(String Itemurl, String Quantity, String Price, String Color,
			String Size) {
		extentTest = extent.startTest("verifyAddAutomatedItem_ValidData");
		System.out.println("||" + Quantity + "||" + Price + "||" + Color + "||" + Size + "||");
		ShoppingCartPage.addItem(Itemurl, Quantity, Price, Color, Size);
		if (ShoppingCartPage.checkQuantity()) {
			Assert.assertTrue(ShoppingCartPage
					.getElementText(driver.findElement(By.xpath("//div[@class=\"fixed-bottom-popup\"]//span")))
					.contains("Item has been added to your cart"));
		} else
			Assert.assertTrue(false);
	}

	@DataProvider // to read invalid data from excel sheet
	public Object[][] readInvalidDataFromExcelFile() throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/main/java/testData";
		String fileName = "Test_Data.xlsx";
		String sheetName = "InvalidData";
		return ReadExcelFile.readExcel(filePath, fileName, sheetName);

	}

	@Test(dataProvider = "readInvalidDataFromExcelFile", priority = 2)
	public void verifyAddAutomatedItem_InvalidData(String Itemurl, String Quantity, String Price, String Color,
			String Size) {
		extentTest = extent.startTest("verifyAddAutomatedItem_InvalidData");
		System.out.println("||" + Quantity + "||" + Price + "||" + Color + "||" + Size + "||");
		ShoppingCartPage.addItem(Itemurl, Quantity, Price, Color, Size);
		Assert.assertTrue(ShoppingCartPage
				.getElementText(driver.findElement(By.xpath("//div[@class=\"fixed-bottom-popup\"]//span")))
				.contains("Element was not added"));
	}

}
