package pages.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.TextUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CartPage extends Page {

    @FindBy(xpath = "//tbody//*[@class='product-name']/a")
    List<WebElement> addedProductsNamesToBay;

    @FindBy(xpath = "//*[@value='Update cart']")
    WebElement updateCartBtn;

    String removeProductBtn = "//tbody//tr[@class='woocommerce-cart-form__cart-item cart_item']" +
            "//td[@class='product-name']//a[contains(text(),'%s')]/ancestor::tr//td[@class='product-remove']/a";

    String quantityBtn = "//tbody//tr[@class='woocommerce-cart-form__cart-item cart_item']" +
            "//td[@class='product-name']//a[contains(text(),'%s')]/ancestor::tr//input[@title='Qty']";

    String price = "//tbody//tr[@class='woocommerce-cart-form__cart-item cart_item']" +
            "//td[@class='product-name']//a[contains(text(),'%s')]/ancestor::tr//input[@title='Qty']";

    @FindBy(xpath = "//*[@class='woocommerce-message']")
    WebElement updateMessage;

    @FindBy(xpath = "//tbody//td[@class='product-subtotal']//span[@class='woocommerce-Price-amount amount']")
    List<WebElement> totalPricesCartForm;

    @FindBy(xpath = "//*[@data-title='Subtotal']//span[@class='woocommerce-Price-amount amount']")
    WebElement subTotalPrice;

    @FindBy(xpath = "//*[@class='wc-proceed-to-checkout']/a")
    WebElement checkoutBtn;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void removeProduct(String productName) {
        driver.findElement(By.xpath(String.format(removeProductBtn, productName))).click();
    }

    public List<String> getAddedProductsName() {
        return addedProductsNamesToBay.stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
    }

    public void changeQuantity(String productName, int quantity) {
        WebElement element = driver.findElement(By.xpath(String.format(quantityBtn, productName)));
        element.clear();
        element.sendKeys(String.valueOf(quantity));
    }

    public void updateCart() {
        if (updateCartBtn.isEnabled()) {
            updateCartBtn.click();
        }
    }

    public String getUpdateMessage() {
        return wait.until(ExpectedConditions.visibilityOf(updateMessage)).getText();

    }

    public Integer sumProductsTotalPrice() {
        List<String> collect = totalPricesCartForm.stream()
                .map(el -> TextUtils.leftOnlyDigits(el.getText()))
                .collect(Collectors.toList());

        AtomicInteger sum = new AtomicInteger();
        collect.forEach(el -> {
            sum.addAndGet(Integer.valueOf(el));
        });

        return sum.get();
    }

    public Integer getSubTotalPrice() {
        return Integer.valueOf(TextUtils.leftOnlyDigits(subTotalPrice.getText()));

    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.visibilityOf(checkoutBtn)).click();
    }
}
