package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//import io.cucumber.core.snippets.SnippetType;
import io.cucumber.testng.CucumberOptions.SnippetType;;


@CucumberOptions(
    // Should find all the feature files in the folder
    features = "src/test/resources/featureFiles/",
    // The glue needs the package name in which the step definitions classes live. 
    glue = "stepDefintions",
    dryRun = true,
    snippets = SnippetType.CAMELCASE,
    monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests{
    
}
