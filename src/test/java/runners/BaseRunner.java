package runners;

import config.Properties;
import drivers.AppiumDriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.*;

public class BaseRunner extends AbstractTestNGCucumberTests {

    @Parameters({"platform"})
    @BeforeClass
    public void beforeTest(@Optional String platform) {
        AppiumDriverManager.setDriver(java.util.Optional
                .ofNullable(platform)
                .orElse(Properties.SYSTEM_PROPERTIES_READER.platformName));
    }

    @AfterClass
    public void tearDown() {
        AppiumDriverManager.getDriver().quit();
    }

}
