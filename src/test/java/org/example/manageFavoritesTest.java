package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class manageFavoritesTest {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/Hena/chromedriver-win64 (1)/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
//        options.addArguments("--disable-blink-features=AutomationControlled");

        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.bershka.com/ba/mu%C5%A1karci/sni%C5%BEenje/majice-c1010747937.html";


    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
    @Order(1)
    @Test
    void addAndRemoveFromOverview() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        Thread.sleep(5000);

        js.executeScript("window.scrollBy(0, 400);");
        WebElement heart = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[2]/section[1]/div[1]/div[1]/ul[1]/li[1]/div[1]/a[1]/div[1]/div[2]/div[1]/div[1]/div[4]/button[1]")));
        heart.click();
        Thread.sleep(4000);
        assertTrue(webDriver.getPageSource().contains("bskico-wishlist-on"));
        heart.click();
        Thread.sleep(1000);
        assertFalse(webDriver.getPageSource().contains("bskico-wishlist-on"));
        Thread.sleep(1000);

        webDriver.navigate().to(baseUrl);
    }


    @Test
    void addAndRemoveFromDetails() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriver.get(baseUrl);
        Thread.sleep(5000);
        js.executeScript("window.scrollBy(0, 300);");
        WebElement shirtLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@class='grid-card-link' and @data-qa-anchor='productItemHref']")));
        shirtLink.click();
        Thread.sleep(3000);
        WebElement heart = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/main[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]")));
        heart.click();
        Thread.sleep(1000);
        assertTrue(webDriver.getPageSource().contains("<button class=\"bds-button bds-button-icon round-wish-list-button wish-list-button bds-button--size-m bds-button--primary\" aria-label=\"Uklonite s popisa želja\" data-qa-anchor=\"addToWishListBtn\" style=\"color: var(--bds-color-extended-promo-high); background-color: var(--bds-color-background-base);\"><!----><svg viewBox=\"0 0 24 24\" class=\"bds-icon bds-button__icon\" aria-hidden=\"true\"><path pid=\"0\" fill-rule=\"evenodd\" clip-rule=\"evenodd\" d=\"M3 9.348C3 6.388 5.437 4 8.402 4a5.42 5.42 0 0 1 3.602 1.362A5.412 5.412 0 0 1 15.598 4c2.971 0 5.41 2.386 5.402 5.35a5.296 5.296 0 0 1-1.614 3.817l-.002.001-7.38 7.232-7.42-7.27A5.24 5.24 0 0 1 3 9.348Zm18 .001v.002-.003.001Z\" fill=\"currentColor\"></path></svg></button>"));
        heart.click();
        Thread.sleep(1000);
        assertTrue(webDriver.getPageSource().contains("<button class=\"bds-button bds-button-icon round-wish-list-button wish-list-button bds-button--size-m bds-button--primary\" aria-label=\"Dodajte u popis želja\" data-qa-anchor=\"addToWishListBtn\" style=\"color: var(--bds-color-content-high); background-color: var(--bds-color-background-base);\"><!----><svg viewBox=\"0 0 24 24\" class=\"bds-icon bds-button__icon\" aria-hidden=\"true\"><path pid=\"0\" fill-rule=\"evenodd\" clip-rule=\"evenodd\" d=\"M3 9.348C3 6.388 5.437 4 8.402 4a5.42 5.42 0 0 1 3.602 1.362A5.412 5.412 0 0 1 15.598 4c2.971 0 5.41 2.386 5.402 5.35a5.296 5.296 0 0 1-1.614 3.817l-.002.001-7.38 7.232-7.42-7.27A5.24 5.24 0 0 1 3 9.348ZM8.402 6C6.517 6 5 7.516 5 9.348c0 .911.36 1.752.974 2.344l.007.006 6.023 5.902 5.986-5.866A3.296 3.296 0 0 0 19 9.348v-.004C19.006 7.517 17.492 6 15.598 6a3.425 3.425 0 0 0-2.782 1.427l-.812 1.128-.812-1.128A3.429 3.429 0 0 0 8.402 6Z\" fill=\"currentColor\"></path></svg></button>"));
        Thread.sleep(1000);
        webDriver.navigate().to(baseUrl);
    }

    @Test
    void wishlist() throws InterruptedException {
        webDriver.get(baseUrl);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10)); // Adjust timeout as needed
        js.executeScript("window.scrollBy(0, 300);");
        WebElement heart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html/body/div[2]/div[1]/div[1]/div[2]/main[1]/div[1]/div[1]/div[1]/div[2]/section[1]/div[1]/div[1]/ul[1]/li[1]/div[1]/a[1]/div[1]/div[2]/div[1]/div[1]/div[4]/button[1]")));
        WebElement heart2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html/body/div[2]/div/div/div[2]/main/div[1]/div[1]/div[1]/div[2]/section[1]/div[1]/div[1]/ul[1]/li[3]/div[1]/a[1]/div[1]/div[2]/div[1]/div[1]/div[4]/button[1]")));
        js.executeScript("arguments[0].scrollIntoView(true);", heart);
        Thread.sleep(500);
        heart.click();
        Thread.sleep(1000);
        js.executeScript("arguments[0].scrollIntoView(true);", heart2);
        heart2.click();
        Thread.sleep(3000);
        WebElement cartbtn = webDriver.findElement(By.id("aria-button-shopcart"));
        js.executeScript("arguments[0].scrollIntoView(true);", cartbtn);
        cartbtn.click();
        Thread.sleep(1000);
        WebElement wishlistbtn = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/section[2]/div[1]/div[1]/a[1]"));
        js.executeScript("arguments[0].scrollIntoView(true);", wishlistbtn);
        wishlistbtn.click();
        Thread.sleep(3000);
        assertTrue(webDriver.getPageSource().contains("<img data-qa-anchor=\"wishListProductMainImage\" data-original=\"https://static.bershka.net/assets/public/0e88/4dc3/0f814afdb801/0d3095d6a819/08066130250-p/08066130250-p.jpg?ts=1723474503278&amp;w=450\" alt=\"Majica kratkih rukava s printom Motonori\" class=\"image-item\" src=\"https://static.bershka.net/assets/public/0e88/4dc3/0f814afdb801/0d3095d6a819/08066130250-p/08066130250-p.jpg?ts=1723474503278&amp;w=450\" style=\"\">"));
        WebElement remove = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/main[1]/div[1]/section[1]/div[1]/div[1]/ul[1]/li[1]/div[1]/a[1]/div[1]/div[2]/div[1]/div[1]/div[4]/button[1]"));
        js.executeScript("arguments[0].scrollIntoView(true);", remove);
        remove.click();
        Thread.sleep(2000);
        assertFalse(webDriver.getPageSource().contains("<img data-qa-anchor=\"wishListProductMainImage\" data-original=\"https://static.bershka.net/assets/public/0e88/4dc3/0f814afdb801/0d3095d6a819/08066130250-p/08066130250-p.jpg?ts=1723474503278&amp;w=450\" alt=\"Majica kratkih rukava s printom Motonori\" class=\"image-item\" src=\"https://static.bershka.net/assets/public/0e88/4dc3/0f814afdb801/0d3095d6a819/08066130250-p/08066130250-p.jpg?ts=1723474503278&amp;w=450\" style=\"\">"));

    }

}