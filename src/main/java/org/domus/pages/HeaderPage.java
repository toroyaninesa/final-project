package org.domus.pages;

import org.domus.locators.HeaderLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPage extends BasePage {

    @FindBy(className = HeaderLocators.search)
    public WebElement search;

    @FindBy(id = HeaderLocators.searchPopup)
    public WebElement searchPopup;

    public HeaderPage(WebDriver driver) {
        super(driver);
    }
}
