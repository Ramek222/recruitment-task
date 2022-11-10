package pages.shop;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Page {

    WebDriver driver;
    WebDriverWait wait;

    JavascriptExecutor executor;

    public Page(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.executor = (JavascriptExecutor) driver;

    }
}
