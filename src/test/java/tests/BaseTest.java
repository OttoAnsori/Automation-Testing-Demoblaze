package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base class untuk semua test
 * Handle setup dan teardown WebDriver
 */
public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl = "https://www.demoblaze.com";

    @BeforeMethod
    public void setup() {
        // Specify lokasi EdgeDriver manual
        System.setProperty("webdriver.edge.driver", "C:\\WebDrivers\\msedgedriver.exe");

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        driver = new EdgeDriver(options);
        driver.get(baseUrl);
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            // Dismiss alert yang mungkin masih terbuka
            try {
                driver.switchTo().alert().accept();
            } catch (Exception e) {
                // Ignore
            }
            driver.quit();
        }
    }
}
