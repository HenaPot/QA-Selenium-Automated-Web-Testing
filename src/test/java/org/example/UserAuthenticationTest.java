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

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserAuthenticationTest {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/Hena/chromedriver-win64 (1)/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36";
        options.addArguments("user-agent=" + userAgent);

        options.addArguments("--user-data-dir=/Users/Hena/Chome-Profiles");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        webDriver = new ChromeDriver(options);
        ((JavascriptExecutor) webDriver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
        baseUrl = "https://www.bershka.com/hr/h-man.html";
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    void testRegistrationBadPassword() throws InterruptedException {
        webDriver.get(baseUrl);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        WebElement accessUserAuthButton = webDriver.findElement(By.xpath("//a[@href='/hr/logon.html?showRegister=true']"));
        Thread.sleep((int)(Math.random() * 1000) + 500); // Random delay between 500ms and 1500ms

        accessUserAuthButton.click();
        Thread.sleep(7000);

        WebElement startRegistrationButton = webDriver.findElement(By.xpath("//a[@href='/hr/register.html']"));
        Thread.sleep((int)(Math.random() * 1000) + 500); // Random delay between 500ms and 1500ms

        startRegistrationButton.click();
        Thread.sleep(7000);

        WebElement emailInput = webDriver.findElement(By.id("register_email"));
        emailInput.sendKeys("hena.potogija@stu.ibu.edu.ba");
        Thread.sleep((int)(Math.random() * 1000) + 500); // Random delay between 500ms and 1500ms


        WebElement passwordInput = webDriver.findElement(By.id("register_password"));
        passwordInput.sendKeys("ThisIsJustATest");
        Thread.sleep((int)(Math.random() * 1000) + 500); // Random delay between 500ms and 1500ms
        WebElement stayLoggedInCheckbox = webDriver.findElement(By.xpath("//label[@for='register_rememberMe']"));
        Thread.sleep((int)(Math.random() * 1000) + 500); // Random delay between 500ms and 1500ms

        stayLoggedInCheckbox.click();

        Thread.sleep(1000);
        WebElement acceptPrivacyPolicy = webDriver.findElement(By.xpath("//label[@for='register_privacyPolicyAccepted']"));
        Thread.sleep(500);
        acceptPrivacyPolicy.click();

        Thread.sleep(1000);
        WebElement closePrivacyPolicy = webDriver.findElement(By.xpath("//*[@id=\"aria-modal-StaticContentModal\"]/div/button"));
        Thread.sleep(200);
        closePrivacyPolicy.click();
        Thread.sleep(2000);

        WebElement loginButton = webDriver.findElement(By.id("register_submit"));
        Thread.sleep(700);
        loginButton.click();
        Thread.sleep(2000);

        WebElement errorValidationMessage = webDriver.findElement(By.xpath("//*[@id=\"helperMessageregister_password\"]"));
        Thread.sleep(200);
        assertEquals("Najmanje 8 znakova, uključujući mala i velika slova i jedan broj. Ne ponavljajte isti znak više od 3 puta",
                errorValidationMessage.getText());
    }

    @Test
    void testLoginGoodEmailAndGoodPassword() throws InterruptedException {
        webDriver.get(baseUrl);
        Actions actions = new Actions(webDriver);
        WebElement closeCookies = webDriver.findElement(
                By.xpath("//*[@id=\"aria-desktop-menu\"]/div[3]/div/div/ul/li[3]/div/a"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", closeCookies);
        Thread.sleep(5000);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        WebElement signupButton = webDriver.findElement(By.xpath("//a[@href='/hr/logon.html?showRegister=true']"));
        actions.moveToElement(signupButton).perform();
        Thread.sleep((int)(Math.random() * 1000) + 500);
        signupButton.click();
        Thread.sleep(7000);

        WebElement emailInput = webDriver.findElement(By.id("login_email"));
        js.executeScript("arguments[0].scrollIntoView(true);", emailInput);
        Thread.sleep(1500);

        char[] email = "hena.potogija@stu.ibu.edu.ba".toCharArray();
        for (char c : email) {
            emailInput.sendKeys(String.valueOf(c));
            Thread.sleep((int) (Math.random() * 2) + 50);
        }
        Thread.sleep((int)(Math.random() * 1000) + 500);

        WebElement passwordInput = webDriver.findElement(By.id("login_password"));
        js.executeScript("arguments[0].scrollIntoView(true);", emailInput);
        Thread.sleep((int)(Math.random() * 1000) + 500);

        char[] password = "ThisIsJustATea;1".toCharArray();
        for (char c : password) {
            passwordInput.sendKeys(String.valueOf(c));
            Thread.sleep((int) (Math.random() * 2) + 50);
        }

        Thread.sleep((int)(Math.random() * 1000) + 500);

        WebElement loginButton = webDriver.findElement(By.xpath("//*[@id=\"login_submitLogin\"]/span"));
        actions.moveToElement(signupButton).perform();
        Thread.sleep((int)(Math.random() * 1000) + 500);
        loginButton.click();
        Thread.sleep(2000);

        WebElement loggedInMark = webDriver.findElement(
                By.xpath("//span[@aria-hidden='true' and @class='account-icon-desktop__label']"));
        assertEquals("Moj račun", loggedInMark.getText());
    }

    @Test
    void testBadLoginEmailAndPassword() throws InterruptedException {
        webDriver.get(baseUrl);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        WebElement signupButton = webDriver.findElement(By.xpath("//a[@href='/hr/logon.html?showRegister=true']"));
        Thread.sleep(700);
        signupButton.click();
        Thread.sleep(7000);
        WebElement emailInput = webDriver.findElement(By.id("login_email"));
        emailInput.sendKeys("test-email@gmail.com");
        Thread.sleep(1000);

        WebElement passwordInput = webDriver.findElement(By.id("login_password"));
        passwordInput.sendKeys("123");
        Thread.sleep(1000);

        WebElement loginButton = webDriver.findElement(By.xpath("//*[@id=\"login_submitLogin\"]/span"));
        Thread.sleep(700);
        loginButton.click();
        Thread.sleep(2000);

        WebElement loggedInMark = webDriver.findElement(
                By.xpath("//span[@aria-hidden='true' and @class='account-icon-desktop__label']"));
        assertEquals("PRIJAVI SE", loggedInMark.getText());
    }



}
