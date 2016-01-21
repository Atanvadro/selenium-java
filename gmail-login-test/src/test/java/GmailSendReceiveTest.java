import com.ok.categories.Major;
import com.ok.pageobjects.GmailHomePage;
import com.ok.pageobjects.GmailLetterPage;
import com.ok.pageobjects.SignInPage;
import com.ok.util.WebUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

/**
 * Created by Oleksandr on 10.01.2016.
 */
public class GmailSendReceiveTest {
    WebDriver driver = new FirefoxDriver();

    @Category({Major.class})
    @Test
    public void SendReceive(){
//        1. Open Gmail
        SignInPage signInPage = WebUtil.goToSignInPage(driver);

//       2. Login
        final String username = "***";
        signInPage.fillInUsername(driver, username);

        signInPage.clickNextButton(driver);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        final String password = "***";
        signInPage.fillInPassword(driver, password);

        GmailHomePage gmailHomePage = signInPage.clickSignInButton(driver);

//        4. Click Compose;
        gmailHomePage.clickComposebutton(driver);

//        5. Fill in recipient;
        gmailHomePage.fillInRecipient(driver, "***");

//        6. Fill in subject;
        final String subjectText = "Test subject";
        gmailHomePage.fillInSubject(driver, subjectText);
//
//        7. Fill in e-mail body;
        final String bodyText = "Test body";
        gmailHomePage.fillInBody(driver, bodyText);

//        8. Click send;
        gmailHomePage.clickSend(driver);

//        9. Click Inbox;
        gmailHomePage.clickInbox(driver);

//        10. Click on our email;
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        GmailLetterPage gmailLetterPage = gmailHomePage.clickNewEmail(driver);

//        11. Verify that e-mail subject and body are correct;
        String actualSubject = gmailLetterPage.getSubjectText(driver);
        Assert.assertEquals("Subject should be same", subjectText, actualSubject);
//
        String actualBody = gmailLetterPage.getBodyText(driver);
        Assert.assertEquals("Body should be same", bodyText, actualBody);

//        12. Log Out;
        gmailHomePage.signOut(driver);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
