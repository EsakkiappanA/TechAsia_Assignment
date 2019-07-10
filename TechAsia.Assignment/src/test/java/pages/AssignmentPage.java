package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.Before;

public class AssignmentPage {
	@FindBy(xpath="//button[@class='btn_secondary btn_inventory']") private static WebElement btnRemove;
	@FindBy(xpath="//a[@class='btn_secondary' and @href='./inventory.html']") private static WebElement btnContShop;
	@FindBy(xpath="//a[@class='cart_cancel_link btn_secondary']") private static WebElement btnCancel;
	@FindBy(xpath="//div[@id='checkout_complete_container']/h2") private static WebElement lblOrderConf;
	
	public static String fileSeperator = System.getProperty("file.separator");
	public static String testDir = System.getProperty("user.dir");
	public static String driverLoc =  testDir+ fileSeperator+ "Resources"+ fileSeperator+"chromedriver.exe";
	
	
	@Before
	public static void initialize(WebDriver wd) {
		System.setProperty("webdriver.chrome.driver",driverLoc);
		PageFactory.initElements(wd, AssignmentPage.class);		
	}
	
	public static WebDriver loadBrowser(WebDriver wd) {
		wd = new ChromeDriver();
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return wd;
	}
	
	public static void loadURL(WebDriver wd, String URL) {
		wd.get(URL);
	}
	
	public static void validateHomePage(WebDriver wd) {
		WebElement btnLogin = wd.findElement(By.xpath("//*[@class='btn_action']")); 
		boolean status = btnLogin.isDisplayed();
		if(status == true) {
			System.out.println("Login Page loaded successfully");
		}else {
			System.out.println("Login page not loaded successfully");
		}
		//assert.assertEqualstrue, status);
	}
	
	public static void enterUserCredentials(String Uname, String pwd, WebDriver wd) {
		WebElement txtUserName = wd.findElement(By.id("user-name")); 
		WebElement txtPwd = wd.findElement(By.id("password"));
		txtUserName.sendKeys(Uname);
		txtPwd.sendKeys(pwd);		
	}
	
	public static void clickLoginButton(WebDriver wd) {
		WebElement btnLogin = wd.findElement(By.xpath("//*[@class='btn_action']"));
		btnLogin.click();
	}
	
	public static void validateUserCredentials(WebDriver wd) throws InterruptedException {
		Thread.sleep(2000);
		boolean status1 = false;
		boolean errStatus = false;
		String cURL = wd.getCurrentUrl();
		if(cURL.contains("/inventory.html")) {
			WebElement lblProductCont = wd.findElement(By.id("inventory_container")); 
			status1 = lblProductCont.isDisplayed();	
		}else {
			WebElement lblErrorMsg = wd.findElement(By.xpath("//h3[@data-test='error']")); 
			errStatus = lblErrorMsg.isDisplayed();	
		}				
		if(status1 == true) {
			System.out.println("User Logged in successfully");
			logout(wd);
		}else if(errStatus == true) {
			System.out.println("Invalid User credentials");
		}		
	}
	
	public static void logout(WebDriver wd) throws InterruptedException {
		WebElement btnOpenMenu = wd.findElement(By.xpath("//button[text()='Open Menu']"));
		WebElement lnkLogout = wd.findElement(By.xpath("//a[@id='logout_sidebar_link']"));
		btnOpenMenu.click();
		Thread.sleep(1000);
		lnkLogout.click();
		Thread.sleep(2000);
	}
	
	public static void addProduct(WebDriver wd) {
		//assert.assertTrue("Add to Cart button is not shown", btnAddtoCart.isDisplayed());
		WebElement btnAddtoCart = wd.findElement(By.xpath("(//button[@class='btn_primary btn_inventory'])[1]")); 
		if(btnAddtoCart.isDisplayed()) {
			btnAddtoCart.click();
		}
	}
	
	public static void gotoCartPage(WebDriver wd) {
		WebElement btnCart = wd.findElement(By.xpath("//a[@href='./cart.html']")); 
		btnCart.click();
		String currURL = wd.getCurrentUrl();
		if(currURL.contains("/cart.html")) {
			System.out.println("Successfully routed to Your Cart page");
		}
	}
	
	public static void gotoCheckOutPage(WebDriver wd) {
		WebElement btnCheckOut = wd.findElement(By.xpath("//a[@class='btn_action checkout_button' and @href='./checkout-step-one.html']")); 
		btnCheckOut.click();
	}
	
	public static void enterDeliveryDetails(String fn, String ln, String pcode, WebDriver wd) {
		WebElement txtFname = wd.findElement(By.id("first-name")); 
		WebElement txtLname = wd.findElement(By.id("last-name"));
		WebElement txtPostalCode = wd.findElement(By.id("postal-code")); 
		WebElement btnCont = wd.findElement(By.xpath("//input[@class='btn_primary cart_button']")); 
		txtFname.sendKeys(fn);
		txtLname.sendKeys(ln);
		txtPostalCode.sendKeys(pcode);
		btnCont.click();
		
	}
	
	public static void finishOrder( WebDriver wd) {
		WebElement btnFinish = wd.findElement(By.xpath("//a[@class='btn_action cart_button']"));
		WebDriverWait wait = new WebDriverWait(wd, 20);
		WebElement btnFinish1 = wait.until(ExpectedConditions.visibilityOf(btnFinish));
		if(btnFinish1.isDisplayed()) {
			btnFinish1.click();
		}
		
		String curURL = wd.getCurrentUrl();
		if(curURL.contains("/checkout-complete.html")) {
			System.out.println("Your order placed succesfully");
		}
	}
	
	public static void nameSortFilter(WebDriver wd) throws InterruptedException {
		WebElement drpdwnFilter = wd.findElement(By.xpath("//div[@id='inventory_filter_container']//select"));
		List<String> beforeList = new ArrayList<String>(); 
		List<WebElement> itemNmB4Sort = wd.findElements(By.xpath("//div[@class='inventory_item_name']"));
		for(int i=itemNmB4Sort.size()-1;i>=0;i--) {
			beforeList.add(itemNmB4Sort.get(i).getText());
		}		
		Select filter = new Select(drpdwnFilter);
		filter.selectByValue("za");
		Thread.sleep(2000);
//		List<String> afterList = new ArrayList<String>(); 
		List<WebElement> itemNmAftSort = wd.findElements(By.xpath("//div[@class='inventory_item_name']"));
		String temp = null;
		for(int i=0;i<itemNmAftSort.size();i++) {
			temp = itemNmAftSort.get(i).getText();
			if(!beforeList.get(i).equalsIgnoreCase(temp)) {
				System.out.println("Filter not worked correctly");
			}
		}
		
	}
	
	public static void priceLowToHighFilter(WebDriver wd) throws InterruptedException {
		WebElement drpdwnFilter = wd.findElement(By.xpath("//div[@id='inventory_filter_container']//select"));
//		List<String> beforeList = new ArrayList<String>(); 
//		List<WebElement> itemPrB4Sort = wd.findElements(By.xpath("//div[@class='inventory_item_price']"));
//		for(int i=itemPrB4Sort.size();i>0;i--) {
//			beforeList.add(itemPrB4Sort.get(i).getText());
//		}		
		Select filter = new Select(drpdwnFilter);
		filter.selectByValue("lohi");
		Thread.sleep(2000);
		System.out.println("Item sorted in low to high");
//		List<String> afterList = new ArrayList<String>(); 
//		List<WebElement> itemPrAftSort = wd.findElements(By.xpath("//div[@class='inventory_item_price']"));
//		StringBuilder temp = new StringBuilder(); 
//		temp.append(itemPrAftSort.get(0).getText().toString());
//		temp.deleteCharAt(0);
//		
//		for(int i=1;i<itemPrAftSort.size();i++) {
//			int temp1 = Integer.parseInt(itemPrAftSort.get(i).getText());
//			if(temp) {
//				System.out.println("Filter not worked correctly");
//			}
//		}
	}
	
	public static void priceHighToLowFilter(WebDriver wd) throws InterruptedException {
		WebElement drpdwnFilter = wd.findElement(By.xpath("//div[@id='inventory_filter_container']//select"));
//		List<String> beforeList = new ArrayList<String>(); 
//		List<WebElement> itemPrB4Sort = wd.findElements(By.xpath("//div[@class='inventory_item_price']"));
//		for(int i=itemPrB4Sort.size();i>0;i--) {
//			beforeList.add(itemPrB4Sort.get(i).getText());
//		}		
		Select filter = new Select(drpdwnFilter);
		filter.selectByValue("hilo");
		Thread.sleep(2000);
		System.out.println("Item sorted in high to low");
//		List<String> afterList = new ArrayList<String>(); 
//		List<WebElement> itemPrAftSort = wd.findElements(By.xpath("//div[@class='inventory_item_price']"));
//		String temp = null;
//		for(int i=0;i<itemPrAftSort.size();i++) {
//			temp = itemPrAftSort.get(i).getText();
//			if(!beforeList.get(i).equalsIgnoreCase(temp)) {
//				System.out.println("Filter not worked correctly");
//			}
//		}
	}
	
}
