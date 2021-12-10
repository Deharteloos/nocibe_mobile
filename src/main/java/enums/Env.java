package enums;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public enum Env {

    PRODUCTION;

    private static final String URLS_LOCATION = "config/urls.properties";

    private String envUrl;

    public static Env parse(String value) {
        return Env.valueOf(value);
    }

    public String getUrl(String endPoint) {
        String base = getUrl();
        return base + (base.endsWith("/") || endPoint.startsWith("/") ? "" : "/") + endPoint;
    }

    public String getUrl() {
        if (envUrl == null) {
            setUrl();
        }
        return envUrl;
    }

    private void setUrl(String envUrl) {
        this.envUrl = envUrl;
    }

    private void setUrl() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(URLS_LOCATION));
            Properties properties = new Properties();
            properties.load(reader);
            properties.forEach((key, value) -> {
                Env.valueOf(key.toString().toUpperCase()).setUrl(value.toString());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
