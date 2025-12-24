package com.uas.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object untuk modal Sign Up
 */
public class SignUpPage extends BasePage {

    // Locators
    private final By usernameField = By.id("sign-username");
    private final By passwordField = By.id("sign-password");
    private final By signUpButton = By.xpath("//button[text()='Sign up']");
    private final By closeButton = By.xpath("//div[@id='signInModal']//button[text()='Close']");

    // Constructor
    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Isi username
     */
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    /**
     * Isi password
     */
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    /**
     * Klik tombol Sign Up
     */
    public void clickSignUpButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
        driver.findElement(signUpButton).click();
    }

    /**
     * Complete sign up flow
     */
    public void signUp(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSignUpButton();
    }
}
