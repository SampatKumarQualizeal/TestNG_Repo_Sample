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
// It follows the existing page object pattern as seen in GooglePage.java.
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

    // TODO: Replace with actual locator for the 'Phone' input field
    @FindBy(how = How.ID, using = "PLACEHOLDER_phoneInput")
    private WebElement phoneInput;

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
     * Enters the first name in the contact creation form.
     * @param firstName The first name to enter.
     */
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    /**
     * Enters the last name in the contact creation form.
     * @param lastName The last name to enter.
     */
    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameInput));
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    /**
     * Enters the email in the contact creation form.
     * @param email The email to enter.
     */
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    /**
     * Enters the phone number in the contact creation form.
     * @param phone The phone number to enter.
     */
    public void enterPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOf(phoneInput));
        phoneInput.clear();
        phoneInput.sendKeys(phone);
    }

    /**
     * Clicks the Save button to create the new contact.
     */
    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }

    /**
     * Checks if the success message is displayed after saving the contact.
     * @return true if the message is displayed, false otherwise
     */
    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Additional placeholder methods for each step in the test case can be added here as needed.
    // For the full 193 steps, replicate the above pattern: create a method per step, with a descriptive name and placeholder locator if needed.
}
