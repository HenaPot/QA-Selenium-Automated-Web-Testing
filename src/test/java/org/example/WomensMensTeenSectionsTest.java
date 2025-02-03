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
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WomensMensTeenSectionsTest {
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
    void testWomensNewDresses() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        Actions actions = new Actions(webDriver);

        WebElement womensSectionHoverArea = webDriver.findElement(By.cssSelector("a[href='/hr/h-woman.html']"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", womensSectionHoverArea);
        Thread.sleep(500);

        actions.moveToElement(womensSectionHoverArea).perform();
        Thread.sleep(1000);
        womensSectionHoverArea.click();
        Thread.sleep(2500);
        WebElement newWomensFashion = webDriver.findElement(By.xpath("//*[@id=\"aria-Žene\"]/ul/li[2]/div/a"));
        actions.moveToElement(newWomensFashion).click().perform();
        Thread.sleep(2500);

        WebElement dresses = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[1]/div/div/div/div/div/ul/li[5]/button"));
        Thread.sleep(500);
        dresses.click();
        Thread.sleep(2500);
        String ariaCheckedValue = dresses.getAttribute("aria-checked");
        assertEquals("true", ariaCheckedValue);
    }

    @Test
    void testWomensAccessoryGlasses() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        Actions actions = new Actions(webDriver);
        WebElement womensSectionHoverArea = webDriver.findElement(By.cssSelector("a[href='/hr/h-woman.html']"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", womensSectionHoverArea);
        Thread.sleep(500);

        actions.moveToElement(womensSectionHoverArea).perform();
        Thread.sleep(1000);
        womensSectionHoverArea.click();
        Thread.sleep(2500);
        WebElement womensAccessories = webDriver.findElement(
                By.xpath("//*[@id=\"aria-Žene\"]/ul/li[5]/div/button/span/span/span"));
        actions.moveToElement(womensAccessories).click().perform();
        Thread.sleep(2500);
        WebElement glassesLink = webDriver.findElement(
                By.xpath("//*[@id=\"aria-Žene\"]/ul/li[5]/div/div/div/ul/li[10]/div/a/span"));
        actions.moveToElement(glassesLink).click().perform();
        Thread.sleep(1000);

        WebElement glassesCategoryName = webDriver.findElement(By.tagName("h1"));
        assertEquals("Naočale", glassesCategoryName.getText());
    }

    @Test
    void testMensFootwearSportShoes() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        Actions actions = new Actions(webDriver);
        WebElement mensSectionHoverArea = webDriver.findElement(By.cssSelector("a[href='/hr/h-man.html']"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", mensSectionHoverArea);
        Thread.sleep(500);
        actions.moveToElement(mensSectionHoverArea).perform();
        Thread.sleep(1000);
        mensSectionHoverArea.click();
        Thread.sleep(2500);
        WebElement mensFootwear = webDriver.findElement(
                By.xpath("//*[@id=\"aria-Muškarci\"]/ul/li[4]/div/button/span/span/span"));
        actions.moveToElement(mensFootwear).click().perform();
        Thread.sleep(2500);
        WebElement sportShoesLink = webDriver.findElement(
                By.xpath("//*[@id=\"aria-Muškarci\"]/ul/li[4]/div/div/div/ul/li[5]/div/a/span"));
        actions.moveToElement(sportShoesLink).click().perform();
        Thread.sleep(1000);
        WebElement sportShoesCategoryName = webDriver.findElement(By.tagName("h1"));
        assertEquals( "Sportska obuća", sportShoesCategoryName.getText());
    }

    @Test
    void testTeenPrintShop() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        Actions actions = new Actions(webDriver);
        WebElement teenSectionHoverArea = webDriver.findElement(
                By.xpath("//*[@id=\"aria-desktop-menu\"]/div[3]/div/div/ul/li[3]/div/a"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", teenSectionHoverArea);
        Thread.sleep(500);
        actions.moveToElement(teenSectionHoverArea).perform();
        Thread.sleep(1000);
        teenSectionHoverArea.click();
        Thread.sleep(2500);
        WebElement printShop = webDriver.findElement(
                By.xpath("//*[@id=\"aria-Bskteen\"]/ul/li[6]/div[2]/a/span"));
        actions.moveToElement(printShop).click().perform();
        Thread.sleep(3500);
        WebElement printShopCategoryName = webDriver.findElement(By.tagName("h1"));
        assertEquals( "The BERSHKA Print Shop", printShopCategoryName.getText());
    }
}
