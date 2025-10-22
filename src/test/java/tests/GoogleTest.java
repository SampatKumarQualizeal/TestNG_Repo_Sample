package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.DriverManager;
import pages.GooglePage;

public class GoogleTest extends BaseTest {
    GooglePage googlePage;

    @Test
    public void testGoogleSearch() {
        String url = config.getUrl();
        String query = testData.getSearchQuery();
        googlePage = new GooglePage(driver);
        driver.get(url);
        log.info("Navigated to " + url);
        googlePage.enterSearchQuery(query);
//        googlePage.clickSearchButton();
        googlePage.sendKeys(googlePage.searchBox, "{ENTER}");
        log.info("Performed search with query: " + query);
    }

}
