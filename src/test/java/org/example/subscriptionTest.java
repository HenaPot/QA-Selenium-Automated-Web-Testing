package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class subscriptionTest {
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

    @Order(1)
    @Test
    void Subscribe() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        Thread.sleep(5000);

        //Instagram
        WebElement instagram = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/main[1]/div[1]/div[1]/section[1]/div[1]/a[1]"));
        js.executeScript("arguments[0].click();", instagram);
        Thread.sleep(3000);

        WebElement email = webDriver.findElement(By.id("newsletter-subscription_email"));
        WebElement date = webDriver.findElement(By.id("newsletter-subscription_birthdate"));
        WebElement gender = webDriver.findElement(By.id("Žena"));
        WebElement privacy = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/main[1]/div[1]/div[1]/section[1]/section[1]/form[1]/div[2]/fieldset[1]/div[7]/div[1]/div[1]"));

        email.sendKeys("anja.sehovac@stu.ibu.edu.ba");
        date.sendKeys("1206");
        gender.click();
        js.executeScript("arguments[0].scrollIntoView(true);", privacy);
        privacy.click();
        Thread.sleep(1000);
        WebElement close = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[5]/div[1]/button[1]"));
        close.click();
        Thread.sleep(1000);

        WebElement submit = webDriver.findElement(By.id("newsletter-subscription_submit"));
        submit.click();
        Thread.sleep(3000);

        assertTrue(webDriver.getPageSource().contains("<div aria-live=\"assertive\" class=\"notification-wrapper\" role=\"dialog\" aria-modal=\"true\" aria-hidden=\"false\" aria-expanded=\"true\" tabindex=\"0\">"));

        Thread.sleep(2000);

    }
    @Order(2)
    @Test
    void Unsubscribe() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);

        Thread.sleep(3000);
        WebElement unsub = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[7]/footer[1]/div[1]/div[1]/div[2]/ul[1]/li[8]/button[1]"));
        unsub.click();
        Thread.sleep(2000);
        WebElement email = webDriver.findElement(By.id("newsletter-unsubscribe_email"));
        email.sendKeys("anja.sehovac@stu.ibu.edu.ba");
        Thread.sleep(1000);
        WebElement submit = webDriver.findElement(By.id("newsletter-unsubscribe_submit"));
        submit.click();
        Thread.sleep(3000);
        WebElement close = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/section[1]/div[2]/button[1]"));
        assertTrue(close.isDisplayed());
    }

    @Order(2)
    @Test
    void UnsubscribeFalse() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);

        Thread.sleep(3000);
        WebElement unsub = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[7]/footer[1]/div[1]/div[1]/div[2]/ul[1]/li[8]/button[1]"));
        unsub.click();
        Thread.sleep(2000);
        WebElement email = webDriver.findElement(By.id("newsletter-unsubscribe_email"));
        email.sendKeys("anja.sehovac03@gmail.com");
        Thread.sleep(1000);
        WebElement submit = webDriver.findElement(By.id("newsletter-unsubscribe_submit"));
        submit.click();
        Thread.sleep(3000);
        WebElement close = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/section[1]/div[2]/button[1]"));
        assertFalse(close.isDisplayed());
    }
}
