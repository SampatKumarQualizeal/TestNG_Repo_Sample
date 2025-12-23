package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactCreationPage {
    private WebDriver driver;

    // Locators (from provided locator metadata)
    private By firstNameInput = By.name("first_name");
    private By lastNameInput = By.name("last_name");
    private By emailAddressInput = By.name("value");
    private By emailDescriptionInput = By.name("name");
    private By addEmailButton = By.className("ui tiny basic icon button");
    private By categoryDropdown = By.cssSelector("div[name='category'].ui.selection.dropdown");
    private By saveButton = By.xpath("//button[contains(@class, 'linkedin') and text()='Save']");
    // Placeholders for missing locators
    private By phoneNumberInput = By.xpath("//input[@name='phone']"); // TODO: Update with actual locator if available
    private By companyInput = By.xpath("//input[@name='company']"); // TODO: Update with actual locator if available

    // Locator for the email input field (placeholder, update with actual locator if available)
    private static final By EMAIL_INPUT = By.id("email");

    // Locator for the Save or Submit button (placeholder, update with actual locator if available)
    private static final By SAVE_BUTTON = By.id("saveContact");

    // Locator for the error message displayed for invalid email format (placeholder, update with actual locator if available)
    private static final By EMAIL_ERROR_MESSAGE = By.id("emailErrorMessage");

    public ContactCreationPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Complete workflow: Create a new contact with all required fields and save.
     *
     * @param firstName First Name
     * @param lastName Last Name
     * @param phoneNumber Phone Number
     * @param company Company Name
     * @param email Email Address
     * @param emailDescription Email Description (e.g., Personal, Business)
     * @param category Category to select (e.g., Lead, Customer, Contact, Affiliate)
     */
    public void createNewContact(String firstName, String lastName, String phoneNumber, String company, String email, String emailDescription, String category) {
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(firstName);

        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);

        // Phone Number (if present)
        try {
            WebElement phoneField = driver.findElement(phoneNumberInput);
            phoneField.clear();
            phoneField.sendKeys(phoneNumber);
        } catch (Exception ignored) {
            // Field might not be present; skip if so
        }

        // Company (if present)
        try {
            WebElement companyField = driver.findElement(companyInput);
            companyField.clear();
            companyField.sendKeys(company);
        } catch (Exception ignored) {
            // Field might not be present; skip if so
        }

        // Email Address
        driver.findElement(emailAddressInput).clear();
        driver.findElement(emailAddressInput).sendKeys(email);
        // Email Description
        driver.findElement(emailDescriptionInput).clear();
        driver.findElement(emailDescriptionInput).sendKeys(emailDescription);
        // Add Email (if required)
        try {
            driver.findElement(addEmailButton).click();
        } catch (Exception ignored) {
            // Button might not be required or present
        }

        // Category Dropdown
        if (category != null && !category.isEmpty()) {
            driver.findElement(categoryDropdown).click();
            By categoryOption = By.xpath("//div[@name='category' and contains(@class, 'item') and span[text()='" + category + "']]");
            driver.findElement(categoryOption).click();
        }

        // Save
        driver.findElement(saveButton).click();
    }

    /**
     * Attempts to create a new contact with an invalid email format and triggers validation.
     * This method enters the provided invalid email, submits the form, and waits for the error message.
     *
     * @param firstName         First name of the contact (if required by the form, else pass empty string)
     * @param lastName          Last name of the contact (if required by the form, else pass empty string)
     * @param invalidEmail      The invalid email string to test
     */
    public void attemptToCreateContactWithInvalidEmail(String firstName, String lastName, String invalidEmail) {
        // Enter first name if applicable
        // Placeholder: By.id("firstName")
        if (firstName != null && !firstName.isEmpty()) {
            driver.findElement(By.id("firstName")).clear();
            driver.findElement(By.id("firstName")).sendKeys(firstName);
        }
        // Enter last name if applicable
        if (lastName != null && !lastName.isEmpty()) {
            driver.findElement(By.id("lastName")).clear();
            driver.findElement(By.id("lastName")).sendKeys(lastName);
        }
        // Enter invalid email
        driver.findElement(EMAIL_INPUT).clear();
        driver.findElement(EMAIL_INPUT).sendKeys(invalidEmail);
        // Click Save/Submit to trigger validation
        driver.findElement(SAVE_BUTTON).click();
    }

    /**
     * Returns the error message displayed for invalid email format, if present.
     *
     * @return The error message text, or null if not displayed.
     */
    public String getInvalidEmailErrorMessage() {
        try {
            WebElement errorElement = driver.findElement(EMAIL_ERROR_MESSAGE);
            if (errorElement.isDisplayed()) {
                return errorElement.getText();
            }
        } catch (Exception e) {
            // Element not found or not visible
        }
        return null;
    }
}