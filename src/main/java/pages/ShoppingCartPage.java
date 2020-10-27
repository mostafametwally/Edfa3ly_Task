package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;

public class ShoppingCartPage extends Base {

	@FindBy(xpath = "//*[@id=\"sb-site\"]/div[1]/div/div[1]")
	WebElement Logo;

	@FindBy(xpath = "//input[@type=\"url\"]")
	WebElement ItemURL;

	@FindBy(xpath = "//input[@name=\"quantity\"]")
	WebElement QuantityTape;

	@FindBy(xpath = "//*[@id=\"product-price\"]")
	WebElement PriceTape;

	@FindBy(xpath = "//select[@name=\"color\"]")
	WebElement CT;

	@FindBy(xpath = "//select[@name=\"size\"]")
	WebElement ST;

	@FindBy(xpath = "//input[@value=\"Add item\"]")
	WebElement AddItemButtom;

	@FindBy(xpath = "//input[@id=\"product-color-text\"]")
	WebElement ColorText;

	@FindBy(xpath = "//input[@id=\"product-size-text\"]")
	WebElement SizeText;

	@FindBy(xpath = "//label[@class=\"visibility ng-binding ng-scope\"]")
	WebElement ApologyMessage;

	static WebDriverWait wait;

	// the Class constructor

	public ShoppingCartPage() {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 5);
	}

	private void waitPageLoading() {
		wait.until(ExpectedConditions.visibilityOf(Logo));
	}

	public void openUrl(String url) {

		driver.get(prop.getProperty(url));
		waitPageLoading();
		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%';");
	}

	public void addItem(String Itemurl, String Quantity, String Price, String Color, String Size) {

		ItemURL.clear();
		ItemURL.sendKeys(Itemurl);

		QuantityTape.clear();
		QuantityTape.sendKeys(Quantity);

		Select ColorTape = new Select(CT);
		try {
			ColorTape.selectByVisibleText(Color);
		} catch (Exception e) {
			try {
				ColorTape.selectByIndex(3);
				ColorText.sendKeys(Color);
			} catch (Exception e1) {

			}
		}

		Select SizeTape = new Select(ST);

		try {
			SizeTape.selectByVisibleText(Size);
		} catch (Exception e) {
			try {
				SizeTape.selectByIndex(13);
				SizeText.sendKeys(Size);
			} catch (Exception e1) {
			}
		}

		PriceTape.clear();
		PriceTape.sendKeys(Price);

		AddItemButtom.sendKeys(Keys.ENTER);

	}

	public String getElementText(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			String x = element.getText();
			wait.until(ExpectedConditions.invisibilityOf(element));
			return x;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "Element was not added";
		}
	}

	public boolean checkQuantity() {
		if (Integer.parseInt(QuantityTape.getAttribute("value")) <= 5)
			return true;
		else
			return false;
	}
	
	public String getApologyMessage(){
		return ApologyMessage.getText();
	}

	public void addProhipetedItem(String Itemurl) {
		ItemURL.clear();
		ItemURL.sendKeys(Itemurl);
		
	}
}
