package runners;


import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/reports/htmlreport.html",
                "json:target/reports/jsonreports/index.json",
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"
        },
        features = {"src/test/resources/features"},
        glue = {"steps", "pageObjects"}
)
public class TestRunner extends BaseRunner{
}
