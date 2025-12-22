package pages;

import base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactCreationPage extends BaseWebPage {

    // Locators
    private final By firstNameInput = By.name("first_name");
    private final By lastNameInput = By.name("last_name");
    private final By emailAddressInput = By.name("value");
    private final By emailNameInput = By.name("name");
    private final By addEmailButton = By.xpath("//button[contains(@class, 'icon button')]");
    private final By categoryDropdown = By.xpath("//div[@class='ui selection dropdown']");
    private final By saveButton = By.xpath("//button[contains(@class, 'linkedin button') and text()='Save']");
    // Placeholder for error message locator (since not provided)
    private final By emailErrorMessage = By.xpath("//span[contains(@class, 'inline-error-msg') and contains(text(), 'email')]" /* TODO: Update with actual error locator */);

    public ContactCreationPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Complete workflow for attempting to create a contact with invalid email format.
     * Fills all required fields, enters invalid email, and clicks Save.
     * This method covers the user flow for test case TC-N002: Invalid Email Format Rejection.
     *
     * @param firstName      The first name to enter
     * @param lastName       The last name to enter
     * @param emailAddress   The invalid email address to enter (e.g., "invalidemail.com")
     * @param emailType      The email type/name (e.g., "Personal")
     * @param category       The category to select (e.g., "Lead")
     */
    public void attemptToCreateContactWithInvalidEmail(String firstName, String lastName, String emailAddress, String emailType, String category) {
        // Enter first name
        driver.findElement(firstNameInput).sendKeys(firstName);
        // Enter last name
        driver.findElement(lastNameInput).sendKeys(lastName);
        // Enter email address
        driver.findElement(emailAddressInput).sendKeys(emailAddress);
        // Enter email type/name
        driver.findElement(emailNameInput).sendKeys(emailType);
        // Click add email button
        driver.findElement(addEmailButton).click();
        // Select category from dropdown
        driver.findElement(categoryDropdown).click();
        // Select the category item (by visible text)
        driver.findElement(By.xpath("//div[@class='menu transition']//div[@role='option']//span[text()='" + category + "']")).click();
        // Click Save button
        driver.findElement(saveButton).click();
    }

    /**
     * Returns the error message displayed for invalid email format.
     * @return The error message text, or null if not present.
     */
    public String getEmailFormatErrorMessage() {
        try {
            return driver.findElement(emailErrorMessage).getText();
        } catch (Exception e) {
            return null;
        }
    }
}
