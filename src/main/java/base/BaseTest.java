package base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.DriverManager;
import utils.LoggerHelper;
import utils.TestDataReader;
import org.testng.annotations.*;

public class BaseTest {
    protected Logger log = LoggerHelper.getLogger(this.getClass());
    protected ConfigReader config = new ConfigReader();
    protected TestDataReader testData = new TestDataReader();
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        DriverManager.setDriver();
        driver = DriverManager.getDriver();
        log.info("Browser opened");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
        log.info("Browser closed");
    }


}
