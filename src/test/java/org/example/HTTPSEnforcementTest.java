package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class HTTPSEnforcementTest {
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
        baseUrl = "http://www.bershka.com/hr/h-man.html";
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    void testHTTPSEnforcementPositive() {
        webDriver.get(baseUrl); //basUrl = "http://www.bershka.com/hr/h-man.html"
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        assertEquals("https://www.bershka.com/hr/40259554/h-man.html", webDriver.getCurrentUrl());
    }

    @Test
    void testHTTPSEnforcementPositiveTwo() {
        webDriver.get(baseUrl); //basUrl = "http://www.bershka.com/hr/h-man.html"
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        assertTrue(webDriver.getCurrentUrl().startsWith("https"));
    }

    @Test
    void testHTTPSEnforcementNegative() {
        webDriver.get(baseUrl); //basUrl = "http://www.bershka.com/hr/h-man.html"
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        assertNotEquals("http://www.bershka.com/hr/40259554/h-man.html", webDriver.getCurrentUrl());
    }

    @Test
    void testHTTPSEnforcementNegativeTwo() {
        webDriver.get(baseUrl); //basUrl = "http://www.bershka.com/hr/h-man.html"
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        assertNotEquals("http://www.bershka.com/hr/h-man.html", webDriver.getCurrentUrl());

    }

}