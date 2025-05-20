import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginTest {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "RailwayReservationTest/chromedriver-mac-arm64/chromedriver");

        List<String[]> credentials = ExcelReader.getCredentials("RailwayReservationTest/src/sourceanddest.xlsx");
        int total = 0, passed = 0, failed = 0;
        long suiteStart = System.currentTimeMillis();
        for (String[] creds : credentials) {
            total++;
            long testStart = System.currentTimeMillis();
            WebDriver driver = new ChromeDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://www.easemytrip.com/railways/");
            driver.manage().window().maximize();
            try {
                WebElement fromInput = driver.findElement(By.xpath("//input[@placeholder='Choose Source station']"));
                highlightElement(driver, fromInput);
                fromInput.clear();
                fromInput.sendKeys(creds[0]);
                Thread.sleep(1000);
                fromInput.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
                WebElement toInput = driver.findElement(By.xpath("//input[@placeholder='Choose destination station']"));
                highlightElement(driver, toInput);
                toInput.clear();
                toInput.sendKeys(creds[1]);
                Thread.sleep(1000);
                toInput.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
                driver.findElement(By.xpath("//input[@placeholder='Depart Date']")).click();
                driver.findElement(By.xpath("/html/body/div[1]/div[1]/table/tbody/tr[5]/td[6]/a")).click();
                System.out.println("Entered destination from : " + creds[0]);
                System.out.println("Entered destination to : " + creds[1]);
                WebElement searchButton = driver.findElement(By.xpath("//input[@id='SearchAll']"));
                highlightElement(driver, searchButton);
                searchButton.click();
                Thread.sleep(20000);
                WebElement element = driver.findElement(By.xpath("/html/body/form/div[2]/div[2]/div/div[8]/a"));
                if (element.isDisplayed()) {
                    System.out.println("✅ Search train has succeeded for from " + creds[0] + " to " + creds[1]);
                    passed++;
                    ExcelReader.writeResult("RailwayReservationTest/src/sourceanddest.xlsx", total, "PASS");
                }
            } catch (Exception e) {
                System.out.println("❌ Search train has failed for from " + creds[0] + " to " + creds[1]);
                failed++;
                takeScreenshot(driver, creds[0] + "_" + creds[1]);
                ExcelReader.writeResult("RailwayReservationTest/src/sourceanddest.xlsx", total, "FAIL");
            }
            long testEnd = System.currentTimeMillis();
            System.out.println("Test execution time: " + (testEnd - testStart) / 1000.0 + " seconds");
            driver.quit();
        }
        long suiteEnd = System.currentTimeMillis();
        String summary = String.format("\nTest Summary:\nTotal: %d\nPassed: %d\nFailed: %d\nTotal Time: %.2f seconds\n", total, passed, failed, (suiteEnd-suiteStart)/1000.0);
        System.out.println(summary);
        try (FileOutputStream fos = new FileOutputStream("RailwayReservationTest/src/test_summary.txt")) {
            fos.write(summary.getBytes());
        }
    }

    private static void takeScreenshot(WebDriver driver, String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File dest = new File("RailwayReservationTest/src/screenshots/" + name + "_" + timestamp + ".png");
            dest.getParentFile().mkdirs();
            java.nio.file.Files.copy(src.toPath(), dest.toPath());
        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }

    private static void highlightElement(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='3px solid red'", element);
            Thread.sleep(500);
            js.executeScript("arguments[0].style.border=''", element);
        } catch (Exception e) {
            // ignore
        }
    }
}