package com.uas.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object untuk halaman detail produk
 */
public class ProductPage extends BasePage {

    // Locators
    private final By addToCartButton = By.linkText("Add to cart");
    private final By productTitle = By.className("name");
    private final By productPrice = By.className("price-container");

    // Constructor
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Klik tombol Add to Cart
     */
    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        driver.findElement(addToCartButton).click();
    }

    /**
     * Click Add to Cart button (alias untuk clickAddToCart)
     */
    public void addToCart() {
        clickAddToCart();
    }

    /**
     * Accept alert after add to cart
     */
    public void acceptAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            System.out.println("No alert present");
        }
    }

    /**
     * Ambil nama produk
     */
    public String getProductTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));
        return driver.findElement(productTitle).getText();
    }

    /**
     * Ambil harga produk
     */
    public String getProductPrice() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice));
        return driver.findElement(productPrice).getText();
    }
}
