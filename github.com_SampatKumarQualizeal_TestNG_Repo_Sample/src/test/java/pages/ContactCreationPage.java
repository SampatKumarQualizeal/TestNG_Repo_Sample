package pages;

import base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactCreationPage extends BaseWebPage {
    private WebDriver driver;

    // Locators (from provided locator data)
    private By firstNameInput = By.name("first_name");
    private By lastNameInput = By.name("last_name");
    private By emailAddressInput = By.name("value");
    private By emailNameInput = By.name("name");
    private By addEmailButton = By.xpath("//button[@class='ui tiny basic icon button']");
    private By categoryDropdown = By.xpath("//div[@name='category' and @role='listbox']");
    private By saveButton = By.xpath("//button[@class='ui linkedin button' and contains(., 'Save')]");

    // Constructor
    public ContactCreationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Complete workflow to create a new contact with the provided details.
     * @param firstName First Name of the contact
     * @param lastName Last Name of the contact
     * @param emailAddress Email address (main input)
     * @param emailType Email type/name (e.g., Personal, Business)
     * @param category Category to select (e.g., Lead, Customer, Contact, Affiliate)
     */
    public void createNewContact(String firstName, String lastName, String emailAddress, String emailType, String category) {
        // Enter first name
        driver.findElement(firstNameInput).sendKeys(firstName);
        // Enter last name
        driver.findElement(lastNameInput).sendKeys(lastName);
        // Enter email address
        driver.findElement(emailAddressInput).sendKeys(emailAddress);
        // Enter email type/name if provided
        if (emailType != null && !emailType.isEmpty()) {
            driver.findElement(emailNameInput).sendKeys(emailType);
        }
        // Optionally add the email if the UI requires clicking the add button
        // driver.findElement(addEmailButton).click();
        // Select category if provided
        if (category != null && !category.isEmpty()) {
            driver.findElement(categoryDropdown).click();
            By categoryOption = By.xpath("//div[@name='category' and @role='option']//span[@class='text' and text()='" + category + "']");
            driver.findElement(categoryOption).click();
        }
        // Click Save
        driver.findElement(saveButton).click();
    }
}
