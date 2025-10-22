package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataReader {
    private Properties properties = new Properties();

    public TestDataReader() {
        try (FileInputStream fis = new FileInputStream("src/test/resources/testdata.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load testdata.properties");
        }
    }

    public String getSearchQuery() {
        return properties.getProperty("searchQuery");
    }

    // Add more getter methods for other test data keys
}
