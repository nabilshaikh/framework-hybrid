package testrunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="Features",
			     glue={"hybrid.stepdefinitions"},
			     plugin= {"pretty"},
			     dryRun=false,
			     monochrome=true
				 )

public class TestRun {}
