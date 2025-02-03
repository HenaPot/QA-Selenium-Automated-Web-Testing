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

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class AddAndRemoveItemsFromCart {
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
    void openTeenSectionChooseFirstItemSize37WithoutOpeningItemPage() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(By.xpath("//*[@id=\"aria-desktop-menu\"]/div[3]/div/div/ul/li[3]/div/a"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Actions actions = new Actions(webDriver);

        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(5000);

        WebElement teenSectionHoverArea = webDriver.findElement(By.cssSelector("a[href='/hr/h-bskteen.html']"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", teenSectionHoverArea);
        Thread.sleep(500);

        actions.moveToElement(teenSectionHoverArea).perform();
        Thread.sleep(1000);
        teenSectionHoverArea.click();
        Thread.sleep(2500);

        WebElement footwear = webDriver.findElement(By.xpath("//*[@id=\"aria-Bskteen\"]/ul/li[4]/div/a/span/span"));
        Thread.sleep(500);
        footwear.click();
        Thread.sleep(2500);

        WebElement firstItem = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/section[1]/div/div/ul/li[1]/div/a"));
        js.executeScript("arguments[0].scrollIntoView(true);", firstItem);
        actions.moveToElement(firstItem).perform();
        Thread.sleep(3500);

        WebElement choose37Size = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/section[1]/div/div/ul/li[1]/div/a/div/div[1]/div[3]/div/div/div/div[2]/ul/li[3]/button"));
        choose37Size.click();
        Thread.sleep(1000);
        assertTrue(webDriver.getPageSource().contains("Dodano u košaricu"));
    }

    @Test
    void testEmptyCartIsEmpty() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(
                By.xpath("//*[@id=\"aria-desktop-menu\"]/div[3]/div/div/ul/li[3]/div/a"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Actions actions = new Actions(webDriver);

        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(6000);

        WebElement cart = webDriver.findElement(By.xpath("//*[@id=\"aria-button-shopcart\"]/div"));
        cart.click();
        Thread.sleep(2000);

        assertTrue(webDriver.getPageSource().contains("Košarica je prazna"));
        assertTrue(webDriver.getPageSource().contains("Još nemate niti jedan artikl u košarici. Otkrijte sve što vam nudimo."));
    }

    @Test
    void testAddItemToCartWithoutSelectingSize() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(
                By.xpath("//*[@id=\"aria-desktop-menu\"]/div[3]/div/div/ul/li[3]/div/a"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(7000);
        WebElement discoverButton = webDriver.findElement(
                By.cssSelector("a.bds-button.bds-button-pill.bds-button--size-l.bds-button--primary"));
        Thread.sleep(500);
        discoverButton.click();
        Thread.sleep(2000);

        WebElement firstFashionItem = webDriver.findElement(
                By.xpath("//a[@data-qa-anchor='productItemHref' and @tabindex='0']"));
        firstFashionItem.click();
        Thread.sleep(2000);
        WebElement addToCartButton = webDriver.findElement(By.className("product-actions__action-text"));
        addToCartButton.click();
        Thread.sleep(1500);
        WebElement errorValidationMessage = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/section/div/div[1]/div[1]/section/div[2]/span"));
        assertEquals("Morate odabrati veličinu", errorValidationMessage.getText());
    }

    @Test
    void testAddItemToCartWithSize() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(
                By.xpath("//*[@id=\"aria-desktop-menu\"]/div[3]/div/div/ul/li[3]/div/a"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(7000);

        WebElement discoverButton = webDriver.findElement(
                By.cssSelector("a.bds-button.bds-button-pill.bds-button--size-l.bds-button--primary"));
        Thread.sleep(500);
        discoverButton.click();
        Thread.sleep(2000);

        WebElement firstFashionItem = webDriver.findElement(
                By.xpath("//a[@data-qa-anchor='productItemHref' and @tabindex='0']"));
        firstFashionItem.click();
        Thread.sleep(2000);

        WebElement itemNameElement = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/section/div/div[1]/div[1]/section/div[1]/h1"));
        String itemName = itemNameElement.getText();

        WebElement sizeM = webDriver.findElement(By.xpath("//button[@aria-label='Veličina M']"));
        Thread.sleep(500);
        sizeM.click();
        Thread.sleep(1500);

        WebElement addToCartButton = webDriver.findElement(By.className("product-actions__action-text"));
        addToCartButton.click();
        Thread.sleep(250);

        WebElement cart = webDriver.findElement(By.xpath("//*[@id=\"aria-button-shopcart\"]/div"));
        cart.click();
        Thread.sleep(2000);

        assertTrue(webDriver.getPageSource().contains(itemName));
    }

    @Test
    public void addAndRemoveAnItemFromCart() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(
                By.xpath("//*[@id=\"aria-desktop-menu\"]/div[3]/div/div/ul/li[3]/div/a"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(7000);

        WebElement discoverButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("a.bds-button.bds-button-pill.bds-button--size-l.bds-button--primary")));
        Thread.sleep(500);
        discoverButton.click();
        Thread.sleep(2000);

        WebElement firstFashionItem = webDriver.findElement(
                By.xpath("//a[@data-qa-anchor='productItemHref' and @tabindex='0']"));
        firstFashionItem.click();
        Thread.sleep(2000);

        WebElement sizeM = webDriver.findElement(By.xpath("//button[@aria-label='Veličina M']"));
        sizeM.click();
        Thread.sleep(1500);

        WebElement addToCartButton = webDriver.findElement(By.className("product-actions__action-text"));
        addToCartButton.click();

        WebElement closeDrawer = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\"aria-button-drawer\"]/span[2]")));
        closeDrawer.click();
        Thread.sleep(1000);

        WebElement cart = webDriver.findElement(By.xpath("//*[@id=\"aria-button-shopcart\"]/div"));
        cart.click();
        Thread.sleep(1000);

        WebElement deleteItemButton = webDriver.findElement(By.xpath("//button[@data-qa-anchor='deleteCartBtn']"));
        Thread.sleep(1500);
        deleteItemButton.click();
        Thread.sleep(1000);

        assertTrue(webDriver.getPageSource().contains("Košarica je prazna"));
    }

}
