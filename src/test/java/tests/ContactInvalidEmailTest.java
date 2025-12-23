package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import pages.ContactCreationPage;
import utils.ConfigReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class ContactInvalidEmailTest extends BaseTest {
    private WebDriver driver;
    private ContactCreationPage contactCreationPage;
    private static final String TEST_DATA_PATH = "src/test/resources/TC-N002-data.json";

    @BeforeMethod
    public void setUp() {
        driver = this.driver; // Provided by BaseTest
        // Load CRM URL if required (not specified in test data, so skipping)
        contactCreationPage = new ContactCreationPage(driver);
    }

    @DataProvider(name = "invalidEmailDataProvider")
    public Iterator<Object[]> invalidEmailDataProvider() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> root = mapper.readValue(new FileReader(TEST_DATA_PATH), new TypeReference<Map<String, Object>>(){});
        Map<String, Object> testData = (Map<String, Object>) root.get("testData");
        // Combine all invalid email cases from all categories
        Object[] testSets = new Object[] { "invalidEmailFormats", "boundaryValues", "edgeCases" };
        java.util.List<Object[]> data = new java.util.ArrayList<>();
        for (Object setNameObj : testSets) {
            String setName = (String) setNameObj;
            Map<String, Object> set = (Map<String, Object>) testData.get(setName);
            String firstName = (String) set.get("firstName");
            String lastName = (String) set.get("lastName");
            List<String> emailCases = (List<String>) set.get("emailCases");
            for (String invalidEmail : emailCases) {
                data.add(new Object[] { firstName, lastName, invalidEmail });
            }
        }
        return data.iterator();
    }

    @Test(dataProvider = "invalidEmailDataProvider", description = "TC-N002: Verify system rejects invalid email formats and displays appropriate error message.")
    public void testInvalidEmailFormatRejection(String firstName, String lastName, String invalidEmail) {
        try {
            contactCreationPage.attemptToCreateContactWithInvalidEmail(firstName, lastName, invalidEmail);
            String errorMsg = contactCreationPage.getInvalidEmailErrorMessage();
            Assert.assertNotNull(errorMsg, "Expected error message for invalid email, but none was displayed. Email: " + invalidEmail);
            Assert.assertTrue(errorMsg.toLowerCase().contains("invalid") || errorMsg.toLowerCase().contains("email"),
                    "Error message does not indicate invalid email format. Actual: " + errorMsg);
        } catch (Exception e) {
            Assert.fail("Exception occurred during invalid email format rejection test for email: " + invalidEmail + ". Exception: " + e.getMessage());
        }
    }
}
