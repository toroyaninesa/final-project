package org.domus.pages;

import org.domus.locators.CartLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
    @FindBy(css = CartLocators.basket)
    public WebElement basket;

    @FindBy(css = CartLocators.closeBasket)
    public WebElement closeBasket;

    @FindBy(css = CartLocators.basketTotalPrice)
    public WebElement basketTotalPrice;

    @FindBy(css = CartLocators.removeFromBasket)
    public WebElement removeFromBasket;

    @FindBy(css = CartLocators.emptyBasket)
    public WebElement emptyBasket;

    @FindBy(css = CartLocators.countIncrement)
    public WebElement countIncrement;

    @FindBy(css = CartLocators.countDecrement)
    public WebElement countDecrement;

    @FindBy(css = CartLocators.productCount)
    public WebElement productCount;

    public CartPage(WebDriver driver) {
        super(driver);
    }
}
