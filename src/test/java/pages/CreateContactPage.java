package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// Reuse existing BaseWebPage for common page functionality
import base.BaseWebPage;

public class CreateContactPage extends BaseWebPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // TODO: Replace with actual locator for 'First Name' field
    @FindBy(how = How.ID, using = "PLACEHOLDER_first_name_field")
    private WebElement firstNameField;

    // TODO: Replace with actual locator for 'Last Name' field
    @FindBy(how = How.ID, using = "PLACEHOLDER_last_name_field")
    private WebElement lastNameField;

    // TODO: Replace with actual locator for 'Email' field
    @FindBy(how = How.ID, using = "PLACEHOLDER_email_field")
    private WebElement emailField;

    // TODO: Replace with actual locator for 'Save' button
    @FindBy(how = How.ID, using = "PLACEHOLDER_save_button")
    private WebElement saveButton;

    // TODO: Replace with actual locator for 'Success Message'
    @FindBy(how = How.ID, using = "PLACEHOLDER_success_message")
    private WebElement successMessage;

    public CreateContactPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Enter first name in the contact creation form
     * @param firstName String first name
     */
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    /**
     * Enter last name in the contact creation form
     * @param lastName String last name
     */
    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameField));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    /**
     * Enter email in the contact creation form
     * @param email String email
     */
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
    }

    /**
     * Click the Save button to create the contact
     */
    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }

    /**
     * Verify if the success message is displayed after creating a contact
     * @return boolean true if success message is displayed
     */
    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Placeholder for additional actions based on the 193 steps in the test case
    // TODO: Implement all required actions as per the detailed test steps
    // For each step, create a descriptive method and placeholder locator if needed
}
