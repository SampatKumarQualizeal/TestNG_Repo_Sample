package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseWebPage {

    private WebDriver driver = null;

    public BaseWebPage(WebDriver driver) {
        this.driver = driver;
    }

    public void sendKeys(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }
}
