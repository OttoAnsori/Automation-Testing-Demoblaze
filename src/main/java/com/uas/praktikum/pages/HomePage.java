package com.uas.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object untuk halaman Home Demoblaze
 */
public class HomePage extends BasePage {

    // Locators
    private final By homeLink = By.linkText("Home");
    private final By loginButton = By.id("login2");
    private final By signupButton = By.id("signin2");
    private final By cartLink = By.id("cartur");
    private final By contactLink = By.linkText("Contact");
    private final By welcomeUser = By.id("nameofuser");
    private final By logoutButton = By.id("logout2");
    private final By categoriesSection = By.id("cat");

    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Klik tombol Login
     */
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    /**
     * Klik tombol Sign Up
     */
    public void clickSignUp() {
        wait.until(ExpectedConditions.elementToBeClickable(signupButton));
        driver.findElement(signupButton).click();
    }

    /**
     * Klik link Cart
     */
    public void clickCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink));
        driver.findElement(cartLink).click();
    }

    /**
     * Klik link Contact
     */
    public void clickContact() {
        wait.until(ExpectedConditions.elementToBeClickable(contactLink));
        driver.findElement(contactLink).click();
    }

    /**
     * Ambil teks welcome user
     */
    public String getWelcomeText() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUser));
            return driver.findElement(welcomeUser).getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Klik tombol Logout
     */
    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        driver.findElement(logoutButton).click();
    }

    /**
     * Cek apakah user sudah login
     */
    public boolean isUserLoggedIn() {
        try {
            return driver.findElement(welcomeUser).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Klik produk berdasarkan nama
     */
    public void clickProduct(String productName) {
        By productLink = By.linkText(productName);
        wait.until(ExpectedConditions.elementToBeClickable(productLink));
        driver.findElement(productLink).click();
    }
}
