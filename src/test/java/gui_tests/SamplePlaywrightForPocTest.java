package gui_tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.LoginPagePomPlaywright;
import pages.ProductPagePlaywright;

import static com.codeborne.selenide.Selenide.open;

public class SamplePlaywrightForPocTest {
    private static long startTime;
    @BeforeAll
    public static void setup() {
        startTime = System.currentTimeMillis();
    }

    @AfterAll
    public static void tearDown() {
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time of tests " + totalTime);;
    }
    @Test
    public void addSomeRandomItemToCart() {
        Playwright playwright = Playwright.create();
        BrowserType chromium = playwright.chromium();
        Browser browser = chromium.launch();
        Page page = browser.newPage();

        LoginPagePomPlaywright loginPagePomPlaywright = new LoginPagePomPlaywright(page);
        ProductPagePlaywright productPagePlaywright = new ProductPagePlaywright(page);

        page.navigate("https://www.saucedemo.com/");
        loginPagePomPlaywright.login();
        productPagePlaywright.selectProduct("red t-shirt");
        productPagePlaywright.selectItemFromBurgerMenu("all items");
        productPagePlaywright.goToCart();
        productPagePlaywright.validatePrice("$15.99");
        browser.close();
    }
}
