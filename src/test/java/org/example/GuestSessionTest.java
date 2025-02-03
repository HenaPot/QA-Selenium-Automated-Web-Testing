package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GuestSessionTest {
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
    public void testGuestDoesNotSeeMojRacun() throws InterruptedException {
        webDriver.get(baseUrl);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        WebElement closeCookies = webDriver.findElement(
                By.xpath("//*[@id=\"aria-desktop-menu\"]/div[3]/div/div/ul/li[3]/div/a"));
        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(5000);

        assertThrows(NoSuchElementException.class, () -> {
            WebElement loggedInMark = webDriver.findElement(
                    By.xpath("//span[@aria-hidden='true' and @class='account-icon-desktop__label' and text()='Moj račun']"));
            assertEquals("Moj račun", loggedInMark.getText());
        });
    }
    @Test
    public void testCriticalCookiesPresent() throws InterruptedException {
        webDriver.get(baseUrl);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        WebElement closeCookies = webDriver.findElement(
                By.xpath("//*[@id=\"aria-desktop-menu\"]/div[3]/div/div/ul/li[3]/div/a"));
        js.executeScript("arguments[0].click();", closeCookies);

        Thread.sleep(6000);
        Set<Cookie> cookies = webDriver.manage().getCookies();

        assertTrue(cookies.stream().anyMatch(cookie -> cookie.getName().equals("ITXSESSIONID")));
        assertTrue(cookies.stream().anyMatch(cookie -> cookie.getName().equals("FPC")));
        assertTrue(cookies.stream().anyMatch(cookie -> cookie.getName().equals("BSKSESSION")));
    }

}
