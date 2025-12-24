package com.uas.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object untuk modal Contact
 */
public class ContactPage extends BasePage {

    // Locators
    private final By emailField = By.id("recipient-email");
    private final By nameField = By.id("recipient-name");
    private final By messageField = By.id("message-text");
    private final By sendButton = By.xpath("//button[text()='Send message']");
    private final By closeButton = By.xpath("//div[@id='exampleModal']//button[text()='Close']");

    // Constructor
    public ContactPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Isi email
     */
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    /**
     * Isi name
     */
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }

    /**
     * Isi message
     */
    public void enterMessage(String message) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageField));
        driver.findElement(messageField).clear();
        driver.findElement(messageField).sendKeys(message);
    }

    /**
     * Klik tombol Send
     */
    public void clickSend() {
        wait.until(ExpectedConditions.elementToBeClickable(sendButton));
        driver.findElement(sendButton).click();
    }

    /**
     * Complete contact flow
     */
    public void sendMessage(String email, String name, String message) {
        enterEmail(email);
        enterName(name);
        enterMessage(message);
        clickSend();
    }
}
