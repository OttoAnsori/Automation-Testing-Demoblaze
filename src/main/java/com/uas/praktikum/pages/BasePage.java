package com.uas.praktikum.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Base class untuk semua Page Object
 * Berisi fungsi umum yang dipakai semua page
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Mengambil teks dari alert yang muncul
     */
    public String getAlertText() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            return alert.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Klik OK pada alert
     */
    public void acceptAlert() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception e) {
            // Ignore jika alert tidak ada
        }
    }

    /**
     * Tunggu beberapa detik
     */
    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Dismiss alert jika ada (untuk cleanup antar test)
     */
    public void dismissAnyAlert() {
        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            // Ignore jika tidak ada alert
        }
    }
}


