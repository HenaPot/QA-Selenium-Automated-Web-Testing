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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InteractingWithFashionItemsTest {
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
    void openMensNewFashionFirstItemChoseLSizeReviewMaterials() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(By.xpath("//div[@id='onetrust-close-btn-container']/button"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(5000);
        WebElement discoverButton = webDriver.findElement(By.cssSelector("a.bds-button.bds-button-pill.bds-button--size-l.bds-button--primary"));
        discoverButton.click();
        Thread.sleep(4000);
        WebElement firstFashionItem = webDriver.findElement(By.xpath("//a[@data-qa-anchor='productItemHref' and @tabindex='0']"));
        Thread.sleep(500);
        firstFashionItem.click();
        Thread.sleep(3500);
        WebElement selectLSize = webDriver.findElement(By.xpath("//button[@aria-label='Veličina L']"));
        selectLSize.click();
        Thread.sleep(3000);
        WebElement reviewMaterials = webDriver.findElement(By.cssSelector(".product-links-list__item--interactive.is-naked"));
        reviewMaterials.click();
        Thread.sleep(4000);
        WebElement sastavPorijeklo = webDriver.findElement(By.className("bsk-drawer__title"));
        assertEquals("Sastav, održavanje i podrijetlo", sastavPorijeklo.getText());
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
        WebElement womensClothes = webDriver.findElement(By.xpath("//*[@id=\"aria-Žene\"]/ul/li[3]/div/button/span/span"));
        actions.moveToElement(womensClothes).click().perform();
        Thread.sleep(2500);

        WebElement bestSellers = webDriver.findElement(
                By.xpath("//*[@id=\"aria-Žene\"]/ul/li[3]/div/div/div/ul/li[1]/div/a/span"));
        Thread.sleep(500);
        bestSellers.click();
        Thread.sleep(2500);

        WebElement firstItem = webDriver.findElement(
        By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/section[1]/div/div/ul/li[1]/div/a/div/div[1]/div[1]/span/img[1]"));
        firstItem.click();
        Thread.sleep(2500);

        WebElement selectBlueColor = webDriver.findElement(By.xpath("//*[@id=\"color-428\"]/a/span/img"));
        selectBlueColor.click();
        Thread.sleep(2500);

        WebElement select38Size = webDriver.findElement(By.xpath("//button[@aria-label='Veličina 38']"));
        select38Size.click();
        Thread.sleep(3000);

        WebElement selectPetiteItemLenth = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/section/div/div[1]/div[1]/section/div[2]/section[1]/div[2]/ul/li[1]/button"));
        selectPetiteItemLenth.click();
        Thread.sleep(3000);

        assertTrue(webDriver.getPageSource()
                .contains("aria-label=\"Veličina 38\" data-qa-anchor=\"selectedSize\" role=\"checkbox\" aria-checked=\"true\""));
        assertTrue(webDriver.getPageSource().contains("aria-label=\"Dužina Petite\" role=\"checkbox\" aria-checked=\"true\""));
        assertTrue(webDriver.getPageSource().contains("aria-selected=\"true\" aria-label=\"Isprana plava\""));
    }


}
