package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import pages.ContactCreationPage;
import utils.ConfigReader;
import utils.TestDataReader;
import utils.TestDataUtils;
import java.util.Map;

public class ContactCreationTest extends BaseTest {
    private WebDriver driver;
    private ContactCreationPage contactCreationPage;
    private Map<String, Object> validContactData;

    @BeforeMethod
    public void setUp() {
        driver = getDriver();
        // Login and navigation handled by preconditions (assumed in BaseTest or test setup)
        // Load test data
        validContactData = TestDataReader.readJson("TC-N001-data.json", "validContact");
        // Navigate to Contact Creation Page if not handled by BaseTest
        contactCreationPage = new ContactCreationPage(driver);
    }

    @Test(description = "TC-N001: Create a new contact with valid data and verify success message")
    public void testCreateNewContact() {
        try {
            // Extract test data fields
            String firstName = (String) validContactData.get("firstName");
            String lastName = (String) validContactData.get("lastName");
            String phone = (String) validContactData.get("phone");
            String company = (String) validContactData.get("company");
            String email = (String) validContactData.get("email");
            // For this UI, emailDescription and category are optional/test-dependent
            String emailDescription = "Personal";
            String category = "Lead";

            // Use the workflow-level Page Object method
            contactCreationPage.createNewContact(firstName, lastName, phone, company, email, emailDescription, category);

            // Assert success message is displayed (locator and message may need adjustment)
            // Example: Assert that a toast or confirmation message appears
            // Replace below with actual locator/assertion as per application
            boolean isMessageDisplayed = driver.getPageSource().contains("Contact saved") ||
                                         driver.getPageSource().toLowerCase().contains("success");
            Assert.assertTrue(isMessageDisplayed, "Expected success message was not displayed after saving contact.");
        } catch (Exception e) {
            Assert.fail("Exception occurred during contact creation: " + e.getMessage(), e);
        }
    }

    @Test(description = "TC-N002: Verify system rejects invalid email formats and displays appropriate error message.")
    public void testInvalidEmailFormatRejection() {
        Map<String, Object> testData = (Map<String, Object>) TestDataUtils.getTestData("TC-N002");
        Map<String, Object> invalidEmails = (Map<String, Object>) testData.get("testData");
        String emailDescription = "Personal";
        String category = "Lead";
        for (Map.Entry<String, Object> entry : invalidEmails.entrySet()) {
            Map<String, String> data = (Map<String, String>) entry.getValue();
            String firstName = data.get("firstName");
            String lastName = data.get("lastName");
            String phone = data.get("phone");
            String company = data.get("company");
            String invalidEmail = data.get("email");
            try {
                boolean isErrorDisplayed = contactCreationPage.attemptToCreateContactWithInvalidEmail(
                        firstName,
                        lastName,
                        phone,
                        company,
                        invalidEmail,
                        emailDescription,
                        category
                );
                Assert.assertTrue(isErrorDisplayed, "Expected error message for invalid email format not displayed for scenario: " + entry.getKey());
            } catch (Exception e) {
                Assert.fail("Exception occurred while testing invalid email scenario '" + entry.getKey() + "': " + e.getMessage(), e);
            }
        }
    }
}