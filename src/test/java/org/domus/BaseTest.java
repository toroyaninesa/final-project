package org.domus;

import org.domus.pages.*;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    public static final String PATH = "src/main/resources/";
    public static final String PNG_EXTENSION = ".png";

    static final String dummyInputValue = "wrongData";
    static final String existingProductName = "սրճեփ";
    protected WebDriver driver;
    protected HeaderPage header;
    protected SearchResultsPage searchResultsPage;
    protected CartPage cart;
    protected ProductPage productPage;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        String gridHubUrl = "http://localhost:4444/wd/hub";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver = new RemoteWebDriver(new URL(gridHubUrl), options);




        /*System.setProperty("webdriver.chrome.whitelistedIps", "");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();*/
        driver.get("https://domus.am/");
        initializePages();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @AfterMethod
    public void screenshotAndTerminate(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot screenshotMaker = (TakesScreenshot) driver;
            Path screenShotBytes = screenshotMaker.getScreenshotAs(OutputType.FILE).getAbsoluteFile().toPath();
            Path destination = Paths.get(PATH + result.getStartMillis() + "-" + "screenshot" + PNG_EXTENSION);
            try {
                Files.move(screenShotBytes, destination);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        driver.close();

    }

    private void initializePages() {
        header = new HeaderPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        cart = new CartPage(driver);
        productPage = new ProductPage(driver);
    };
}
