package demo;

import java.sql.Date;
import java.time.LocalDateTime;
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

    public void TestCase02() throws InterruptedException
    {
        System.out.println("Start Test case: testCase02");
        driver.get("https://in.bookmyshow.com/explore/home/chennai");
        List<WebElement> links = driver.findElements(By.tagName("a"));
        wait.until(ExpectedConditions.visibilityOfAllElements(links));
        Thread.sleep(5000);
        links = driver.findElements(By.tagName("a"));
        System.out.println(links.size());
        System.out.println("end Test case: testCase02");
    }

    public void TestCase03() throws InterruptedException
    {
        System.out.println("Start Test case: testCase03");
        driver.get("https://in.linkedin.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("session_key")));
        WebElement emailtxt = driver.findElement(By.id("session_key"));
        emailtxt.sendKeys("murugappanlakshnanan@gmail.com");
        WebElement pasdtxt = driver.findElement(By.id("session_password"));
        pasdtxt.sendKeys("Murugu*1412");
        WebElement signinbtn = driver.findElement(By.xpath("//*[@data-id = 'sign-in-form__submit-btn']"));
        signinbtn.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@class,'share-box-feed-entry__trigger')]")));
        WebElement createpostbutton = driver.findElement(By.xpath("//button[contains(@class,'share-box-feed-entry__trigger')]"));
        createpostbutton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-placeholder='What do you want to talk about?']")));
        WebElement posttextarea = driver.findElement(By.xpath("//*[@aria-placeholder='What do you want to talk about?']"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@aria-placeholder='What do you want to talk about?']")));
        Thread.sleep(2000);
        posttextarea.click();
        LocalDateTime dateTime = LocalDateTime.of(2023, 10, 8, 12, 0, 5);
        long secondsToAdd = 38; // Number of seconds to add
        int newDateTime = dateTime.getSecond();
        posttextarea.sendKeys("Test12" + dateTime.getSecond() + dateTime.getNano());
        Thread.sleep(2000);
        WebElement userdropdown = driver.findElement(By.xpath("//*[@id='share-to-linkedin-modal__header']/button"));
        userdropdown.click();
        WebElement connectionsonly = driver.findElement(By.xpath("//*[contains(text(),'Connections only')]"));
        connectionsonly.click();
        WebElement donebutton = driver.findElement(By.xpath("//*[contains(@class,'share-box-footer__primary-btn')]"));
        donebutton.click();
        WebElement postbutton = driver.findElement(By.xpath("//*[contains(@class,'share-actions__primary-action')]"));
        wait.until(ExpectedConditions.elementToBeClickable(postbutton));
        postbutton.click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[contains(@class, 'update-components-actor__title')]/span/span/span")));
        List<WebElement> postername = driver.findElements(By.xpath("//*[contains(@class, 'update-components-actor__title')]/span/span/span"));
        Assert.assertTrue(Isposternamepresent(postername, "lotus thamarai"));
        System.out.println("end Test case: testCase03"); 
    }

    public void TestCase04()
    {
        System.out.println("Start Test case: testCase04");
        driver.get("https://in.bookmyshow.com/explore/home/chennai");
        //List<WebElement> image = driver.findElements(By.xpath("//*[contains(text(),'Movies in Chennai')]/../following-sibling::div[4]/descendant::img"));
        List<WebElement> imageurl = driver.findElements(By.xpath("(//*[contains(text(),'Recommended Movies')]/../..)[2]/../following-sibling::div/descendant::img"));
        wait.until(ExpectedConditions.visibilityOfAllElements(imageurl.get(1)));
        WebElement secondElement = driver.findElement(By.xpath("((//*[contains(text(),'Recommended Movies')]/../..)[2]/../following-sibling::div/descendant::img)[2]/../../following-sibling::div/div/div"));
        for (WebElement img : imageurl) 
        {
           if(img.getAttribute("src") != null)
           {
               System.out.println(img.getAttribute("src").toString());
           }    
        }

        System.out.println("nameofmovie"+ secondElement.getText());
    }

    public boolean Isposternamepresent(List<WebElement> s,String name)
    {
       for (WebElement webElement : s) 
       {
          if(webElement.getText().contains(name))
          {
              return true;
          }    
       }

       return false;
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
