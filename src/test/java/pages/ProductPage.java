package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.*;

public class ProductPage {

    public void selectProduct(String productName) {
        if ("red t-shirt".equals(productName)) {
            $(By.name("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        }
    }

    public void selectItemFromBurgerMenu(String item) {
        $(By.id("react-burger-menu-btn")).click();
        if ("all items".equals(item)) {
            $(By.id("inventory_sidebar_link")).click();
        }
    }

    public void goToCart() {
        $(By.id("shopping_cart_container")).click();
    }

    public void validatePrice(String price) {
        String someValue = $(By.className("inventory_item_price")).text();
        assertThat(price).isEqualTo(someValue);
    }
}
