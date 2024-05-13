package org.domus.pages;

import org.domus.locators.SearchResultsLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {
    @FindBy(css = SearchResultsLocators.sortButton)
    public WebElement sortButton;

    @FindBy(css = SearchResultsLocators.price)
    public WebElement price;

    @FindBy(css = SearchResultsLocators.allFilters)
    public WebElement allFilters;

    @FindBy(css = SearchResultsLocators.productAvailability)
    public WebElement productAvailability;

    @FindBy(css = SearchResultsLocators.specificProductAvailability)
    public WebElement specificProductAvailability;

    @FindBy(css = SearchResultsLocators.productArea)
    public WebElement productArea;

    public WebElement getNthMatchedProduct(int matchNo) {
        List<WebElement> divsWithClass = driver.findElements(By.cssSelector("div.prod_sec"));
        return divsWithClass.get(matchNo - 1);
    }

    public String getPriceOfCurrentProduct(WebElement product) {
        return product.findElement(By.cssSelector(".price"))
                .getText()
                .replaceAll("[^\\d]", "")
                .trim();
    }

    @FindBy(css = SearchResultsLocators.sort)
    public WebElement sort;

    public WebElement getSortAscendingOrderButton() {
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(SearchResultsLocators.sortOptions));
        return radioButtons.get(1);
    }

    public WebElement getSortDescendingOrderButton() {
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(SearchResultsLocators.sortOptions));
        return radioButtons.get(0);
    }
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }
}
