package pages;

import base.BaseWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GooglePage extends BaseWebPage {
    private WebDriver driver;

    // Locators (no @FindBy or PageFactory)
    public By searchBox = By.name("q");
    public By searchButton = By.name("btnK");

    // Constructor
    public GooglePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Actions on the page
    public void enterSearchQuery(String query) {
        driver.findElement(searchBox).sendKeys(query);
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }
}
