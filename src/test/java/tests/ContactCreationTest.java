package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactCreationPage;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.TestDataReader;
import java.util.List;
import java.util.Map;

public class ContactCreationTest extends BaseTest {

    private ContactCreationPage contactCreationPage;
    private String baseUrl;
    private Map<String, Object> testData;

    @BeforeMethod
    public void setUp() {
        // Load base URL from config if available, else use test data (none specified)
        baseUrl = ConfigReader.getProperty("base.url");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            driver.get(baseUrl);
        }
        // Login and navigate to Contact Creation screen as per preconditions
        // Assuming login and navigation handled in BaseTest or utility methods
        // If not, add login and navigation steps here using appropriate Page Objects
        contactCreationPage = new ContactCreationPage(driver);
        // Load test data for TC-N002
        testData = TestDataReader.getTestData("TC-N002-data.json");
    }

    @Test(description = "TC-N002: Invalid Email Format Rejection - System should reject invalid email formats and display appropriate error message.")
    public void testInvalidEmailFormatRejection() {
        try {
            String firstName = ((Map<String, String>)testData.get("testData")).getOrDefault("validName", "John");
            String lastName = "Doe";
            String emailType = "Personal";
            String category = "Lead";
            List<String> invalidEmails = (List<String>) ((Map<String, Object>)testData.get("testData")).get("invalidEmails");
            String expectedErrorMsg = (String) testData.getOrDefault("expectedErrorMessage", "Please enter a valid email address.");

            for (String invalidEmail : invalidEmails) {
                // Refresh or navigate to Contact Creation page for each iteration if needed
                driver.navigate().refresh();
                // Attempt to create contact with invalid email
                contactCreationPage.attemptToCreateContactWithInvalidEmail(
                        firstName,
                        lastName,
                        invalidEmail,
                        emailType,
                        category
                );
                // Validate error message
                String actualErrorMsg = contactCreationPage.getEmailFormatErrorMessage();
                Assert.assertNotNull(actualErrorMsg, "Error message should be displayed for invalid email: " + invalidEmail);
                Assert.assertTrue(
                        actualErrorMsg.toLowerCase().contains("valid") || actualErrorMsg.toLowerCase().contains("email"),
                        "Expected error message about invalid email, but got: '" + actualErrorMsg + "' for input: " + invalidEmail
                );
            }
        } catch (Exception e) {
            Assert.fail("Test failed due to unexpected exception: " + e.getMessage(), e);
        }
    }
}
