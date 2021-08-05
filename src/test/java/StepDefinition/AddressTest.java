package StepDefinition;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;


import Reports.TestReportSteps;
import Utilities.ConfigFile;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.AddressPage;
import pages.LoginPage;
import projectConfig.Setup;


public class AddressTest extends Setup  {
	public static JSONObject listOfInputs = null, jObject;
	private static List<TestReportSteps> report = new ArrayList<>();
	
	
	@Before
	public static void Before(Scenario scenario) throws Exception
	{
		Setup.BeforeEachTest();
		init(scenario);
	}
	
	@After
	public static void After(Scenario scenario) throws Exception
	{
		Setup.AfterEachTest(scenario);
	}
	

	
	public static void init(Scenario scenario) throws Exception
	{
		testObjective = scenario.getName();
		scriptName = "TS001_Add/Delete Address";
		inputjson = ConfigFile.RetrieveTestData("AddressTest.json");
		for (int i = 0; i < inputjson.length(); i++) {
			listOfInputs = inputjson.getJSONObject(i);
		}
	}
	
	
	@Given("^Login to the application$")
	public void login_to_the_application() throws Throwable 
	{
		report = LoginPage.LoginToApplication(driver, listOfInputs);
		finalreport.addAll(report);		
		for (String s : LoginPage.getFilePath()) 
		{
			screenshotList.add(s);
		}
		report.clear();	
		Screenshot();

	}

	@And("^Click on Address Book$")
	public void click_on_address_book() throws Throwable {
	
		report = AddressPage.OpenAddressBook(driver, listOfInputs);
		finalreport.addAll(report);		
		for (String s : AddressPage.getFilePath()) 
		{
			screenshotList.add(s);
		}
		report.clear();		
		Screenshot();

	}
	
	@When("^Click on Add Address button$")
	public void click_on_add_address_button() throws Throwable
	{
		report = AddressPage.ClickonNewAddress(driver, listOfInputs);
		finalreport.addAll(report);		
		for (String s : AddressPage.getFilePath()) 
		{
			screenshotList.add(s);
		}
		report.clear();	
		Screenshot();
	}
	

	@And("^Enter Details and Click Add button$")
	public void enter_the_Username_and_Password() throws Throwable {
		
		report = AddressPage.EnterAddressDetails(driver, listOfInputs);
		finalreport.addAll(report);		
		for (String s : AddressPage.getFilePath()) 
		{
			screenshotList.add(s);
		}
		report.clear();		
		Screenshot();

		}
	
	@Then("^Address book should be created$")
	public void Reset_the_credential() throws Throwable {
		report = AddressPage.VerifyAddSuccessMessage(driver, listOfInputs);
		finalreport.addAll(report);		
		for (String s : AddressPage.getFilePath()) 
		{
			screenshotList.add(s);
		}
		report.clear();		
		Screenshot();

	}
	
	@When("^Click on Delete button against newly added address$")
	public void click_on_Delete_button_against_newly_added_address() throws Throwable {
		report = AddressPage.ClickDeleteButton(driver, listOfInputs);
		finalreport.addAll(report);		
		for (String s : AddressPage.getFilePath()) 
		{
			screenshotList.add(s);
		}
		report.clear();	
		Screenshot();

	}

	@Then("^Address book should be deleted$")
	public void address_book_should_be_deleted() throws Throwable {
		report = AddressPage.VerifyDeleteSuccessMessage(driver, listOfInputs);
		finalreport.addAll(report);		
		for (String s : AddressPage.getFilePath()) 
		{
			screenshotList.add(s);
		}
		report.clear();
		Screenshot();

	}

}
