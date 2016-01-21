package com.ok.pageobjects;

import com.ok.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by OK on 12.01.2016.
 */
public class SignInPage {
    public void fillInUsername(WebDriver driver, String username) {
        WebUtil.clearAndSendKeys(driver, By.id("Email"), username);
    }

    public void clickNextButton(WebDriver driver) {
        WebUtil.click(driver, By.id("next"));
    }

    public void fillInPassword(WebDriver driver, String password) {
        WebUtil.clearAndSendKeys(driver, By.name("Passwd"), password);
    }

    public GmailHomePage clickSignInButton(WebDriver driver) {
        WebUtil.click(driver, By.id("signIn"));
        WebUtil.waitForElementVisible(driver, By.partialLinkText("Вхідні"));

        return PageFactory.initElements(driver,GmailHomePage.class);
    }

    public boolean signInButtonExist(WebDriver driver) {
        return WebUtil.isElementExist(driver,By.id("signIn"));
    }
}
