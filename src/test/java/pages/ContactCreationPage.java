package pages;

import base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactCreationPage extends BaseWebPage {
    private WebDriver driver;

    // Locators (placeholders, update with real values as needed)
    public By emailInput = By.id("contact-email"); // Placeholder locator for email input
    public By saveButton = By.id("save-contact"); // Placeholder locator for save button
    public By emailErrorMessage = By.id("email-error-message"); // Placeholder locator for email error message

    // Constructor
    public ContactCreationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Complete workflow: Attempts to create a contact with an invalid email and expects error message to be shown.
     * @param invalidEmail The invalid email string to input.
     */
    public void attemptToCreateContactWithInvalidEmail(String invalidEmail) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(invalidEmail);
        driver.findElement(saveButton).click();
        // No assertion here; test class should verify error message visibility/content
    }

    /**
     * Returns the error message displayed for invalid email format.
     * @return String error message text, or empty string if not present.
     */
    public String getEmailFormatErrorMessage() {
        try {
            return driver.findElement(emailErrorMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
