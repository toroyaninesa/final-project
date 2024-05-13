package org.domus;

import org.domus.assertions.SearchPageAssertions;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;

import static org.domus.assertions.SearchPageAssertions.ELEMENT_EXISTS;
import static org.domus.assertions.SearchPageAssertions.WRONG_REDIRECT_URL;

public class SearchTest extends BaseTest{

    @Parameters("input")
    @Test
    public void shouldRedirectToDetailedSearchPage(String input) {
        Assert.assertNotNull(header.search);
        header.search.sendKeys(input);
        header.search.sendKeys(Keys.ENTER);
        String currentUrl = header.getCurrentUrl();

        Assert.assertEquals(WRONG_REDIRECT_URL,"https://domus.am/?s=" + input, currentUrl);
    }

    @Test
    public void shouldCloseSearchPopupOnOutsideClick() {
        Assert.assertNotNull(header.search);
        header.search.sendKeys(dummyInputValue);
        Assert.assertNotNull(header.searchPopup);
        header.clickOutside();
        boolean isPresent = header.isElementPresent(header.searchPopup);

        Assert.assertFalse(ELEMENT_EXISTS,isPresent);
    }

    @Test
    public void detailedSearchOptionsShouldBeVisible() {
        Assert.assertNotNull(header.search);
        header.search.sendKeys(existingProductName);
        header.search.sendKeys(Keys.ENTER);

        Assert.assertNotNull(searchResultsPage.sortButton);
        Assert.assertNotNull(searchResultsPage.price);
        Assert.assertNotNull(searchResultsPage.allFilters);
        Assert.assertNotNull(searchResultsPage.productAvailability);
    }

    @Test
    public void shouldCorrectlyFilterOutResultsOnAvailability() {
        Assert.assertNotNull(header.search);
        header.search.sendKeys(existingProductName);
        header.search.sendKeys(Keys.ENTER);
        Assert.assertNotNull(searchResultsPage.productAvailability);
        String innerValue = searchResultsPage.specificProductAvailability.getText();
        Assert.assertEquals(innerValue,"Առկա");
        searchResultsPage.productAvailability.click();
        String innerValueAfterToggle = searchResultsPage.specificProductAvailability.getText();
        Assert.assertEquals(innerValueAfterToggle,"Առկա չէ");
    }

    @Test
    public void shouldCorrectlySortByPriceAscendingOrder() {
        Assert.assertNotNull(header.search);
        header.search.sendKeys(existingProductName);
        header.search.sendKeys(Keys.ENTER);
        searchResultsPage.sort.click();
        searchResultsPage.getSortAscendingOrderButton().click();
        WebElement productOne = searchResultsPage.getNthMatchedProduct(1);
        String priceOne = searchResultsPage.getPriceOfCurrentProduct(productOne);
        WebElement productTwo = searchResultsPage.getNthMatchedProduct(2);
        String priceTwo = searchResultsPage.getPriceOfCurrentProduct(productTwo);
        Assert.assertTrue(SearchPageAssertions.SORTING_NOT_VALID, Integer.parseInt(priceOne) < Integer.parseInt(priceTwo));
    }

    @Test
    public void shouldCorrectlySortByPriceDescendingOrder() {
        Assert.assertNotNull(header.search);
        header.search.sendKeys(existingProductName);
        header.search.sendKeys(Keys.ENTER);
        searchResultsPage.sort.click();
        searchResultsPage.getSortDescendingOrderButton().click();
        WebElement productOne = searchResultsPage.getNthMatchedProduct(1);
        String priceOne = searchResultsPage.getPriceOfCurrentProduct(productOne);
        WebElement productTwo = searchResultsPage.getNthMatchedProduct(2);
        String priceTwo = searchResultsPage.getPriceOfCurrentProduct(productTwo);
        Assert.assertTrue(SearchPageAssertions.SORTING_NOT_VALID, Integer.parseInt(priceOne) >= Integer.parseInt(priceTwo));
    }
}
