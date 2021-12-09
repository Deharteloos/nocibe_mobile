package config;

public enum SystemPropertiesReader {

    INSTANCE;

    public final String platformName;
    public final String platformVersion;
    public final String deviceName;
    public final boolean onBrowser;
    public final Env env;

    SystemPropertiesReader() {
        env = Env.parse(System.getProperty("env", "production").toUpperCase());
        platformName = System.getProperty("platformName", "Android");
        platformVersion = System.getProperty("platformVersion", "9.0");
        deviceName = System.getProperty("deviceName", "Android Emulator");
        onBrowser = Boolean.parseBoolean(System.getProperty("onBrowser", "true"));
    }

    public static SystemPropertiesReader getInstance() {
        return INSTANCE;
    }

}
