package server;

import config.Properties;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServer {

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
        System.out.println("- - - - - -  Starting Appium Server - - - - - -");
    }

    public static void stop() {
        try {
            localService.stop();
            System.out.println("- - - - - -  Appium Server has been stopped - - - - - -");
        } catch (Exception e) {
            System.out.println("- - - - - -  Stopping Appium Server has failed - - - - - -");
        }
    }

    public static boolean isRunning() {
        if(localService != null) {
            return localService.isRunning();
        }
        return false;
    }

}
