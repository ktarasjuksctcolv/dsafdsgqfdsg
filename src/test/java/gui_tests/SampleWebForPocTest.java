package gui_tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.LoginPagePom;
import pages.ProductPage;

import static com.codeborne.selenide.Selenide.open;

public class SampleWebForPocTest {

    private final LoginPagePom loginPagePom = new LoginPagePom();
    private final ProductPage productPage = new ProductPage();
    private static long startTime;
    @BeforeAll
    public static void setup() {
        startTime = System.currentTimeMillis();
        Configuration.headless = true;
    }

    @AfterAll
    public static void tearDown() {
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time of tests " + totalTime);;
    }
    @Test
    public void addSomeRandomItemToCart() {
        open("https://www.saucedemo.com/");
        loginPagePom.login();
        productPage.selectProduct("red t-shirt");
        productPage.selectItemFromBurgerMenu("all items");
        productPage.goToCart();
        productPage.validatePrice("$15.99");
    }
}