package com.uas.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

/**
 * Page Object untuk Cart Page
 */
public class CartPage extends BasePage {

    // Locators
    private By placeOrderButton = By.xpath("//button[text()='Place Order']");
    private By nameField = By.id("name");
    private By countryField = By.id("country");
    private By cityField = By.id("city");
    private By creditCardField = By.id("card");
    private By monthField = By.id("month");
    private By yearField = By.id("year");
    private By purchaseButton = By.xpath("//button[text()='Purchase']");
    private By confirmationMessage = By.xpath("//h2[contains(text(),'Thank you')]");
    private By okButton = By.xpath("//button[text()='OK']");
    private By cartItems = By.cssSelector("#tbodyid tr");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Click Place Order button
     */
    public void clickPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
    }

    /**
     * Fill checkout form
     */
    public void fillCheckoutForm(String name, String country, String city,
                                 String card, String month, String year) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(countryField).sendKeys(country);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(creditCardField).sendKeys(card);
        driver.findElement(monthField).sendKeys(month);
        driver.findElement(yearField).sendKeys(year);
    }

    /**
     * Click Purchase button
     */
    public void clickPurchase() {
        driver.findElement(purchaseButton).click();
    }

    /**
     * Get confirmation message text
     */
    public String getConfirmationText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage)).getText();
    }

    /**
     * Click OK on confirmation dialog
     */
    public void clickOkConfirmation() {
        wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
    }

    /**
     * Get cart item count
     */
    public int getCartItemCount() {
        try {
            List<WebElement> items = driver.findElements(cartItems);
            return items.size();
        } catch (Exception e) {
            return 0;
        }
    }
}
