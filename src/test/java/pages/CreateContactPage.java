package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// Reusing existing page object structure and patterns from GooglePage.java
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
     * Enter first name in the contact creation form
     * @param firstName String
     */
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    /**
     * Enter last name in the contact creation form
     * @param lastName String
     */
    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameInput));
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    /**
     * Enter email in the contact creation form
     * @param email String
     */
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    /**
     * Click the Save button to create a new contact
     */
    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }

    /**
     * Verify that the success message is displayed after saving contact
     * @return boolean
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
