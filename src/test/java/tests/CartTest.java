package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.uas.praktikum.pages.HomePage;
import com.uas.praktikum.pages.LoginPage;
import com.uas.praktikum.pages.ProductPage;
import com.uas.praktikum.pages.CartPage;

/**
 * Test class untuk Cart dan Checkout functionality
 */
public class CartTest extends BaseTest {

    @Test(priority = 1, description = "TC-CT-P01: Checkout dengan user login")
    public void testCheckoutSuccess() {
        // Dismiss alert jika ada dari test sebelumnya
        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            // Ignore
        }

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        System.out.println("Testing checkout normal flow");

        // Login dulu
        homePage.clickLogin();
        homePage.waitFor(1);
        loginPage.login("fathan2424", "qwerty");
        homePage.waitFor(2);

        // Add product ke cart
        homePage.clickProduct("Samsung galaxy s6");
        homePage.waitFor(1);

        productPage.addToCart();
        homePage.waitFor(1);
        productPage.acceptAlert();

        // Checkout
        homePage.clickCart();
        homePage.waitFor(1);

        cartPage.clickPlaceOrder();
        homePage.waitFor(1);

        cartPage.fillCheckoutForm("John Doe", "USA", "New York",
                "1234567890123456", "12", "2025");
        cartPage.clickPurchase();
        homePage.waitFor(1);

        String confirmationText = cartPage.getConfirmationText();
        System.out.println("Confirmation: " + confirmationText);

        Assert.assertTrue(confirmationText.contains("Thank you"),
                "Checkout failed!");

        cartPage.clickOkConfirmation();
        System.out.println("Checkout completed successfully");
    }

    @Test(priority = 2, description = "TC-CT-N01: Checkout dengan cart kosong (BUG)")
    public void testCheckoutEmptyCart() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CartPage cartPage = new CartPage(driver);

        System.out.println("Testing checkout dengan cart kosong (expected: BUG)");

        // Login
        homePage.clickLogin();
        homePage.waitFor(1);
        loginPage.login("fathan2424", "qwerty");
        homePage.waitFor(2);

        // Langsung ke cart tanpa add product
        homePage.clickCart();
        homePage.waitFor(1);

        // Cek jumlah item di cart (expected: 0)
        int cartItems = cartPage.getCartItemCount();
        System.out.println("Cart item count: " + cartItems);

        // Coba checkout walau cart kosong
        cartPage.clickPlaceOrder();
        homePage.waitFor(1);

        cartPage.fillCheckoutForm("Test User", "Indonesia", "Jakarta",
                "1234567890123456", "11", "2026");
        cartPage.clickPurchase();
        homePage.waitFor(2);

        try {
            String confirmationText = cartPage.getConfirmationText();

            // Ini adalah BUG! Seharusnya tidak bisa checkout dengan cart kosong
            System.out.println("BUG-001: Order berhasil dibuat walau cart kosong");
            System.out.println("Confirmation: " + confirmationText);

            Assert.assertTrue(confirmationText.contains("Thank you"),
                    "BUG confirmed: Empty cart checkout allowed");

            cartPage.clickOkConfirmation();
        } catch (Exception e) {
            System.out.println("Expected: Cart kosong tidak bisa checkout");
        }
    }

    @Test(priority = 3, description = "TC-CT-N02: Checkout tanpa login (BUG)")
    public void testCheckoutWithoutLogin() {
        // Dismiss alert jika ada dari test sebelumnya
        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            // Ignore
        }

        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        System.out.println("Testing checkout tanpa login (expected: BUG)");

        // JANGAN login, langsung add product
        homePage.clickProduct("Samsung galaxy s6");
        homePage.waitFor(1);

        productPage.addToCart();
        homePage.waitFor(1);
        productPage.acceptAlert();

        // Checkout
        homePage.clickCart();
        homePage.waitFor(1);

        cartPage.clickPlaceOrder();
        homePage.waitFor(1);

        cartPage.fillCheckoutForm("Guest User", "USA", "LA",
                "1234567890123456", "11", "2026");
        cartPage.clickPurchase();
        homePage.waitFor(2);

        try {
            String confirmationText = cartPage.getConfirmationText();
            System.out.println("BUG-002: Checkout berhasil tanpa login!");
            System.out.println("Confirmation: " + confirmationText);

            // Bug: seharusnya gagal atau diminta login
            Assert.assertTrue(confirmationText.contains("Thank you"),
                    "BUG confirmed: Checkout allowed without login");

            cartPage.clickOkConfirmation();
        } catch (Exception e) {
            System.out.println("Expected: Checkout should require login");
        }
    }
}
