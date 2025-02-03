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
class socialMediaTest {
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
    void Instagram() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        Thread.sleep(5000);

        //Instagram
        WebElement instagram = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[7]/footer[1]/div[1]/div[1]/div[5]/ul[1]/li[1]/a[1]"));
        js.executeScript("arguments[0].click();", instagram);
        Thread.sleep(3000);

        String currentWindowHandle = webDriver.getWindowHandle();
        Set<String> windowHandles = webDriver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        assertEquals("https://www.instagram.com/bershka/", webDriver.getCurrentUrl());
        webDriver.close();
        webDriver.switchTo().window(currentWindowHandle);
        Thread.sleep(2000);
        }

    @Test
    void Facebook() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Thread.sleep(5000);
        //Facebook
        WebElement facebook = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[7]/footer[1]/div[1]/div[1]/div[5]/ul[1]/li[2]/a[1]"));
        js.executeScript("arguments[0].click();", facebook);
        Thread.sleep(3000);

        String currentWindowHandle = webDriver.getWindowHandle();
        Set<String> windowHandles = webDriver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }


        assertEquals("https://www.facebook.com/bershka", webDriver.getCurrentUrl());
        webDriver.close();
        webDriver.switchTo().window(currentWindowHandle);
        Thread.sleep(2000);
    }

    @Test
    void Twitter() throws InterruptedException {
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //Twitter
        WebElement twitter = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[7]/footer[1]/div[1]/div[1]/div[5]/ul[1]/li[3]/a[1]"));
        js.executeScript("arguments[0].click();", twitter);
        Thread.sleep(3000);

        String currentWindowHandle = webDriver.getWindowHandle();
        Set<String> windowHandles = webDriver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        assertEquals("https://x.com/bershka?mx=2", webDriver.getCurrentUrl());
        webDriver.close();
        webDriver.switchTo().window(currentWindowHandle);
        Thread.sleep(2000);
    }

    @Test
    void Tiktok() throws InterruptedException {
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //Tiktok
        WebElement tiktok = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[7]/footer[1]/div[1]/div[1]/div[5]/ul[1]/li[4]/a[1]"));
        js.executeScript("arguments[0].click();", tiktok);
        Thread.sleep(3000);

        String currentWindowHandle = webDriver.getWindowHandle();
        Set<String> windowHandles = webDriver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
        assertEquals("https://www.tiktok.com/@bershka", webDriver.getCurrentUrl());
        webDriver.close();
        webDriver.switchTo().window(currentWindowHandle);
        Thread.sleep(2000);
    }

    @Test
    void Snapchat() throws InterruptedException {
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //Snapchat
        WebElement snapchat = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[7]/footer[1]/div[1]/div[1]/div[5]/ul[1]/li[5]/a[1]"));
        js.executeScript("arguments[0].click();", snapchat);
        Thread.sleep(3000);

        String currentWindowHandle = webDriver.getWindowHandle();
        Set<String> windowHandles = webDriver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }


        assertEquals("https://www.snapchat.com/add/bershkaofficial", webDriver.getCurrentUrl());
        webDriver.close();
        webDriver.switchTo().window(currentWindowHandle);
        Thread.sleep(2000);
    }

    @Test
    void Youtube() throws InterruptedException {
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //Youtube
        WebElement youtube = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[7]/footer[1]/div[1]/div[1]/div[5]/ul[1]/li[6]/a[1]"));
        js.executeScript("arguments[0].click();", youtube);
        Thread.sleep(3000);

        String currentWindowHandle = webDriver.getWindowHandle();
        Set<String> windowHandles = webDriver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }


        assertEquals("https://www.youtube.com/@bershka", webDriver.getCurrentUrl());
        webDriver.close();
        webDriver.switchTo().window(currentWindowHandle);
        Thread.sleep(2000);
    }

    @Test
    void Pinterest() throws InterruptedException {
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //Pinterest
        WebElement pinterest = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[7]/footer[1]/div[1]/div[1]/div[5]/ul[1]/li[7]/a[1]"));
        js.executeScript("arguments[0].click();", pinterest);
        Thread.sleep(3000);

        String currentWindowHandle = webDriver.getWindowHandle();
        Set<String> windowHandles = webDriver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        assertEquals("https://www.pinterest.com/bershka/", webDriver.getCurrentUrl());
        webDriver.close();
        webDriver.switchTo().window(currentWindowHandle);
        Thread.sleep(2000);
    }

    @Test
    void Spotify() throws InterruptedException {
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //Spotify
        WebElement spotify = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[7]/footer[1]/div[1]/div[1]/div[5]/ul[1]/li[8]/a[1]"));
        js.executeScript("arguments[0].click();", spotify);
        Thread.sleep(3000);

        String currentWindowHandle = webDriver.getWindowHandle();
        Set<String> windowHandles = webDriver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(currentWindowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        assertEquals("https://open.spotify.com/user/bershka", webDriver.getCurrentUrl());
        webDriver.close();
        webDriver.switchTo().window(currentWindowHandle);
        Thread.sleep(2000);

    }




}

