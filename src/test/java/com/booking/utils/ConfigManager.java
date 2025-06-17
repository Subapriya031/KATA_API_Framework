package com.booking.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static final String CONFIG_FILE_PATH = "./src/test/resources/Config.properties";
    private static final Properties properties = new Properties();


    static {
        try (FileInputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config: " + CONFIG_FILE_PATH, e);
        }
    }

    private ConfigManager() {
        // prevent instantiation
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

}
