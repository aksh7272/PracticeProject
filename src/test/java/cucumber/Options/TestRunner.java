package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;



@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = {"stepDefinitions"}
//        plugin = {"pretty", "html:target/cucumber-html-report","json:cucumber.json"},
//        tags = {"not @wip", "@discount"}
) 
public class TestRunner {

}
