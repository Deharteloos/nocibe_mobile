package drivers;

public class DriverFactory {

    public static MobileDriver getDriver(String platform) {
        return new MobileDriver(platform);
    }

}
