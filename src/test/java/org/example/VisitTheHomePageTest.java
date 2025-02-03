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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class VisitTheHomePageTest {
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
        baseUrl = "https://www.bershka.com/hr/h-man.html";
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    void openDiscountedFashionItems() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(By.xpath("//div[@id='onetrust-close-btn-container']/button"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        js.executeScript("arguments[0].click();", closeCookies);

        Thread.sleep(7000);

        WebElement discountedPage = webDriver.findElement(By.xpath("//main"));
        discountedPage.click();

        Thread.sleep(2000);
        assertEquals("https://www.bershka.com/hr/men/clothes/sales-n5383.html", webDriver.getCurrentUrl());
    }

    @Test
    void openJacketSalesPage() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(By.xpath("//div[@id='onetrust-close-btn-container']/button"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(5000);

        WebElement jacketPage = webDriver.findElement(By.xpath("//*[@id='main-content']/div/div/section/div/ul/li[1]/a/figure/figcaption"));
        jacketPage.click();
        Thread.sleep(2000);

        assertEquals("https://www.bershka.com/hr/mu%C5%A1karci/sni%C5%BEenje-do--60%25/jakne-%26-kaputi-c1010747925.html", webDriver.getCurrentUrl());
    }

    @Test
    void openPantsAndJeansPage() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(By.xpath("//div[@id='onetrust-close-btn-container']/button"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(5000);

        WebElement jacketPage = webDriver.findElement(By.xpath("//*[@id='main-content']/div/div/section/div/ul/li[1]/a/figure/figcaption"));
        jacketPage.click();
        Thread.sleep(2000);

        assertEquals("https://www.bershka.com/hr/mu%C5%A1karci/sni%C5%BEenje-do--60%25/jakne-%26-kaputi-c1010747925.html", webDriver.getCurrentUrl());
    }

    @Test
    void discoverNewFashion() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(By.xpath("//div[@id='onetrust-close-btn-container']/button"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(5000);

        WebElement discoverButton = webDriver.findElement(By.cssSelector("a.bds-button.bds-button-pill.bds-button--size-l.bds-button--primary"));
        discoverButton.click();
        Thread.sleep(2000);

        assertEquals("https://www.bershka.com/hr/muskarci/novo-n3745.html", webDriver.getCurrentUrl());
    }

    @Test
    void viewStreetwearFashion() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(By.xpath("//div[@id='onetrust-close-btn-container']/button"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(5000);

        WebElement discoverButton = webDriver.findElement(By.xpath("//*[@id='main-content']/div/div/section/section[1]/div/figure[1]/a"));
        discoverButton.click();
        Thread.sleep(2000);

        assertEquals("https://www.bershka.com/hr/pag/bershkastyle.html?style=66206392&gender=66486573", webDriver.getCurrentUrl());
    }

    @Test
    void discountSignupBadEmailBadDOB() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(By.xpath("//div[@id='onetrust-close-btn-container']/button"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(5000);

        WebElement discoverButton = webDriver.findElement(By.xpath("//a[@href='/hr/newsletter.html?origin=footer_web']"));
        js.executeScript("arguments[0].scrollIntoView(true);", discoverButton);
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", discoverButton);
        Thread.sleep(3000);

        WebElement emailInput = webDriver.findElement(By.id("newsletter-subscription_email"));
        emailInput.sendKeys("bademail");
        Thread.sleep(1000);

        WebElement dobInput = webDriver.findElement(By.id("newsletter-subscription_birthdate"));
        dobInput.sendKeys("badDOB");
        Thread.sleep(1000);

        WebElement femaleRadioButton = webDriver.findElement(By.id("Žena"));
        femaleRadioButton.click();
        Thread.sleep(1000);

        WebElement acceptPolicyInput = webDriver.findElement(By.xpath("//*[@id=\"newsletter-subscription\"]/div[2]/fieldset/div[7]/div/div/label"));
        Thread.sleep(200);
        acceptPolicyInput.click();
        Thread.sleep(2000);

        WebElement closePolicyView = webDriver.findElement(By.xpath("//*[@id=\"aria-modal-StaticContentModal\"]/div/button"));
        Thread.sleep(200);
        closePolicyView.click();
        Thread.sleep(2000);

        WebElement submitButton = webDriver.findElement(By.xpath("//*[@id=\"newsletter-subscription_submit\"]/span"));
        Thread.sleep(200);
        submitButton.click();
        Thread.sleep(2000);

        WebElement errorMessage = webDriver.findElement(By.id("helperMessagenewsletter-subscription_email"));
        assertEquals("Format e-pošte nije valjan", errorMessage.getText());
    }

    @Test
    void discountSignupGoodEmailGoodDOB() throws InterruptedException {
        webDriver.get(baseUrl);
        WebElement closeCookies = webDriver.findElement(By.xpath("//div[@id='onetrust-close-btn-container']/button"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(5000);

        WebElement discoverButton = webDriver.findElement(By.xpath("//a[@href='/hr/newsletter.html?origin=footer_web']"));
        js.executeScript("arguments[0].scrollIntoView(true);", discoverButton);
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", discoverButton);
        Thread.sleep(3000);

        WebElement emailInput = webDriver.findElement(By.id("newsletter-subscription_email"));
        emailInput.sendKeys("hena.potogija@stu.ibu.edu.ba");
        Thread.sleep(1000);

        WebElement dobInput = webDriver.findElement(By.id("newsletter-subscription_birthdate"));
        dobInput.sendKeys("27/01");
        Thread.sleep(1000);

        WebElement femaleRadioButton = webDriver.findElement(By.id("Žena"));
        femaleRadioButton.click();
        Thread.sleep(1000);

        WebElement acceptPolicyInput = webDriver.findElement(By.xpath("//*[@id=\"newsletter-subscription\"]/div[2]/fieldset/div[7]/div/div/label"));
        Thread.sleep(200);
        acceptPolicyInput.click();
        Thread.sleep(2000);

        WebElement closePolicyView = webDriver.findElement(By.xpath("//*[@id=\"aria-modal-StaticContentModal\"]/div/button"));
        Thread.sleep(200);
        closePolicyView.click();
        Thread.sleep(2000);

        WebElement submitButton = webDriver.findElement(By.xpath("//*[@id=\"newsletter-subscription_submit\"]/span"));
        Thread.sleep(200);
        submitButton.click();
        Thread.sleep(4000);

        assertEquals("https://www.bershka.com/hr/40259554/h-man.html", webDriver.getCurrentUrl());
    }
}
