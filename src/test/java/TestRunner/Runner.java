package TestRunner;		

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;		

@CucumberOptions(features="Features/Address.feature",glue={"StepDefinition"},plugin ={"com.cucumber.listener.ExtentCucumberFormatter:Cucumber-Test-Results/Cucumber-Report.html"})						
public class Runner extends AbstractTestNGCucumberTests				
{		

}