package pages;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import GenericComponents.ReusableComponents;
import Reports.CaptureScreenshot;
import Reports.TestReportSteps;
import Utilities.ConfigFile;

public class AddressPage {
	public static JSONObject jObject;
	public static List<String> screenshotList = new ArrayList<String>();

	public static List<TestReportSteps> OpenAddressBook(WebDriver driver, JSONObject inputjson) throws Exception {
		List<TestReportSteps> listOfReport = new ArrayList<TestReportSteps>();
		screenshotList.clear();
		int step = 0;
		String objective = "To verify that user is able to click Address Book.";
		jObject = ConfigFile.RetrieveUIMap("AddressPageSelector.json");

		try {

			// Click on the 'Address Book' button
			listOfReport.add(ReusableComponents.GenerateReportSteps("Click on the 'Address Book' button.", "", objective, step));
			ReusableComponents.Click(driver, "XPath", jObject.get("address").toString());
			listOfReport.get(step++).actualResultFail = "";

		} catch (Exception e) {
			System.out.println("Clicking Address book button failed at step: " + (step + 1) + " " + e);
			if (!listOfReport.get(step).GetStepDescription().toLowerCase().contains("screenshot"))
				listOfReport.get(step).stepDescription = listOfReport.get(step).stepDescription.concat(" Capture Screenshot.");
			screenshotList.add(CaptureScreenshot.TakeSingleSnapShot(driver, "Error" + ConfigFile.GetCurrentDateTime()));

		}

		return listOfReport;

	}

	public static List<TestReportSteps> ClickonNewAddress(WebDriver driver, JSONObject inputjson) throws Exception {
		List<TestReportSteps> listOfReport = new ArrayList<TestReportSteps>();
		screenshotList.clear();
		int step = 0;
		String objective = "To verify that user is able to click 'New Address' button.";
		jObject = ConfigFile.RetrieveUIMap("AddressPageSelector.json");

		try {

			// Click on the 'New Address' button
			listOfReport.add(ReusableComponents.GenerateReportSteps("Click on the 'New Address' button.", "", objective, step));
			ReusableComponents.Click(driver, "XPath", jObject.get("newAddressButton").toString());
			listOfReport.get(step++).actualResultFail = "";

		} catch (Exception e) {
			System.out.println("Clicking new address button failed at step: " + (step + 1) + " " + e);
			if (!listOfReport.get(step).GetStepDescription().toLowerCase().contains("screenshot"))
				listOfReport.get(step).stepDescription = listOfReport.get(step).stepDescription.concat(" Capture Screenshot.");
			screenshotList.add(CaptureScreenshot.TakeSingleSnapShot(driver, "Error" + ConfigFile.GetCurrentDateTime()));

		}

		return listOfReport;

	}

	public static List<TestReportSteps> EnterAddressDetails(WebDriver driver, JSONObject inputjson) throws Exception {
		List<TestReportSteps> listOfReport = new ArrayList<TestReportSteps>();
		screenshotList.clear();
		int step = 0;
		String objective = "To verify that user is able enter new address details.";
		jObject = ConfigFile.RetrieveUIMap("AddressPageSelector.json");

		try {
			// Enter firstname
			listOfReport.add(ReusableComponents.GenerateReportSteps("Enter firstname : " + inputjson.get("firstName").toString() + ".", "", objective, step));
			ReusableComponents.SendKeys(driver, "Id", jObject.get("firstName").toString(),inputjson.get("firstName").toString());
			listOfReport.get(step++).actualResultFail = "";

			// Enter lastname
			listOfReport.add(ReusableComponents.GenerateReportSteps("Enter lastname : " + inputjson.get("lastName").toString() + ".", "", objective, step));
			ReusableComponents.SendKeys(driver, "Id", jObject.get("lastName").toString(),inputjson.get("lastName").toString());
			listOfReport.get(step++).actualResultFail = "";

			// Enter address
			listOfReport.add(ReusableComponents.GenerateReportSteps("Enter address : " + inputjson.get("address").toString() + ".", "", objective, step));
			ReusableComponents.SendKeys(driver, "Id", jObject.get("addressInput").toString(),inputjson.get("address").toString());
			listOfReport.get(step++).actualResultFail = "";

			// Enter city
			listOfReport.add(ReusableComponents.GenerateReportSteps("Enter city : " + inputjson.get("city").toString() + ".", "", objective, step));
			ReusableComponents.SendKeys(driver, "Id", jObject.get("city").toString(), inputjson.get("city").toString());
			listOfReport.get(step++).actualResultFail = "";

			// Enter postcode
			listOfReport.add(ReusableComponents.GenerateReportSteps("Enter postcode : " + inputjson.get("postcode").toString() + ".", "", objective, step));
			ReusableComponents.SendKeys(driver, "Id", jObject.get("postcode").toString(),inputjson.get("postcode").toString());
			listOfReport.get(step++).actualResultFail = "";

			// Select region
			listOfReport.add(ReusableComponents.GenerateReportSteps("Select region : " + inputjson.get("state").toString() + ". Capture Screenshot.", "", objective, step));
			ReusableComponents.ScrollToElement(driver, "XPath", jObject.get("continueButton").toString());
			WebElement region = driver.findElement(By.name(jObject.get("region").toString()));
			// create select element object
			Select selectElement = new Select(region);
			// select by value
			selectElement.selectByVisibleText(inputjson.get("state").toString());
			listOfReport.get(step++).actualResultFail = "";
			screenshotList.add(CaptureScreenshot.TakeSingleSnapShot(driver, "AddressDetails" + ConfigFile.GetCurrentDateTime()));

			// Click on the 'Continue' button.
			listOfReport.add(ReusableComponents.GenerateReportSteps("Click on the Continue button.", "", objective, step));
			ReusableComponents.Click(driver, "XPath", jObject.get("continueButton").toString());
			listOfReport.get(step++).actualResultFail = "";

			// Wait for page to load
			listOfReport.add(ReusableComponents.GenerateReportSteps("Verify that user is able to add address. Capture Screenshot.", "", objective, step));
			ReusableComponents.RetrieveAndContainsText(driver, "XPath", jObject.get("verifyAddAddress").toString(),inputjson.get("verifyAddAddress").toString());
			listOfReport.get(step++).actualResultFail = "";
			screenshotList.add(CaptureScreenshot.TakeSingleSnapShot(driver,"VerifyAddressPage" + ConfigFile.GetCurrentDateTime()));

		} catch (Exception e) {
			System.out.println("Entering new address details failed at step: " + (step + 1) + " " + e);
			if (!listOfReport.get(step).GetStepDescription().toLowerCase().contains("screenshot"))
				listOfReport.get(step).stepDescription = listOfReport.get(step).stepDescription.concat(" Capture Screenshot.");
			screenshotList.add(CaptureScreenshot.TakeSingleSnapShot(driver, "Error" + ConfigFile.GetCurrentDateTime()));

		}

		return listOfReport;

	}

	public static List<TestReportSteps> VerifyAddSuccessMessage(WebDriver driver, JSONObject inputjson) throws Exception {
		List<TestReportSteps> listOfReport = new ArrayList<TestReportSteps>();
		screenshotList.clear();
		int step = 0;
		String objective = "To verify that user is able to add the address.";
		jObject = ConfigFile.RetrieveUIMap("AddressPageSelector.json");

		try {
			
			// Wait for page to load
			listOfReport.add(ReusableComponents.GenerateReportSteps("Verify that success message '"+inputjson.get("verifyAddAddress").toString()+"' is displayed. Capture Screenshot.", "", objective, step));
			ReusableComponents.RetrieveAndContainsText(driver, "XPath", jObject.get("verifyAddAddress").toString(),inputjson.get("verifyAddAddress").toString());
			listOfReport.get(step++).actualResultFail = "";
			screenshotList.add(CaptureScreenshot.TakeSingleSnapShot(driver,"VerifyAddressPage" + ConfigFile.GetCurrentDateTime()));

		} catch (Exception e) {
			System.out.println("Adding new address failed at step: " + (step + 1) + " " + e);
			if (!listOfReport.get(step).GetStepDescription().toLowerCase().contains("screenshot"))
				listOfReport.get(step).stepDescription = listOfReport.get(step).stepDescription.concat(" Capture Screenshot.");
			screenshotList.add(CaptureScreenshot.TakeSingleSnapShot(driver, "Error" + ConfigFile.GetCurrentDateTime()));

		}

		return listOfReport;

		}
	
	
	
	public static List<TestReportSteps> ClickDeleteButton(WebDriver driver, JSONObject inputjson) throws Exception {
		List<TestReportSteps> listOfReport = new ArrayList<TestReportSteps>();
		screenshotList.clear();
		int step = 0;
		String objective = "To verify that user is able to click on delete button against address.";
		jObject = ConfigFile.RetrieveUIMap("AddressPageSelector.json");

		try {
			
			// Click on Delete button
			listOfReport.add(ReusableComponents.GenerateReportSteps("Click on the 'Delete' button.", "", objective, step));
			ReusableComponents.Click(driver, "XPath", jObject.get("delete").toString());
			listOfReport.get(step++).actualResultFail = "";

		} catch (Exception e) {
			System.out.println("Clicking delete button failed at step: " + (step + 1) + " " + e);
			if (!listOfReport.get(step).GetStepDescription().toLowerCase().contains("screenshot"))
				listOfReport.get(step).stepDescription = listOfReport.get(step).stepDescription.concat(" Capture Screenshot.");
			screenshotList.add(CaptureScreenshot.TakeSingleSnapShot(driver, "Error" + ConfigFile.GetCurrentDateTime()));

		}

		return listOfReport;

		}
	
	
	
	public static List<TestReportSteps> VerifyDeleteSuccessMessage(WebDriver driver, JSONObject inputjson) throws Exception {
		List<TestReportSteps> listOfReport = new ArrayList<TestReportSteps>();
		screenshotList.clear();
		int step = 0;
		String objective = "To verify that user is able to delete the address.";
		jObject = ConfigFile.RetrieveUIMap("AddressPageSelector.json");

		try {
			
			// Wait for page to load
			listOfReport.add(ReusableComponents.GenerateReportSteps("Verify that success message '"+inputjson.get("verifyDeleteAddress").toString()+"' is displayed. Capture Screenshot.", "", objective, step));
			ReusableComponents.RetrieveAndContainsText(driver, "XPath", jObject.get("verifyDeleteAddress").toString(),inputjson.get("verifyDeleteAddress").toString());
			listOfReport.get(step++).actualResultFail = "";
			screenshotList.add(CaptureScreenshot.TakeSingleSnapShot(driver,"VerifyAddressDelete" + ConfigFile.GetCurrentDateTime()));

		} catch (Exception e) {
			System.out.println("Deleting address failed at step: " + (step + 1) + " " + e);
			if (!listOfReport.get(step).GetStepDescription().toLowerCase().contains("screenshot"))
				listOfReport.get(step).stepDescription = listOfReport.get(step).stepDescription.concat(" Capture Screenshot.");
			screenshotList.add(CaptureScreenshot.TakeSingleSnapShot(driver, "Error" + ConfigFile.GetCurrentDateTime()));

		}

		return listOfReport;

		}
	
	
	
	
	
	
	
	
	public static List<String> getFilePath() {
		return screenshotList;
	}

}
