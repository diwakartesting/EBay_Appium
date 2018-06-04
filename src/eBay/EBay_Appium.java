package eBay;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class EBay_Appium {
	//Declaration of Variables
	private static WebDriver driver;
	static String search_Item = "65 Inch TV";
	static String item_Name;
	static String item_Price;
	static String reviewitem_Name;
	static String reviewitem_Price;
	static String summaryitem_Name;
	static String summaryitem_Price;
	static String username ="shraddha171994@gmail.com";
	static String password = "Welcome@123";
	//Declaration of Locators
	static By SearchBox = By.id("com.ebay.mobile:id/search_box");
	static By SearchText = By.id("com.ebay.mobile:id/search_src_text");
	static By SearchList = By.id("com.ebay.mobile:id/text");
	static By ResultList = By.id("com.ebay.mobile:id/textview_item_title");
	static By SelectedItemName = By.id("com.ebay.mobile:id/textview_item_title");
	static By SelectedItemPrice = By.id("com.ebay.mobile:id/textview_item_price");
	static By BuyNowButton = By.xpath("//android.widget.Button[@text='BUY IT NOW']");
	static By UserName = By.xpath("//android.widget.EditText[@password='false']");
	static By Password = By.xpath("//android.widget.EditText[@password='true']");
	static By SignIn = By.xpath("//android.widget.Button[@text='SIGN IN']");
	static By AllowOption = By.id("com.ebay.mobile:id/button_google_deny");
	static By ReviewItemName = By.id("com.ebay.mobile:id/item_title");
	static By ReviewItemPrice = By.id("com.ebay.mobile:id/textview_item_price");
	static By ReviewButton = By.id("com.ebay.mobile:id/take_action");
	static By SummaryItemName = By.xpath("//android.view.View[@instance='26']");
	static By SummaryItemPrice = By.xpath("//android.view.View[@instance='28']");
	//Method to verify existence of element
	public static boolean elementExists(By locator) throws Throwable
	{
		Thread.sleep(10000);
		if(driver.findElements(locator).size()!=0)
		{
			return true;
		}
		return false;
	}
	//Method to click element
	public static void clickElement(By locator) {
		driver.findElement(locator).click();
	}
	//Method to save text property of element
	public static String saveText(By locator) {
		return driver.findElement(locator).getText();		
	}
	//Method to create web element List
	public static void elementList(By locator, int index)
	{
		List<WebElement> search = driver.findElements(locator);
		if (!search.isEmpty()) {
			System.out.println(search.get(index).getText());
			search.get(index).click();
		}	 
	}
	//Method to send keys to element
	public static void enterData(By locator, String value) {
		driver.findElement(locator).sendKeys(value);
	}

	public static void main(String[] args) throws Throwable, MalformedURLException {
		File appDir = new File("C:\\Users\\SHRADS\\Downloads");
		File app = new File(appDir, "eBay.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//Set Mandatory Capabilities
		capabilities.setCapability("device","Moto G");
		capabilities.setCapability("deviceName","Moto G");
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("udid", "ZY223GM339");
		capabilities.setCapability("fullReset","false");
		capabilities.setCapability("app", app.getAbsolutePath());
		driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		//verify search box is present
		if(elementExists(SearchBox))
		{
			clickElement(SearchBox);
		}
		//Enter search item
		if(elementExists(SearchText))
		{
			enterData(SearchText, search_Item);
		}
		//select item from auto populated options
		if(elementExists(SearchList))
		{
			elementList(SearchList, 0);
		}
		//verify result list
		if(elementExists(ResultList))
		{
			elementList(ResultList, 1);
		}
		//Verify selected Item name
		if(elementExists(SelectedItemName))
		{
			item_Name = saveText(SelectedItemName);
			System.out.println(item_Name);
		}
		//Verify selected Item price	
		if(elementExists(SelectedItemPrice))
		{
			item_Price = saveText(SelectedItemPrice);
			System.out.println(item_Price);
		}
		
		if(elementExists(BuyNowButton))
		{
			clickElement(BuyNowButton);
		}
		//Enter credentials to Sign IN
		if(elementExists(UserName))
		{
			enterData(UserName, username);
		}

		if(elementExists(Password))
		{
			enterData(Password, password);
		}

		if(elementExists(SignIn))
		{
			clickElement(SignIn);
		}

		if(elementExists(AllowOption))
		{
			clickElement(AllowOption);
		}
		//Verify selected Item name on review page
		if(elementExists(ReviewItemName))
		{
			reviewitem_Name = saveText(ReviewItemName);
			System.out.println(reviewitem_Name);
		}
		
		//Verify selected Item price on review page
		if(elementExists(ReviewItemPrice))
		{
			reviewitem_Price = saveText(ReviewItemPrice);
			System.out.println(reviewitem_Price);
			Assert.assertEquals(item_Price, reviewitem_Price);
		}

		if(elementExists(ReviewButton))
		{
			clickElement(ReviewButton);
		}
		
		//Verify selected Item name on summary page		
		if(elementExists(SummaryItemName))
		{
			summaryitem_Name = saveText(SummaryItemName);
			System.out.println(summaryitem_Name);
		}
		
		//Verify selected Item price on summary page
		if(elementExists(SummaryItemPrice))
		{
			summaryitem_Price = saveText(SummaryItemPrice);
			System.out.println(summaryitem_Price);
		}

	}
}
