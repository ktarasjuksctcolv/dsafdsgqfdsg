package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductPagePlaywright {

    private final Page page;
    private final Locator redtshirt;
    private final Locator burgerMenuButton;

    private final Locator allItemsInMenu;
    private final Locator goToCartButton;


    public ProductPagePlaywright(Page page) {
        this.page = page;
        this.redtshirt = page.locator("//button[@id='add-to-cart-test.allthethings()-t-shirt-(red)']");
        this.burgerMenuButton = page.locator("#react-burger-menu-btn");
        this.allItemsInMenu = page.locator("#inventory_sidebar_link");
        this.goToCartButton = page.locator("#shopping_cart_container");
    }

    public void selectProduct(String productName) {
        if ("red t-shirt".equals(productName)) {
            redtshirt.click();
        }
    }

    public void selectItemFromBurgerMenu(String item) {
        burgerMenuButton.click();
        if ("all items".equals(item)) {
            allItemsInMenu.click();
        }
    }

    public void goToCart() {
        goToCartButton.click();
    }

    public void validatePrice(String price) {
        String someValue = page.locator(".inventory_item_price").innerText();
        assertThat(price).isEqualTo(someValue);
    }
}
