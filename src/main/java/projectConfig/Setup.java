package projectConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.xml.sax.SAXException;
import com.cucumber.listener.Reporter;

import GenericComponents.ReusableComponents;
import Reports.Report;
import Reports.ReportModifier;
import Reports.TestReportSteps;
import Utilities.ConfigFile;
import Utilities.Constant;
import cucumber.api.Scenario;

public class Setup {
	public static WebDriver driver;
	public static List<TestReportSteps> finalreport = new ArrayList<TestReportSteps>();
	public static List<String> screenshotList = new ArrayList<String>();
	public static String testObjective;
	public static String scriptName;
	public static JSONArray inputjson;
	public static String fileLocation;
	public static String fileName;

	public static void BeforeEachTest() throws Exception {
		Constant.SetConfig();
		driver = ConfigFile.Init();
	}

	public static void AfterEachTest(Scenario scenario) throws Exception {
		Report.WriteResultToHtml(driver, finalreport, screenshotList, testObjective, scriptName,
				ReportModifier.scriptCount);
		finalreport.clear();
		screenshotList.clear();
		Reporter.loadXMLConfig(new File(ReusableComponents.GetAbsoluteFilePath("configs/extent-config.xml")));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", "Windows 10" + "64 Bit");
		Reporter.setSystemInfo("Selenium", "3.7.0");
		Reporter.setSystemInfo("Maven", "3.5.2");
		Reporter.setSystemInfo("Java Version", "1.8");
			
			 driver.quit();

	}
	
	public static void Screenshot() throws Exception {
		String screenshotName = "Step"+ ConfigFile.GetCurrentDateTime();
		try {
			
            fileName = ReusableComponents.GetAbsoluteFilePath(System.getProperty("user.dir"))+"/Cucumber-Test-Results"+ "/"+screenshotName + ".png";
            TakesScreenshot image =((TakesScreenshot)driver);
            File SrcFile=image.getScreenshotAs(OutputType.FILE);
          
            //Move image file to new destination
            System.out.println(fileName);
            File DestFile=new File(fileName);
            if(ConfigFile.GetAppConfig("screenShotPathFromLocal").contains("yes"))
                Reporter.addScreenCaptureFromPath("../Cucumber-Test-Results/"+screenshotName + ".png");
                else
                Reporter.addScreenCaptureFromPath(screenshotName + ".png");
            
            //Copy file at destination

            FileUtils.copyFile(SrcFile, DestFile);
           
            
		} 
		catch (Exception e) {
		} 
	}

	@BeforeSuite(alwaysRun = true)
	public static void GetScriptCount() throws SAXException, ParserConfigurationException {
		ReportModifier.setUpConfiguration();
	}
}
