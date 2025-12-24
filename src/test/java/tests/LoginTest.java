package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.uas.praktikum.pages.HomePage;
import com.uas.praktikum.pages.LoginPage;

/**
 * Test class untuk Login functionality
 */
public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "TC-LG-P01: Login dengan kredensial valid")
    public void testLoginValid() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        System.out.println("Testing login dengan username: fathan2424");

        homePage.clickLogin();
        homePage.waitFor(1);

        loginPage.login("fathan2424", "qwerty");
        homePage.waitFor(3);  // ← TAMBAH WAIT LEBIH LAMA (dari 2 jadi 3 detik)

        String welcomeText = homePage.getWelcomeText();
        System.out.println("Welcome text: " + welcomeText);

        Assert.assertTrue(welcomeText.contains("fathan2424"),
                "Login gagal! Expected welcome message dengan username: fathan2424");

        System.out.println("Login successful!");
    }


    @Test(priority = 2, description = "TC-LG-N01: Login dengan password salah")
    public void testLoginInvalidPassword() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        String username = "fathan2424";
        String wrongPassword = "WrongPassword123";

        System.out.println("Testing login dengan password salah");

        homePage.clickLogin();
        loginPage.login(username, wrongPassword);

        // Verify alert error
        String alertText = loginPage.getAlertText();
        System.out.println("Alert text: " + alertText);

        Assert.assertTrue(
                alertText.contains("Wrong password") || alertText.contains("User does not exist"),
                "Expected error message, Actual: " + alertText
        );

        loginPage.acceptAlert();
    }
}
