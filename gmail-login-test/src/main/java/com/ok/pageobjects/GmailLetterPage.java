package com.ok.pageobjects;

import com.ok.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by OK on 17.01.2016.
 */
public class GmailLetterPage {

    public String getSubjectText(WebDriver driver) {
       return WebUtil.getElementText(driver, By.cssSelector("div[class='ha'] h2"));
    }

    public String getBodyText(WebDriver driver) {
        return WebUtil.getElementText(driver, By.cssSelector("div[class='a3s'] div[dir='ltr']"));
    }
}
