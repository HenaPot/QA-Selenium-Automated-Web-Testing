package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class FilterFashionItems {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/Hena/chromedriver-win64 (1)/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");

        webDriver = new ChromeDriver(options);
        baseUrl = "http://www.bershka.com/hr/h-man.html";
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    void applyOneFilterPerEachFilterCategoryNoItemsFound() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(By.xpath("//div[@id='onetrust-close-btn-container']/button"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        js.executeScript("arguments[0].click();", closeCookies);

        Thread.sleep(7000);

        WebElement discountedPage = webDriver.findElement(By.xpath("//main"));
        discountedPage.click();

        Thread.sleep(2000);
        WebElement filterButton = webDriver.findElement(By.cssSelector("button[data-qa-anchor='filterButton']"));
        filterButton.click();
        Thread.sleep(1000);

        WebElement sortFilterButtonByLowerPrice = webDriver.findElement(By.xpath("//button[@value='byLowerPrice']"));
        Thread.sleep(500);
        sortFilterButtonByLowerPrice.click();

        Thread.sleep(1500);
        WebElement sortFilterButtonCategory = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/div[1]/section/div[2]/div/div/section[2]/button"));
        sortFilterButtonCategory.click();
        Thread.sleep(1500);

        WebElement trousersCategory = webDriver.findElement(By.xpath("//button[@nameen=\"Trousers\"]"));
        trousersCategory.click();
        Thread.sleep(2500);

        WebElement sortFilterButtonColor = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/div[1]/section/div[2]/div/div/section[3]/button"));
        sortFilterButtonColor.click();
        Thread.sleep(1500);

        WebElement blueColor = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/div[1]/section/div[2]/div/div/section[3]/div/div[8]/button"));
        js.executeScript("arguments[0].scrollIntoView(true);", blueColor);
        blueColor.click();
        Thread.sleep(2500);

        WebElement sortFilterButtonSize = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/div[1]/section/div[2]/div/div/section[4]/button"));
        js.executeScript("arguments[0].scrollIntoView(true);", sortFilterButtonSize);
        sortFilterButtonSize.click();
        Thread.sleep(1500);

        WebElement sizeXL = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/div[1]/section/div[2]/div/div/section[4]/div/button[7]"));
        js.executeScript("arguments[0].scrollIntoView(true);", sizeXL);
        sizeXL.click();
        Thread.sleep(2500);

        WebElement sortFilterButtonMaxPrice = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/div[1]/section/div[2]/div/div/section[5]/button"));
        js.executeScript("arguments[0].scrollIntoView(true);", sortFilterButtonMaxPrice);
        sortFilterButtonMaxPrice.click();
        Thread.sleep(1500);

        WebElement maxPrice30 = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/div[1]/section/div[2]/div/div/section[5]/div/button[5]"));
        js.executeScript("arguments[0].scrollIntoView(true);", maxPrice30);
        maxPrice30.click();
        Thread.sleep(2500);

        assertTrue(webDriver.getPageSource().contains("Nema rezultata"));
        assertTrue(webDriver.getPageSource().contains("Pokušajte odabrati druge filtre"));

    }
    @Test
    void applyMultipleFiltersForTwoCategoriesWithDiscount() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(By.xpath("//div[@id='onetrust-close-btn-container']/button"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        js.executeScript("arguments[0].click();", closeCookies);

        Thread.sleep(7000);

        WebElement discoverButton = webDriver.findElement(
                By.cssSelector("a.bds-button.bds-button-pill.bds-button--size-l.bds-button--primary"));
        discoverButton.click();
        Thread.sleep(2000);

        WebElement filterButton = webDriver.findElement(By.cssSelector("button[data-qa-anchor='filterButton']"));
        filterButton.click();
        Thread.sleep(1500);

        WebElement sortFilterButtonCategory = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/div[1]/section/div[2]/div/div/section[2]/button"));
        sortFilterButtonCategory.click();
        Thread.sleep(1500);

        WebElement clothesCategory = webDriver.findElement(By.xpath("//button[@nameen=\"Clothes\"]"));
        clothesCategory.click();
        Thread.sleep(1000);

        WebElement basicsCategory = webDriver.findElement(By.xpath("//button[@nameen=\"Basics\"]"));
        basicsCategory.click();
        Thread.sleep(1000);

        WebElement shoesCategory = webDriver.findElement(By.xpath("//button[@nameen=\"Shoes\"]"));
        shoesCategory.click();
        Thread.sleep(1000);

        WebElement sortFilterButtonColor = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/div[1]/section/div[2]/div/div/section[3]/button"));
        sortFilterButtonColor.click();
        Thread.sleep(1500);

        WebElement whiteColor = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/div[1]/section/div[2]/div/div/section[3]/div/div[1]/button"));
        js.executeScript("arguments[0].scrollIntoView(true);", whiteColor);
        whiteColor.click();
        Thread.sleep(500);

        WebElement blackColor = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/div[1]" +
                        "/section/div[2]/div/div/section[3]/div/div[6]/button"));
        js.executeScript("arguments[0].scrollIntoView(true);", blackColor);
        blackColor.click();
        Thread.sleep(1500);

        WebElement resultButton = webDriver.findElement(By.xpath("//button[@data-qa-anchor='seeResultBtn']"));
        assertTrue(resultButton.getText().contains("34"));
    }

    @Test
    public void testClearingFilters() throws InterruptedException {
        webDriver.get("https://www.bershka.com/hr/muskarci/novo-n3745.html" +
                "?" +
                "tipology=1010624787%7C%7C1010565676%7C%7C1010565177&color=Bijela%7C%7CCrna");
        WebElement closeCookies = webDriver.findElement(By.xpath("//div[@id='onetrust-close-btn-container']/button"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(7000);

        WebElement filterButton = webDriver.findElement(By.cssSelector("button[data-qa-anchor='filterButton']"));
        filterButton.click();
        Thread.sleep(1500);

        WebElement resultButton = webDriver.findElement(By.xpath("//button[@data-qa-anchor='seeResultBtn']"));
        assertTrue(resultButton.getText().contains("34"));

        WebElement clearFiltersButton = webDriver.findElement(By.xpath("//button[@data-qa-anchor='clearFilters']"));
        clearFiltersButton.click();

        resultButton = webDriver.findElement(By.xpath("//button[@data-qa-anchor='seeResultBtn']"));
        assertFalse(resultButton.getText().contains("34"));
    }
}
