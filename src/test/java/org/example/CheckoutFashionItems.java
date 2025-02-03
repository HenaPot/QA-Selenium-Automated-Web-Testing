package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutFashionItems {
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
    void checkoutSuccess() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(
                By.xpath("//*[@id=\"aria-desktop-menu\"]/div[3]/div/div/ul/li[3]/div/a"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Actions actions = new Actions(webDriver);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(5000);

        WebElement teenSectionHoverArea = webDriver.findElement(By.cssSelector("a[href='/hr/h-bskteen.html']"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", teenSectionHoverArea);
        Thread.sleep(500);

        actions.moveToElement(teenSectionHoverArea).perform();
        Thread.sleep(1000);
        teenSectionHoverArea.click();
        Thread.sleep(2500);

        WebElement footwear = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\"aria-Bskteen\"]/ul/li[4]/div/a/span/span")));
        Thread.sleep(500);
        footwear.click();
        Thread.sleep(2500);

        WebElement firstItem = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/section[1]/div/div/ul/li[1]/div/a"));
        js.executeScript("arguments[0].scrollIntoView(true);", firstItem);
        actions.moveToElement(firstItem).perform();
        Thread.sleep(3500);

        WebElement choose37Size = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/section[1]/div/div/ul/li[1]/div/a/div/div[1]/" +
                        "div[3]/div/div/div/div[2]/ul/li[3]/button"));
        choose37Size.click();
        Thread.sleep(4000);

        WebElement cart = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\"aria-button-shopcart\"]/div")));
        cart.click();
        Thread.sleep(2000);

        WebElement checkoutButton = webDriver.findElement(By.xpath("//button[@data-qa-anchor='processOrderBtn']"));
        Thread.sleep(1500);
        checkoutButton.click();
        Thread.sleep(1500);

        WebElement continueAsGuestButton = webDriver.findElement(By.xpath("//a[@href='/hr/new-checkout.html']"));
        js.executeScript("arguments[0].scrollIntoView(true);", continueAsGuestButton);
        continueAsGuestButton.click();
        Thread.sleep(3500);

        WebElement paymentOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[1]/div/ul/li[2]/button/span")));
        Thread.sleep(200);
        paymentOption.click();
        Thread.sleep(500);

        WebElement continueButton = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[1]/div/div[2]/div/div/button"));
        Thread.sleep(200);
        continueButton.click();
        Thread.sleep(3500);

        WebElement addressInput = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("checkout-shipping-pickup-search_address")));

        addressInput.sendKeys("zadar" + Keys.ENTER);
        Thread.sleep(1500);

        WebElement zadarStore = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\"8733\"]/span")));
        Thread.sleep(200);
        zadarStore.click();

        Thread.sleep(1000);
        WebElement continueModalButton = webDriver.findElement(
                By.xpath("//*[@id=\"aria-modal-PickupSelectDesktop\"]/div/div/div/div/div/div/div[2]/div/button"));
        js.executeScript("arguments[0].scrollIntoView(true);", continueModalButton);
        Thread.sleep(200);

        continueModalButton.click();
        Thread.sleep(2000);

        WebElement firstName = webDriver.findElement(By.id("checkout-shipping-pickup_firstName"));
        firstName.sendKeys("Hena");
        Thread.sleep(1000);

        WebElement lastName = webDriver.findElement(By.id("checkout-shipping-pickup_lastName"));
        lastName.sendKeys("Potogija");
        Thread.sleep(1000);

        WebElement emailInput = webDriver.findElement(By.id("checkout-shipping-pickup_email"));
        emailInput.sendKeys("hena.potogija@stu.ibu.edu.ba");
        Thread.sleep(1000);

        WebElement phone = webDriver.findElement(By.id("checkout-shipping-pickup_phone"));
        phone.sendKeys("603237378");
        Thread.sleep(1000);

        WebElement continueToPaymentButton = webDriver.findElement(
                By.xpath("//*[@id=\"checkout-shipping-pickup_submit\"]/span"));
        Thread.sleep(200);
        continueToPaymentButton.click();
        Thread.sleep(1000);

        WebElement totalMoney = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\"main-content\"]/div/div[2]/div[2]/div/div[2]/div/div/div[2]/div/div/span")));
        String stringMoney = totalMoney.getText();
        Thread.sleep(2000);

        assertEquals("29,99 €", stringMoney);

    }

    @Test
    void unsuccessfulCheckoutBadPhoneNumber() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(
                By.xpath("//*[@id=\"aria-desktop-menu\"]/div[3]/div/div/ul/li[3]/div/a"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Actions actions = new Actions(webDriver);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(5000);

        WebElement teenSectionHoverArea = webDriver.findElement(By.cssSelector("a[href='/hr/h-bskteen.html']"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", teenSectionHoverArea);
        Thread.sleep(500);

        actions.moveToElement(teenSectionHoverArea).perform();
        Thread.sleep(1000);
        teenSectionHoverArea.click();
        Thread.sleep(2500);

        WebElement footwear = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\"aria-Bskteen\"]/ul/li[4]/div/a/span/span")));
        Thread.sleep(500);
        footwear.click();
        Thread.sleep(2500);

        WebElement firstItem = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/section[1]/div/div/ul/li[1]/div/a"));
        js.executeScript("arguments[0].scrollIntoView(true);", firstItem);
        actions.moveToElement(firstItem).perform();
        Thread.sleep(3500);

        WebElement choose37Size = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[2]/section[1]/div/div/ul/li[1]/div/a/div/div[1]/" +
                        "div[3]/div/div/div/div[2]/ul/li[3]/button"));
        choose37Size.click();
        Thread.sleep(4000);

        WebElement cart = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\"aria-button-shopcart\"]/div")));
        cart.click();
        Thread.sleep(2000);

        WebElement checkoutButton = webDriver.findElement(By.xpath("//button[@data-qa-anchor='processOrderBtn']"));
        Thread.sleep(1500);
        checkoutButton.click();
        Thread.sleep(1500);

        WebElement continueAsGuestButton = webDriver.findElement(By.xpath("//a[@href='/hr/new-checkout.html']"));
        js.executeScript("arguments[0].scrollIntoView(true);", continueAsGuestButton);
        continueAsGuestButton.click();
        Thread.sleep(3500);

        WebElement paymentOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[1]/div/ul/li[2]/button/span")));
        Thread.sleep(200);
        paymentOption.click();
        Thread.sleep(500);

        WebElement continueButton = webDriver.findElement(
                By.xpath("//*[@id=\"main-content\"]/div/div[1]/div[1]/div/div[2]/div/div/button"));
        Thread.sleep(200);
        continueButton.click();
        Thread.sleep(3500);

        WebElement addressInput = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("checkout-shipping-pickup-search_address")));

        addressInput.sendKeys("zadar" + Keys.ENTER);
        Thread.sleep(1500);

        WebElement zadarStore = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id=\"8733\"]/span")));
        Thread.sleep(200);
        zadarStore.click();

        Thread.sleep(1000);
        WebElement continueModalButton = webDriver.findElement(
                By.xpath("//*[@id=\"aria-modal-PickupSelectDesktop\"]/div/div/div/div/div/div/div[2]/div/button"));
        js.executeScript("arguments[0].scrollIntoView(true);", continueModalButton);
        Thread.sleep(200);

        continueModalButton.click();
        Thread.sleep(2000);

        WebElement firstName = webDriver.findElement(By.id("checkout-shipping-pickup_firstName"));
        firstName.sendKeys("Hena");
        Thread.sleep(1000);

        WebElement lastName = webDriver.findElement(By.id("checkout-shipping-pickup_lastName"));
        lastName.sendKeys("Potogija");
        Thread.sleep(1000);

        WebElement emailInput = webDriver.findElement(By.id("checkout-shipping-pickup_email"));
        emailInput.sendKeys("hena.potogija@stu.ibu.edu.ba");
        Thread.sleep(1000);

        WebElement phone = webDriver.findElement(By.id("checkout-shipping-pickup_phone"));
        phone.sendKeys("cdcds");
        Thread.sleep(1000);

        WebElement continueToPaymentButton = webDriver.findElement(
                By.xpath("//*[@id=\"checkout-shipping-pickup_submit\"]/span"));
        Thread.sleep(200);
        continueToPaymentButton.click();
        Thread.sleep(1000);

        WebElement errorMessage = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("helperMessagecheckout-shipping-pickup_phone")));

        assertEquals("Broj telefona nije ispravan", errorMessage.getText());

    }

}
