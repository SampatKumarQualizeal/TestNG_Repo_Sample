package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import pages.ContactCreationPage;
import utils.ConfigReader;
import utils.TestDataReader;
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
}
