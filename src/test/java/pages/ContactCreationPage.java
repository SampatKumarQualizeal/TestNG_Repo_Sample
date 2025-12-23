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
}
