package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.uas.praktikum.pages.HomePage;
import com.uas.praktikum.pages.SignUpPage;

/**
 * Test class untuk Sign Up functionality
 */
public class SignUpTest extends BaseTest {

    @Test(priority = 1, description = "TC-SU-P01: Sign up dengan data valid")
    public void testSignUpValid() {
        HomePage homePage = new HomePage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);

        // Generate unique username menggunakan timestamp
        String username = "fathan2424" + System.currentTimeMillis();
        String password = "qwerty";

        System.out.println("Testing sign up dengan username: " + username);

        homePage.clickSignUp();
        signUpPage.signUp(username, password);

        // Verify alert success
        String alertText = signUpPage.getAlertText();
        System.out.println("Alert text: " + alertText);

        Assert.assertTrue(alertText.contains("Sign up successful"),
                "Expected: 'Sign up successful', Actual: " + alertText);

        signUpPage.acceptAlert();
    }

    @Test(priority = 2, description = "TC-SU-N01: Sign up dengan username sudah terdaftar")
    public void testSignUpDuplicate() {
        HomePage homePage = new HomePage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);

        // Gunakan username yang sudah pasti ada
        String username = "qa_user01";
        String password = "Test123";

        System.out.println("Testing sign up dengan username existing: " + username);

        homePage.clickSignUp();
        signUpPage.signUp(username, password);

        // Verify alert error
        String alertText = signUpPage.getAlertText();
        System.out.println("Alert text: " + alertText);

        Assert.assertTrue(
                alertText.contains("already exist") || alertText.contains("user already"),
                "Expected error message, Actual: " + alertText
        );

        signUpPage.acceptAlert();
    }
}
