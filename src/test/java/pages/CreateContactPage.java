package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// Reuses the existing page object pattern from GooglePage.java
public class CreateContactPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // TODO: Replace with actual locator for the contact name field
    @FindBy(how = How.ID, using = "PLACEHOLDER_contact_name_field")
    private WebElement contactNameField;

    // TODO: Replace with actual locator for the contact email field
    @FindBy(how = How.ID, using = "PLACEHOLDER_contact_email_field")
    private WebElement contactEmailField;

    // TODO: Replace with actual locator for the contact phone field
    @FindBy(how = How.ID, using = "PLACEHOLDER_contact_phone_field")
    private WebElement contactPhoneField;

    // TODO: Replace with actual locator for the save button
    @FindBy(how = How.ID, using = "PLACEHOLDER_save_contact_button")
    private WebElement saveContactButton;

    // TODO: Replace with actual locator for the success message
    @FindBy(how = How.ID, using = "PLACEHOLDER_success_message")
    private WebElement successMessage;

    public CreateContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Enters the contact name in the contact creation form
     * @param name Contact name
     */
    public void enterContactName(String name) {
        wait.until(ExpectedConditions.visibilityOf(contactNameField));
        contactNameField.clear();
        contactNameField.sendKeys(name);
    }

    /**
     * Enters the contact email in the contact creation form
     * @param email Contact email
     */
    public void enterContactEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(contactEmailField));
        contactEmailField.clear();
        contactEmailField.sendKeys(email);
    }

    /**
     * Enters the contact phone in the contact creation form
     * @param phone Contact phone number
     */
    public void enterContactPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOf(contactPhoneField));
        contactPhoneField.clear();
        contactPhoneField.sendKeys(phone);
    }

    /**
     * Clicks the save button to create the contact
     */
    public void clickSaveContact() {
        wait.until(ExpectedConditions.elementToBeClickable(saveContactButton));
        saveContactButton.click();
    }

    /**
     * Checks if the success message is displayed after creating a contact
     * @return true if displayed, false otherwise
     */
    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Additional placeholder methods for other steps can be added here as needed
}
