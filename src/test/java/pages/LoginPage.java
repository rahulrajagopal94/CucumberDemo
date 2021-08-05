package pages;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import GenericComponents.ReusableComponents;
import Reports.CaptureScreenshot;
import Reports.TestReportSteps;
import Utilities.ConfigFile;

public class LoginPage {
	public static JSONObject jObject;
	public static List<String> screenshotList = new ArrayList<String>();

	public static List<TestReportSteps> LoginToApplication(WebDriver driver, JSONObject inputjson) throws Exception {
		List<TestReportSteps> listOfReport = new ArrayList<TestReportSteps>();
		screenshotList.clear();
		int step = 0;
		String objective = "To verify that user is able to login to the application.";
		jObject = ConfigFile.RetrieveUIMap("LoginPageSelector.json");

		try {
			// Click on the 'My Account' button
			listOfReport.add(ReusableComponents.GenerateReportSteps("Click on the My Account button.", "", objective, step));
			ReusableComponents.Click(driver, "XPath", jObject.get("myaccount").toString());
			listOfReport.get(step++).actualResultFail = "";

			// Click on the 'Login' button.
			listOfReport.add(ReusableComponents.GenerateReportSteps("Click on the Login button.", "", objective, step));
			ReusableComponents.Click(driver, "XPath", jObject.get("loginPage").toString());
			listOfReport.get(step++).actualResultFail = "";

			// Enter username
			listOfReport.add(ReusableComponents.GenerateReportSteps("Enter username : " + inputjson.get("username").toString() + ".", "", objective, step));
			ReusableComponents.SendKeys(driver, "Id", jObject.get("username").toString(),inputjson.get("username").toString());
			listOfReport.get(step++).actualResultFail = "";

			// Enter password
			listOfReport.add(ReusableComponents.GenerateReportSteps("Enter password. Capture Screenshot.", "", objective, step));
			ReusableComponents.SendKeys(driver, "Id", jObject.get("password").toString(),inputjson.get("password").toString());
			listOfReport.get(step++).actualResultFail = "";
			screenshotList.add(CaptureScreenshot.TakeSingleSnapShot(driver, "LoginPage" + ConfigFile.GetCurrentDateTime()));

			// Click on the 'Login' button.
			listOfReport.add(ReusableComponents.GenerateReportSteps("Click on the Login button.", "", objective, step));
			ReusableComponents.Click(driver, "XPath", jObject.get("loginButton").toString());
			listOfReport.get(step++).actualResultFail = "";

			// Wait for page to load
			listOfReport.add(ReusableComponents.GenerateReportSteps("Verify that user is able to log in to the application. Capture Screenshot.", "", objective, step));
			ReusableComponents.RetrieveAndContainsText(driver, "XPath", jObject.get("verifyLogin").toString(),"My Account");
			listOfReport.get(step++).actualResultFail = "";
			screenshotList.add(CaptureScreenshot.TakeSingleSnapShot(driver, "VerifyLoginPage" + ConfigFile.GetCurrentDateTime()));

		} catch (Exception e) {
			System.out.println("LoginToApplication failed at step: " + (step + 1) + " " + e);
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
