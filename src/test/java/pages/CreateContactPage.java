package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// This page object represents the Contact Creation screen in the CRM system.
public class CreateContactPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // TODO: Replace with actual locator for the 'First Name' input field
    @FindBy(how = How.ID, using = "PLACEHOLDER_firstNameInput")
    private WebElement firstNameInput;

    // TODO: Replace with actual locator for the 'Last Name' input field
    @FindBy(how = How.ID, using = "PLACEHOLDER_lastNameInput")
    private WebElement lastNameInput;

    // TODO: Replace with actual locator for the 'Email' input field
    @FindBy(how = How.ID, using = "PLACEHOLDER_emailInput")
    private WebElement emailInput;

    // TODO: Replace with actual locator for the 'Save' button
    @FindBy(how = How.ID, using = "PLACEHOLDER_saveButton")
    private WebElement saveButton;

    // TODO: Replace with actual locator for the success message
    @FindBy(how = How.ID, using = "PLACEHOLDER_successMessage")
    private WebElement successMessage;

    public CreateContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Enters the first name in the contact form.
     * @param firstName The first name to enter.
     */
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    /**
     * Enters the last name in the contact form.
     * @param lastName The last name to enter.
     */
    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameInput));
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    /**
     * Enters the email in the contact form.
     * @param email The email to enter.
     */
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    /**
     * Clicks the Save button to create the contact.
     */
    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }

    /**
     * Verifies if the success message is displayed after creating a contact.
     * @return true if success message is displayed, false otherwise.
     */
    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Placeholder methods for additional steps as per the 193 test steps
    // TODO: Implement methods for all remaining steps as per the test case TC-N001
    // Example:
    // public void enterPhoneNumber(String phoneNumber) { ... }
    // public void selectContactType(String type) { ... }
    // ...
}
