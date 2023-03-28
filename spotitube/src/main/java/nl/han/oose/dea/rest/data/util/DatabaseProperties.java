package nl.han.oose.dea.rest.data.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseProperties {
    private static final String PROPERTY_FILE = "database.properties";

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTY_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDbUrl() {
        return properties.getProperty("DB_URL");
    }

    public static String getDbUser() {
        return properties.getProperty("DB_USER");
    }

    public static String getDbPassword() {
        return properties.getProperty("DB_PASSWORD");
    }
}