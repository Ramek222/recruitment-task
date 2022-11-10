package pages.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends Page {

    @FindBy(xpath = "//*[@class='products']//li")
    List<WebElement> allProducts;
    @FindBy(xpath = "//*[@class='products']//li//*[@class='fa fa-shopping-cart']")
    List<WebElement> allProductsAddToCard;
    @FindBy(xpath = "//*[@class='products']//li//a//h2")
    List<WebElement> allProductsName;
    @FindBy(className = "top-cart")
    WebElement myCartBtn;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }

    public void addAllProductToCart(){
        allProductsAddToCard.forEach(webElement ->{
            webElement.click();
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//parent::*//parent::*//*[@title='View cart']")));

        });
    }

    public List<String> getProductsName() {
        return allProductsName.stream()
                .map(el->el.getAttribute("textContent"))
                .map(text->text.replaceAll("[^a-zA-Z0-9]", " "))
                .collect(Collectors.toList());
    }

    public void goToMyCart() {
        myCartBtn.click();
    }
}
