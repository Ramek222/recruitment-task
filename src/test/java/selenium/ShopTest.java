package selenium;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.shop.CartPage;
import pages.shop.CheckoutPage;
import pages.shop.HomePage;
import pages.shop.ProductsPage;
import utils.TextUtils;
import java.util.List;

public class ShopTest extends BaseTest {

    @Test(testName = "dodanie produktu do koszyka")
    public void addProductToCart() {

        //when
        HomePage homePage = new HomePage(driver);
        homePage.chooseCategory("Jeans");
        ProductsPage productsPage = new ProductsPage(driver);
        //given
        productsPage.addAllProductToCart();
        List<String> expectedAddedProduct = TextUtils.removeSpecialCharacters(productsPage.getProductsName());
        productsPage.goToMyCart();
        CartPage cartPage = new CartPage(driver);
        List<String> actualAddedProductsName = TextUtils.removeSpecialCharacters(cartPage.getAddedProductsName());
        //then
        Assert.assertEquals(actualAddedProductsName, expectedAddedProduct);

    }


    @Test(testName = "Usunięcie pierwszego produktu z koszyka")
    public void removeProductFromCart() {

        /*button do usuwania produktów z koszyka nie działa na stronie, dlatego test nie przechodzi asercji */

        //when
        HomePage homePage = new HomePage(driver);
        homePage.chooseCategory("Jeans");
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addAllProductToCart();
        productsPage.goToMyCart();
        CartPage cartPage = new CartPage(driver);
        List<String> beforeRemoveProductsName = TextUtils.removeSpecialCharacters(cartPage.getAddedProductsName());
        //given
        cartPage.removeProduct(cartPage.getAddedProductsName().get(0));
        List<String> afterRemoveProductsName = TextUtils.removeSpecialCharacters(cartPage.getAddedProductsName());
        //then
        Assert.assertNotEquals(afterRemoveProductsName, beforeRemoveProductsName);

    }


    @Test(testName = "Zaktualizowanie koszyka poprzez zmiane ilości pierwszego z produktów")
    public void changeQuantityProductInCart() {

        //when
        HomePage homePage = new HomePage(driver);
        homePage.chooseCategory("Jeans");
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addAllProductToCart();
        productsPage.goToMyCart();
        CartPage cartPage = new CartPage(driver);
        List<String> addedProductsName = cartPage.getAddedProductsName();
        //given
        cartPage.changeQuantity(addedProductsName.get(0), 100);
        cartPage.updateCart();
        //then
        String updateMessage = cartPage.getUpdateMessage();
        Assert.assertEquals(updateMessage, "Cart updated.");
        Assert.assertEquals(cartPage.sumProductsTotalPrice(),cartPage.getSubTotalPrice());




    }

    @Test(testName = "Dodaj produkty i przejdź do formularza wysyłki")
    public void addProductToCartAndGoToCheckout() {

        //when
        HomePage homePage = new HomePage(driver);
        homePage.chooseCategory("Jeans");
        ProductsPage productsPage = new ProductsPage(driver);
        //given
        productsPage.addAllProductToCart();
        List<String> expectedAddedProduct = TextUtils.removeSpecialCharacters(productsPage.getProductsName());
        productsPage.goToMyCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();

        Assert.assertEquals(driver.getCurrentUrl(),"http://skleptest.pl/checkout/");


    }
}
