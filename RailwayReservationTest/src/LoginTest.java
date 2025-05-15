import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginTest {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/I529006/Desktop/SQAT/RailwayReservationTest/chromedriver-mac-arm64/chromedriver");

        List<String[]> credentials = ExcelReader.getCredentials("/Users/I529006/Desktop/SQAT/RailwayReservationTest/src/credentials.xlsx");

        for (String[] creds : credentials) {
            WebDriver driver = new ChromeDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://www.irctc.co.in/nget/train-search");

            // Maximize window
            driver.manage().window().maximize();

            // Click on LOGIN button
            WebElement loginLink = driver.findElement(By.xpath("//a[contains(text(),'LOGIN')]"));
            loginLink.click();

            // Wait for login modal to appear (simple wait)
            Thread.sleep(2000);

            // Enter username and password
            driver.findElement(By.cssSelector("input[formcontrolname='userid']")).sendKeys(creds[0]);
            driver.findElement(By.cssSelector("input[formcontrolname='password']")).sendKeys(creds[1]);

            System.out.println("Entered credentials for user: " + creds[0]);

            // Pause to allow manual CAPTCHA input
            System.out.println("⏳ Please enter CAPTCHA manually and press Login within 20 seconds...");
            Thread.sleep(20000);

            // Click on Login button
            WebElement signInButton = driver.findElement(By.xpath("//button[contains(text(),'SIGN IN')]"));
            signInButton.click();

            // Wait for response
            Thread.sleep(20000);

          if (driver.findElement(By.xpath("/html/body/app-root/app-home/div[1]/app-header/div[2]/div[2]/div[2]/nav/ul/li[12]/a")).isDisplayed()) {
                System.out.println("✅ Login has succeeded for user " + creds[0]);
            } else {
                System.out.println("❌ Login has failed for user " + creds[0]);
            }

            driver.quit();
        }
    }
}

