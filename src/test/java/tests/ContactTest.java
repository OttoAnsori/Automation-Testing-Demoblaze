package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.uas.praktikum.pages.ContactPage;
import com.uas.praktikum.pages.HomePage;

/**
 * Test class untuk Contact functionality
 */
public class ContactTest extends BaseTest {

    @Test(priority = 1, description = "TC-CO-P01: Kirim pesan contact valid")
    public void testContactValid() {
        HomePage homePage = new HomePage(driver);
        ContactPage contactPage = new ContactPage(driver);

        System.out.println("Testing send contact message dengan data valid");

        homePage.clickContact();
        homePage.waitFor(1);

        contactPage.sendMessage("test@example.com", "QA Tester", "Ini pesan test automation");
        homePage.waitFor(1);

        // Verify alert konfirmasi
        String alertText = contactPage.getAlertText();
        System.out.println("Alert text: " + alertText);

        Assert.assertTrue(alertText.contains("Thanks for the message"),
                "Expected: 'Thanks for the message', Actual: " + alertText);

        contactPage.acceptAlert();

        System.out.println("Contact message sent successfully");
    }

    @Test(priority = 2, description = "TC-CO-N01: Kirim pesan tanpa email")
    public void testContactWithoutEmail() {
        HomePage homePage = new HomePage(driver);
        ContactPage contactPage = new ContactPage(driver);

        System.out.println("Testing send contact message tanpa email");

        homePage.clickContact();
        homePage.waitFor(1);

        // Kirim tanpa email (email kosong)
        contactPage.sendMessage("", "Test User", "Pesan tanpa email");
        homePage.waitFor(1);

        // Expected: Tidak ada konfirmasi atau ada error
        // Tergantung implementasi Demoblaze
        System.out.println("Test completed - behavior depends on Demoblaze validation");
    }
}
