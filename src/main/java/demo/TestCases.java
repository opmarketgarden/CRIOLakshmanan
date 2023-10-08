package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.github.dockerjava.api.model.Links;

import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.checkerframework.checker.units.qual.s;
import org.junit.Assert;
import org.openqa.selenium.By;
//Selenium Imports
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
///
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCases {
    ChromeDriver driver;
    WebDriverWait wait;

    public TestCases() {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.quit();
    }

    public void testCase01() throws InterruptedException {

        System.out.println("Start Test case: testCase01");
        driver.get("https://www.google.com");

        WebElement searchbox = driver.findElement(By.name("q"));
        wait.until(ExpectedConditions.visibilityOf(searchbox));
        searchbox.sendKeys("amazon");
        searchbox.sendKeys(Keys.ENTER);
        List<WebElement> links = driver.findElements(By.tagName("a"));
        Thread.sleep(3000);
        // wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("a"),
        // 100));
        links = driver.findElements(By.tagName("a"));
        Assert.assertTrue(IsLinkPresent(links));
        System.out.println("end Test case: testCase01");
    }

    public boolean IsLinkPresent(List<WebElement> f) {
        for (WebElement e : f) {
            String link = e.getAttribute("href");
            if (link != null) {
                System.out.println(f.size());
                if (link.contains("amazon.in") || link.contains("amazon.com")) {
                    return true;
                }
            }

        }
        return false;
    }

}
