import com.ok.categories.Critical;
import com.ok.pageobjects.GmailHomePage;
import com.ok.pageobjects.SignInPage;
import com.ok.util.WebUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by OK on 12.01.2016.
 */
public class GmailLoginTest {

    WebDriver driver;

    @Before
    public void setDriver(){
        String browserName = System.getenv("browser");
        if (browserName != null && browserName.equalsIgnoreCase("Chrome")){
            String chromeDriverPath = GmailLoginTest.class.getClassLoader().getResource("bin/chromedriver.exe").getPath();
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            driver = new ChromeDriver();
        }else{
            driver = new FirefoxDriver();
        }
    }

    @Category({Critical.class})
    @Test
    public void GmailLoginShouldBeSuccefull(){
//        1. Open Gmail
        SignInPage signInPage = WebUtil.goToSignInPage(driver);

//       2. Login
        final String username = "***@***";
        signInPage.fillInUsername(driver, username);

        signInPage.clickNextButton(driver);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        final String password = "***";
        signInPage.fillInPassword(driver, password);

        GmailHomePage gmailHomePage = signInPage.clickSignInButton(driver);

//      3. Check that we have logged in
        Assert.assertTrue("Inbox should exist", gmailHomePage.InboxExist(driver));

//      4. Log out
        signInPage = gmailHomePage.signOut(driver);

//      5. Check that we have logged out
        Assert.assertTrue("Log In button should exist", signInPage.signInButtonExist(driver));
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
