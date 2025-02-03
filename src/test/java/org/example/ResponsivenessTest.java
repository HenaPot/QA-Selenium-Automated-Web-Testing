package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResponsivenessTest {
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
    void testIPhoneXResolutionViewMainSectionMenu() {
        webDriver.manage().window().setSize(new Dimension(375, 812));
        webDriver.get(baseUrl);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        WebElement menuButton = webDriver.findElement(By.cssSelector(".menu-desktop__nav"));
        assertTrue(menuButton.isDisplayed());
    }

    @Test
    void testIPadTabletViewSearchButtonDisplayed() throws InterruptedException {
        webDriver.manage().window().setSize(new Dimension(768, 1024));
        webDriver.get(baseUrl);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        WebElement searchButton = webDriver.findElement(By.xpath("//button[@data-qa-anchor='searchBtn']"));
        searchButton.click();
        Thread.sleep(3000);
        assertTrue(searchButton.isDisplayed());
    }

    @Test
    void testDesktopViewFullHeaderAndFooter() throws InterruptedException {
        webDriver.manage().window().maximize();
        webDriver.get(baseUrl);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        WebElement header = webDriver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/header"));
        WebElement footer = webDriver.findElement(By.xpath("//footer[@data-js-selector='footer']"));
        Thread.sleep(2000);

        assertTrue(header.isDisplayed());
        assertTrue(footer.isDisplayed());
    }

}
