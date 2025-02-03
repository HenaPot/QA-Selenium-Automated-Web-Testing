package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchFashionItemsTest {
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
        baseUrl = "https://www.bershka.com/hr/h-man.html";
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void searchEmptyStringTest() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(
                By.xpath("//*[@id=\"aria-desktop-menu\"]/div[3]/div/div/ul/li[3]/div/a"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(5000);

        WebElement searchButton = webDriver.findElement(By.xpath("//button[@data-qa-anchor='searchBtn']"));
        searchButton.click();
        Thread.sleep(3000);

        WebElement searchInput = webDriver.findElement(By.id("input-search"));
        Thread.sleep(250);
        searchInput.sendKeys("" + Keys.ENTER);

        Thread.sleep(3000);

        WebElement errorMessage = webDriver.findElement(By.className("search-bar-input__error"));
        assertEquals("Opišite što tražite (npr. majica s kratkim rukavima)", errorMessage.getText());
    }

    @Test
    public void searchSuggestedDresses() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(
                By.xpath("//*[@id=\"aria-desktop-menu\"]/div[3]/div/div/ul/li[3]/div/a"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(5000);

        WebElement searchButton = webDriver.findElement(By.xpath("//button[@data-qa-anchor='searchBtn']"));
        searchButton.click();
        Thread.sleep(3000);
        WebElement searchInput = webDriver.findElement(By.id("input-search"));
        Thread.sleep(250);

        WebElement suggestedDressesChip = webDriver.findElement(By.xpath("//a[@href='/hr/q/Haljine']"));
        Thread.sleep(250);
        suggestedDressesChip.click();
        Thread.sleep(5000);

        assertTrue(webDriver.getPageSource().contains("haljina"));
    }

    @Test
    public void searchSpecialCharactersGetNoItemsDisplayed() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(
                By.xpath("//*[@id=\"aria-desktop-menu\"]/div[3]/div/div/ul/li[3]/div/a"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(5000);

        WebElement searchButton = webDriver.findElement(By.xpath("//button[@data-qa-anchor='searchBtn']"));
        searchButton.click();
        Thread.sleep(3000);

        WebElement searchInput = webDriver.findElement(By.id("input-search"));
        Thread.sleep(250);
        searchInput.sendKeys("test :@)q(*" + Keys.ENTER);

        Thread.sleep(3000);

        assertTrue(webDriver.getPageSource().contains("Nismo pronašli rezultate"));
    }

}
