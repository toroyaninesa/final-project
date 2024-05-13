package org.domus;

import org.junit.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.Keys;

public class CartTest extends BaseTest{
    @Test
    public void addToCartButtonShouldBeVisible() {
        Assert.assertNotNull(header.search);
        header.search.sendKeys(existingProductName);
        header.search.sendKeys(Keys.ENTER);

        Assert.assertNotNull(searchResultsPage.productArea);
        searchResultsPage.productArea.click();

        Assert.assertNotNull(productPage.addToBasket);
    }

    @Test
    public void priceInCartShouldBeCorrect() {
        Assert.assertNotNull(header.search);
        header.search.sendKeys(existingProductName);
        header.search.sendKeys(Keys.ENTER);
        searchResultsPage.productArea.click();
        productPage.addToBasket.click();

        Assert.assertEquals("1 200",productPage.price.getText());
        cart.clickOutside();
        Assert.assertNotNull(cart.basket);
        cart.basket.click();
        Assert.assertEquals("1 200", cart.basketTotalPrice.getText());
    }

    @Test
    public void shouldAdjustPriceOnCountIncrement() throws InterruptedException {
        Assert.assertNotNull(header.search);
        header.search.sendKeys(existingProductName);
        header.search.sendKeys(Keys.ENTER);
        searchResultsPage.productArea.click();
        productPage.addToBasket.click();
        cart.clickOutside();

        Assert.assertNotNull(cart.basket);
        cart.basket.click();
        Assert.assertEquals("1", cart.productCount.getAttribute("value"));
        cart.countIncrement.click();
        Thread.sleep(3000);
        Assert.assertEquals("2", cart.productCount.getAttribute("value"));
    }

    @Test
    public void shouldAdjustPriceOnCountDecrement() throws InterruptedException {
        Assert.assertNotNull(header.search);
        header.search.sendKeys(existingProductName);
        header.search.sendKeys(Keys.ENTER);
        searchResultsPage.productArea.click();
        productPage.addToBasket.click();
        cart.clickOutside();

        Assert.assertNotNull(cart.basket);
        cart.basket.click();
        Assert.assertEquals("1", cart.productCount.getAttribute("value"));
        cart.countIncrement.click();
        Thread.sleep(3000);
        Assert.assertEquals("2", cart.productCount.getAttribute("value"));
        cart.countDecrement.click();
        Thread.sleep(3000);
        Assert.assertEquals("1", cart.productCount.getAttribute("value"));
    }

    @Test
    public void shouldCorrectlyRemoveFromCart(){
        Assert.assertNotNull(header.search);
        header.search.sendKeys(existingProductName);
        header.search.sendKeys(Keys.ENTER);
        searchResultsPage.productArea.click();
        productPage.addToBasket.click();
        cart.clickOutside();

        Assert.assertNotNull(cart.basket);
        cart.basket.click();
        cart.countDecrement.click();
        Assert.assertNotNull(cart.emptyBasket);
        Assert.assertEquals(" Ձեր զամբյուղը դատարկ է", cart.emptyBasket.getText());
    }
}
