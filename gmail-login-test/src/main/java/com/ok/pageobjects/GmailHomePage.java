package com.ok.pageobjects;

import com.ok.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by OK on 12.01.2016.
 */
public class   GmailHomePage {
    public SignInPage signOut(WebDriver driver) {
        WebUtil.click(driver, By.cssSelector("span[class='gb_Za gbii']"));

        WebUtil.click(driver, By.partialLinkText("Вийти"));

        WebUtil.waitForElementVisible(driver, By.id("signIn"));

        return PageFactory.initElements(driver,SignInPage.class);
    }

    public boolean InboxExist(WebDriver driver) {
        return  WebUtil.isElementExist(driver, By.partialLinkText("Вхідні"));

    }

    public static void clickComposebutton(WebDriver driver) {
        WebUtil.click(driver, By.cssSelector("div[role='button'][gh='cm']"));
    }

    public void fillInRecipient(WebDriver driver, String recipient) {
        WebUtil.waitForElementVisible(driver, By.name("to"));
        WebUtil.clearAndSendKeys(driver,By.name("to"), "kononenko.alexander@gmail.com");

    }

    public void fillInSubject(WebDriver driver, String subjectText) {
        WebUtil.clearAndSendKeys(driver, By.name("subjectbox"), subjectText);
    }

    public void fillInBody(WebDriver driver, String bodyText) {
        WebUtil.clearAndSendKeys(driver, By.cssSelector("div[aria-label='Текст повідомлення']"), bodyText);
    }

    public void clickSend(WebDriver driver) {
        WebUtil.click(driver, By.cssSelector("div[role='button'][aria-label*='Надіслати']"));
    }

    public void clickInbox(WebDriver driver) {
        WebUtil.waitForElementVisible(driver, By.xpath("//a[contains(@href,'https://mail.google.com/mail/u/0/#inbox')]"));
        WebUtil.click(driver, By.xpath("//a[contains(@href,'https://mail.google.com/mail/u/0/#inbox')]"));
    }

    public GmailLetterPage clickNewEmail(WebDriver driver) {
        WebUtil.waitForElementVisible(driver, By.xpath("//*[@class='y6']//b[contains(text(),'subject')]"));
        WebUtil.click(driver,By.xpath("//*[@class='y6']//b[contains(text(),'subject')]"));

        return PageFactory.initElements(driver,GmailLetterPage.class);
    }
}
