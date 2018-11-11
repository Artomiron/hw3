import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.EventHendler;

import java.util.concurrent.TimeUnit;


public class homework3 {
    public static void main(String[] args) {
        WebDriver driver = getConfiguredDriver();
        driver.manage().window().fullscreen();

        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/");


//        WebElement block = driver.findElement(By.cssSelector(".caption-description"));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        String innerHTML = (String)js.executeScript("return arguments[0].innerHTML;", block);
//        System.out.println(innerHTML);


        WebElement input = driver.findElement(By.cssSelector("input[name=s]"));
        input.sendKeys("Dress");
        input.submit();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("products")));

        WebElement product = driver.findElement(By.cssSelector(".js-product-miniature"));
        WebElement quickView = driver.findElement(By.cssSelector(".quick-view"));

        Actions actions = new Actions(driver);
        actions.moveToElement(product).pause(5).click(quickView).build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".quickview")));

        driver.quit();
    }

    public static WebDriver getDriver(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//resources/chromedriver.exe");
        return new ChromeDriver();
    }

    public static EventFiringWebDriver getConfiguredDriver(){
        EventFiringWebDriver driver = new EventFiringWebDriver(getDriver());
        driver.register(new EventHendler());

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}

