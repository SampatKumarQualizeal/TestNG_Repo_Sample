package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// This page object represents the Contact Creation screen in the CRM system
public class CreateContactPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // TODO: Replace with actual locator for 'First Name' field
    @FindBy(how = How.ID, using = "PLACEHOLDER_first_name_input")
    private WebElement firstNameInput;

    // TODO: Replace with actual locator for 'Last Name' field
    @FindBy(how = How.ID, using = "PLACEHOLDER_last_name_input")
    private WebElement lastNameInput;

    // TODO: Replace with actual locator for 'Email' field
    @FindBy(how = How.ID, using = "PLACEHOLDER_email_input")
    private WebElement emailInput;

    // TODO: Replace with actual locator for 'Phone Number' field
    @FindBy(how = How.ID, using = "PLACEHOLDER_phone_input")
    private WebElement phoneInput;

    // TODO: Replace with actual locator for 'Save' button
    @FindBy(how = How.ID, using = "PLACEHOLDER_save_button")
    private WebElement saveButton;

    // TODO: Replace with actual locator for success message
    @FindBy(how = How.ID, using = "PLACEHOLDER_success_message")
    private WebElement successMessage;

    public CreateContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Enters the first name in the contact creation form
     */
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    /**
     * Enters the last name in the contact creation form
     */
    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameInput));
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    /**
     * Enters the email in the contact creation form
     */
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    /**
     * Enters the phone number in the contact creation form
     */
    public void enterPhoneNumber(String phoneNumber) {
        wait.until(ExpectedConditions.visibilityOf(phoneInput));
        phoneInput.clear();
        phoneInput.sendKeys(phoneNumber);
    }

    /**
     * Clicks the Save button to create the contact
     */
    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }

    /**
     * Verifies if the success message is displayed after creating a contact
     */
    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Additional placeholder methods for other steps (since 193 steps are mentioned, but not detailed)
    // TODO: Implement methods for each specific step as per the generated feature file steps
    // Example placeholder:
    // public void performStepX() {
    //     // TODO: Implement step X
    // }
}
