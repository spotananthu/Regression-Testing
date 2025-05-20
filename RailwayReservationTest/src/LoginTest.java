import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginTest {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "RailwayReservationTest/chromedriver-mac-arm64/chromedriver");

        List<String[]> credentials = ExcelReader.getCredentials("RailwayReservationTest/src/sourceanddest.xlsx");

        for (String[] creds : credentials) {
            WebDriver driver = new ChromeDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://www.easemytrip.com/railways/");

            // Maximize window
            driver.manage().window().maximize();

            // Enter from and to destination
            WebElement fromInput=driver.findElement(By.xpath("//input[@placeholder='Choose Source station']"));
            fromInput.clear();
            fromInput.sendKeys(creds[0]);
            Thread.sleep(1000); // wait for suggestions
            fromInput.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
            WebElement toInput=driver.findElement(By.xpath("//input[@placeholder='Choose destination station']"));
            toInput.clear();
            toInput.sendKeys(creds[1]);
            Thread.sleep(1000); // wait for suggestions
            toInput.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
            driver.findElement(By.xpath("//input[@placeholder='Depart Date']")).click();
            driver.findElement(By.xpath("/html/body/div[1]/div[1]/table/tbody/tr[5]/td[6]/a")).click();

            System.out.println("Entered destination from : " + creds[0]);
            System.out.println("Entered destination to : " + creds[1]);
            // Click on the search button

            WebElement searchButton = driver.findElement(By.xpath("//input[@id='SearchAll']"));
            searchButton.click();
            Thread.sleep(20000);

            try {
                WebElement element= driver.findElement(By.xpath("/html/body/form/div[2]/div[2]/div/div[8]/a"));
                if (element.isDisplayed()) {
                    System.out.println("✅ Search train has succeeded for from " + creds[0] + " to " + creds[1]);
                }
            } catch (Exception e) {
                System.out.println("❌ Search train has failed for from " + creds[0] + " to " + creds[1]);
            }
            driver.quit();
        }
    }
}