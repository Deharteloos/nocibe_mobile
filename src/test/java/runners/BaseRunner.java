package runners;

import config.Properties;
import drivers.AppiumDriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.*;
import server.AppiumServer;

public class BaseRunner extends AbstractTestNGCucumberTests {

    @Parameters({"platform"})
    @BeforeClass
    public void beforeTest(@Optional String platform) {
        if(!AppiumServer.isRunning()) {
            AppiumServer.start();
        }
        AppiumDriverManager.setDriver(java.util.Optional
                .ofNullable(platform)
                .orElse(Properties.SYSTEM_PROPERTIES_READER.platformName));
    }

    @AfterClass
    public void tearDown() {
        AppiumDriverManager.getDriver().quit();
        AppiumServer.stop();
    }

}
