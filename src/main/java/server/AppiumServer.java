package server;

import config.Properties;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppiumServer {

    private static final Logger LOG = LogManager.getLogger(AppiumServer.class);
    private static AppiumDriverLocalService localService;

    public static void start() {
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder()
                .withIPAddress(Properties.CONFIG_PROPERTIES_READER.appiumServerHost)
                .usingPort(Properties.CONFIG_PROPERTIES_READER.appiumServerPort)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.RELAXED_SECURITY);

        localService = AppiumDriverLocalService.buildService(serviceBuilder);
        localService.start();
        LOG.info("===============================================");
        LOG.info("- - - - - -  Starting Appium Server - - - - - -");
        LOG.info("===============================================");
    }

    public static void stop() {
        try {
            localService.stop();
            LOG.info("=======================================================");
            LOG.info("- - - - - -  Appium Server has been stopped - - - - - -");
            LOG.info("=======================================================");
        } catch (Exception e) {
            LOG.warn("==========================================================");
            LOG.warn("- - - - - -  Stopping Appium Server has failed - - - - - -");
            LOG.warn("==========================================================");
        }
    }

    public static boolean isRunning() {
        if(localService != null) {
            return localService.isRunning();
        }
        return false;
    }

}
