package drivers;

public class DriverFactory {

    public static Driver getDriver(String platform) {
        return new MobileDriver(platform);
    }

}
