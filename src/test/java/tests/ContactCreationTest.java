package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import pages.ContactCreationPage;
import utils.ConfigReader;
import utils.TestDataReader;
import org.openqa.selenium.WebDriver;

public class ContactCreationTest extends BaseTest {
    private ContactCreationPage contactCreationPage;
    private String[] invalidEmails;
    private String expectedErrorMessage;

    @BeforeMethod
    public void setUpTest() {
        // Load the CRM URL if available in config (not provided in this test case)
        // ConfigReader configReader = new ConfigReader();
        // String url = configReader.getUrl();
        // driver.get(url);
        // Assume preconditions: user is logged in and navigated to contact creation screen

        contactCreationPage = new ContactCreationPage(driver);
        // Load invalid email test data
        TestDataReader testDataReader = new TestDataReader("testdata_invalid_email_format_rejection.properties");
        invalidEmails = new String[] {
            testDataReader.getValue("invalidEmail1"),
            testDataReader.getValue("invalidEmail2"),
            testDataReader.getValue("invalidEmail3"),
            testDataReader.getValue("invalidEmail4"),
            testDataReader.getValue("invalidEmail5"),
            testDataReader.getValue("invalidEmail6"),
            testDataReader.getValue("invalidEmail7"),
            testDataReader.getValue("invalidEmail8"),
            testDataReader.getValue("invalidEmail9"),
            testDataReader.getValue("invalidEmail10"),
            testDataReader.getValue("invalidEmail11"),
            testDataReader.getValue("invalidEmail12"),
            testDataReader.getValue("invalidEmail13"),
            testDataReader.getValue("invalidEmail14"),
            testDataReader.getValue("invalidEmail15"),
            testDataReader.getValue("invalidEmail16"),
            testDataReader.getValue("invalidEmail17"),
            testDataReader.getValue("invalidEmail18"),
            testDataReader.getValue("invalidEmail19"),
            testDataReader.getValue("invalidEmail20")
        };
        expectedErrorMessage = testDataReader.getValue("expectedErrorMessage");
    }

    @Test(description = "TC-N002: Verify system rejects invalid email formats and displays appropriate error message.")
    public void testInvalidEmailFormatRejection() {
        for (String invalidEmail : invalidEmails) {
            try {
                contactCreationPage.attemptToCreateContactWithInvalidEmail(invalidEmail);
                String actualError = contactCreationPage.getEmailFormatErrorMessage();
                Assert.assertEquals(
                    actualError.trim(),
                    expectedErrorMessage,
                    "Error message mismatch for invalid email: '" + invalidEmail + "'"
                );
            } catch (Exception e) {
                Assert.fail("Exception occurred while testing invalid email: '" + invalidEmail + "' - " + e.getMessage());
            }
        }
    }
}
