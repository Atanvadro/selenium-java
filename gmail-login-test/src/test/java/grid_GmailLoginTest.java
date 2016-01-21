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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by OK on 12.01.2016.
 */
public class grid_GmailLoginTest {

    RemoteWebDriver driver;

    @Before
    public void setDriver() throws MalformedURLException {
        String browserName = System.getenv("browser");
        DesiredCapabilities desiredCapabilities;
        if (browserName != null && browserName.equalsIgnoreCase("Chrome")){
            desiredCapabilities = DesiredCapabilities.chrome();
        }else{
            desiredCapabilities = DesiredCapabilities.firefox();
        }
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4433/wd/hub"), desiredCapabilities);
    }

    @Category({Critical.class})
    @Test
    public void GmailLoginShouldBeSuccefull(){
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
