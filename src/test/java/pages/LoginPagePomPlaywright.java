package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPagePomPlaywright {
    private final Page page;
    private final Locator username;
    private final Locator password;

    private final Locator loginButton;


    public LoginPagePomPlaywright(Page page) {
        this.page = page;
        this.username = page.locator("#user-name");
        this.password = page.locator("#password");
        this.loginButton = page.locator("#login-button");
    }
    public void login(){
        username.fill("standard_user");
        password.fill("secret_sauce");
        loginButton.click();
    }
}
