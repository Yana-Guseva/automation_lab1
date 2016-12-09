package com.yanaguseva.automation;

import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Lab1 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Iana_Guseva/Selenium/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://temp-mail.ru/";
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
    }

    @Test
    public void testLab1() throws Exception {
        driver.get(baseUrl);
        String mail = driver.findElement(By.id("mail")).getAttribute("value");
        driver.get("http://lingualeo.com/ru");
        driver.findElement(By.id("registerFormEmail")).clear();
        driver.findElement(By.id("registerFormEmail")).sendKeys(mail);
        driver.findElement(By.id("registerFormPassword")).clear();
        driver.findElement(By.id("registerFormPassword")).sendKeys("1234qwer");
        driver.findElement(By.id("registerFormBtn")).click();
        driver.get(baseUrl);
        driver.findElement(By.linkText("Как заниматься на Lingual")).click();
        driver.findElement(By.xpath("//span[text()='Подтвердить']")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}

