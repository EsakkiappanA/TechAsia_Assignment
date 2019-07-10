package stepDefinition;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.AssignmentPage;
import reportGenerator.ScreenShotGenerator;

public class Assignment {
	
	public WebDriver wd = null;
//	public String Username = null;
//	public String pwd = null;
	
	@Given("^Open the browser$")
	public void open_the_browser() {
		wd = AssignmentPage.loadBrowser(wd);
	}
	
	@Given("^Open the URL$")
	public void open_the_URL(DataTable urlDetails){
		List<List<String>> data = urlDetails.raw();
		String URL = data.get(0).get(0);
		AssignmentPage.loadURL(wd, URL);	    
	}

	@Then("^validate user details by enter valid and invalid data$")
	public void validate_user_details_by_enter_valid_and_invalid_data(DataTable usercredentials) throws InterruptedException{
//		List<Map<String,String>> data = usercredentials.asMaps(String.class,String.class);
		for (Map<String, String> data : usercredentials.asMaps(String.class, String.class)) {
			AssignmentPage.enterUserCredentials(data.get("Username").toString(), data.get("Password").toString(),wd);
			AssignmentPage.clickLoginButton(wd);
			AssignmentPage.validateUserCredentials(wd);
			
		}	    
	}
	
	@Then("^User enters crediential to login$")
	public void user_enters_crediential_to_login(DataTable usercredentials) {
		List<List<String>> data = usercredentials.raw();
		AssignmentPage.enterUserCredentials(data.get(0).get(0), data.get(0).get(1),wd);		
	}

	@Then("^Click the login button$")
	public void click_the_login_button(){
	 AssignmentPage.clickLoginButton(wd);  
	}

//	@Then("^validate user details is valid or not$")
//	public void validate_user_details_is_valid_or_not(){
//	    
//	}

//	@Given("^Open the URL$")
//	public void open_the_URL() {
//	    
//	}

//	@Then("^User enters crediential to login$")
//	public void enter_the_username_and_password() throws Throwable {
//	    
//	}

	@Then("^Add a product from homepage$")
	public void add_a_product_from_homepage(){
		AssignmentPage.addProduct(wd);
	    
	}

	@Then("^go to cart page and validate the product$")
	public void go_to_cart_page_and_validate_the_product(){
	    AssignmentPage.gotoCartPage(wd);
	    
	}

	@Then("^Click on checkout$")
	public void click_on_checkout(){
	    AssignmentPage.gotoCheckOutPage(wd);
	}

	@Then("^Enter delivery details$")
	public void enter_delivery_details() throws Throwable {
	    AssignmentPage.enterDeliveryDetails("aaa", "bbb", "123",wd);
	}

	@Then("^Finish the order$")
	public void finish_the_order() throws Throwable {
		AssignmentPage.finishOrder(wd);
	}

	@Then("^Check the name sort filter$")
	public void check_the_name_sort_filter() throws Throwable {
		AssignmentPage.nameSortFilter(wd);
	    
	}

	@Then("^check the price low to high filter$")
	public void check_the_price_low_to_high_filter() throws Throwable {
	    AssignmentPage.priceLowToHighFilter(wd);
	}

	@Then("^check the price high to low filter$")
	public void check_the_price_high_to_low_filter() throws Throwable {
	    AssignmentPage.priceHighToLowFilter(wd);
	}
	
	@After
	public void tearDown(Scenario S) throws IOException{
		if(S.isFailed()) {
			ScreenShotGenerator.generateScreenShot(S, wd);
		}
	}
	
	@AfterClass
	public void closeBrowser() {
		wd.close();
	}


}
