package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// This Page Object represents the Contact Creation screen in the CRM system
public class CreateContactPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // TODO: Replace with actual locator for the Contact Name input field
    @FindBy(how = How.ID, using = "PLACEHOLDER_contact_name_input")
    private WebElement contactNameInput;

    // TODO: Replace with actual locator for the Save button
    @FindBy(how = How.ID, using = "PLACEHOLDER_save_contact_button")
    private WebElement saveContactButton;

    // TODO: Replace with actual locator for the success message
    @FindBy(how = How.ID, using = "PLACEHOLDER_success_message")
    private WebElement successMessage;

    // Constructor
    public CreateContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Enters the contact name in the input field
     * @param contactName Name of the contact to create
     */
    public void enterContactName(String contactName) {
        wait.until(ExpectedConditions.visibilityOf(contactNameInput));
        contactNameInput.clear();
        contactNameInput.sendKeys(contactName);
    }

    /**
     * Clicks the Save button to create the contact
     */
    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveContactButton));
        saveContactButton.click();
    }

    /**
     * Waits for the success message to appear after saving
     * @return true if success message is displayed, false otherwise
     */
    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Placeholder methods for additional steps (since there are 193 steps, but only 2 locators provided)
    // For each step without a locator, create a descriptive placeholder method
    // Example:
    /**
     * Placeholder for step: Navigate to the contact creation screen
     */
    public void navigateToContactCreationScreen() {
        // TODO: Implement navigation logic or use existing navigation utilities
    }

    /**
     * Placeholder for step: Verify user is logged in
     */
    public void verifyUserIsLoggedIn() {
        // TODO: Implement verification logic or use existing authentication utilities
    }

    // ... Add more placeholder methods as needed for each step in the test case
}
