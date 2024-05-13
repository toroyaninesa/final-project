package org.domus.pages;
import org.domus.locators.ProductLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{

    @FindBy(css = ProductLocators.addToBasket)
    public WebElement addToBasket;

    @FindBy(css = ProductLocators.price)
    public WebElement price;

    public ProductPage(WebDriver driver) {
        super(driver);
    }
}
