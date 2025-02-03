package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class preferencesTest {
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
        baseUrl = "https://www.bershka.com/ba/h-man.html";


    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    void languageAndPlace() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        Thread.sleep(4000);
        WebElement button = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[7]/footer[1]/div[1]/div[2]/div[2]/div[1]/button[1]"));
        button.click();
        Thread.sleep(2000);
        WebElement selectbtn = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[8]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]"));
        selectbtn.click();
        Thread.sleep(2000);
        WebElement input = webDriver.findElement(By.id("input-search"));
        input.sendKeys("bos");
        Thread.sleep(500);
        WebElement option = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[8]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/ul[1]/li[1]"));
        option.click();
        Thread.sleep(2000);
        WebElement eng = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[8]/div[2]/div[2]/div[1]/div[1]/div[2]/label[1]"));

        WebElement label = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[8]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]/span[1]/span[2]"));
        eng.click();
        Thread.sleep(2000);
        assertTrue(webDriver.getPageSource().contains("<label for=\"en\" tabindex=\"0\" class=\"country-language-item selected\"><span class=\"country-language-link\">en</span></label>"));
        assertEquals("Bosnia and Herzegovina", label.getText());

        WebElement saveBtn = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[8]/div[2]/div[2]/div[3]/button[1]"));
        saveBtn.click();
        Thread.sleep(6000);

        assertTrue(webDriver.getPageSource().contains("div id=\"INDWrap\" lang=\"en\" "));
    }

    @Test
    void cookiePreferences() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.get(baseUrl);
        Thread.sleep(4000);
        WebElement button = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[7]/footer[1]/div[1]/div[2]/div[1]/ul[1]/li[5]/span[1]/button[1]"));
        button.click();
        Thread.sleep(2000);

        WebElement firstPref = webDriver.findElement(By.xpath("//span[@class='ot-switch-nob']"));

        WebElement secondPref = webDriver.findElement(RelativeLocator.with(By.xpath("//span[@class='ot-switch-nob']")).below(firstPref));
        WebElement thirdPref = webDriver.findElement(RelativeLocator.with(By.xpath("//span[@class='ot-switch-nob']")).below(secondPref));

        firstPref.click();
        secondPref.click();
        thirdPref.click();
        Thread.sleep(2000);

        WebElement submit = webDriver.findElement(By.xpath("//button[@class='save-preference-btn-handler onetrust-close-btn-handler']"));
        submit.click();
        Thread.sleep(2000);

        WebElement button2 = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[7]/footer[1]/div[1]/div[2]/div[1]/ul[1]/li[5]/span[1]/button[1]"));
        button2.click();
        Thread.sleep(2000);
        assertTrue(webDriver.getPageSource().contains("<input type=\"checkbox\" name=\"ot-group-id-C0003\" id=\"ot-group-id-C0003\" aria-checked=\"false\" role=\"switch\" class=\"category-switch-handler\" data-optanongroupid=\"C0003\" aria-labelledby=\"ot-header-id-C0003\" tabindex=\"0\">"));
        assertTrue(webDriver.getPageSource().contains("<input type=\"checkbox\" name=\"ot-group-id-C0003\" id=\"ot-group-id-C0003\" aria-checked=\"false\" "));
    }


}