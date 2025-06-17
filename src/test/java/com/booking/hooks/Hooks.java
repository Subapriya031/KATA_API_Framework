package com.booking.hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Hooks {
    private static PrintStream ps;
    private static final String CONFIG_PATH = "src/test/resources/Config.properties";
    private static boolean isInitialized = false;
    private static Properties config;
    private static final File LOG_DIR = new File("target/logs");

    static {
        if (!isInitialized) {
            loadProperties();
            setupLogging();
            isInitialized = true;
        }
    }

    @Before
    public void setupRequest(Scenario scenario) throws InterruptedException {
        Thread.sleep(2000); // If you need to delay between requests

        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(config.getProperty("loginurl"))
                .setContentType("application/json")
                .addFilter(RequestLoggingFilter.logRequestTo(ps))
                .addFilter(ResponseLoggingFilter.logResponseTo(ps));
        RestAssured.requestSpecification = builder.build();
    }

    @After
    public void teardownRequest() {
        RestAssured.reset();
    }

// ---------------- Utility Methods ----------------

    private static void loadProperties() {
        config = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            config.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    private static void setupLogging() {
        try {
            if (!LOG_DIR.exists()) LOG_DIR.mkdirs();
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File logFile = new File(LOG_DIR, "api_log_" + timestamp + ".log");
            ps = new PrintStream(new FileOutputStream(logFile, true));
        } catch (IOException e) {
            throw new RuntimeException("Failed to set up logging", e);
        }
    }
}
