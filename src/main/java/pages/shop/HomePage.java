package pages.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Page {
    public static final String categoryItemXpath = "//*[@title='%s']";
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }
    public void chooseCategory(String categoryName) {
        WebElement category = driver.findElement(By.xpath(String.format(categoryItemXpath,categoryName)));
        this.executor.executeScript("arguments[0].click();",category);

    }

}
