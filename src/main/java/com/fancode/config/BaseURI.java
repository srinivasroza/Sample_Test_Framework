package com.fancode.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

public class BaseURI {
    private BaseURI(){
    }
    private static final Logger logger = Logger.getLogger(BaseURI.class.getName());
    public static final String DATA_FILE_PATH = "src/test/resources/data.properties";
    protected static final Properties properties=new Properties();


    /**
     * Loads and returns properties from a configuration file.
     *
     * <p>This method reads key-value pairs from a properties file located at the path
     * specified by the constant <code>DATA_FILE_PATH</code>. It uses a {@link FileReader}
     * to load the data into a {@link Properties} object, which can then be used throughout
     * the application for configuration purposes.</p>
     *
     * <p>If the properties file is not found or cannot be read, an error message is logged,
     * and the application exits with a non-zero status code.</p>
     *
     * @return A {@link Properties} object containing the loaded configuration.
     * @throws RuntimeException If the file cannot be found or read (application exits).
     */
    public static Properties getProperties() {
        try (FileReader fileReader = new FileReader(DATA_FILE_PATH)) {
            properties.load(Objects.requireNonNull(fileReader));
        }catch(IOException e) {
            logger.severe("------data.properties File not found-------------");
            System.exit(1);
        }
        return properties;
    }

    /**
     * Retrieves the base URL for API requests from the properties file.
     *
     * <p>This method reads the <code>BASE_URL</code> property from the configuration
     * loaded via the {@link #getProperties()} method. It is typically used to build
     * complete API endpoints for HTTP requests.</p>
     *
     * @return A {@link String} representing the base URL for API calls,
     *         as defined in the <code>data.properties</code> file.
     */

    public static String getBaseURL() {
        return getProperties().getProperty("BASE_URL");
    }
}
