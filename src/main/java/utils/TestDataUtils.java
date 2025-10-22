package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class TestDataUtils {
    private static final Logger logger = LogManager.getLogger(TestDataUtils.class);
    private static final String TEST_DATA_PATH = "src/test/resources/test-data.json";
    private static Map<String, Object> testDataJson;

    // Static block to load test data once at startup
    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            testDataJson = mapper.readValue(new File(TEST_DATA_PATH), new TypeReference<Map<String, Object>>() {});
            logger.info("Test data loaded successfully from " + TEST_DATA_PATH);
        } catch (Exception e) {
            logger.error("Failed to load test data from " + TEST_DATA_PATH, e);
            throw new RuntimeException(e);
        }
    }

    public static Object getTestData(String key) {
        logger.debug("Getting test data for key: " + key);

        if (!testDataJson.containsKey(key)) {
            logger.error("Test data not found for key: " + key);
            throw new IllegalArgumentException("Test data not found for key: " + key);
        }

        Object data = testDataJson.get(key);
        logger.debug("Successfully retrieved test data for key: " + key);
        return data;
    }

    public static Map<String, Object> getAllTestData() {
        logger.debug("Getting all test data");
        return testDataJson;
    }

    public static boolean hasTestDataKey(String key) {
        logger.debug("Checking if test data key exists: " + key);
        boolean exists = testDataJson.containsKey(key);
        logger.debug("Test data key '" + key + "' exists: " + exists);
        return exists;
    }

    public static Set<String> getAvailableKeys() {
        logger.debug("Getting available test data keys");
        Set<String> keys = testDataJson.keySet();
        logger.debug("Available test data keys: " + keys);
        return keys;
    }
}
