package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPagePom {

    public void login(){
        $(By.id("user-name")).sendKeys("standard_user");
        $(By.id("password")).sendKeys("secret_sauce");
        $(By.id("login-button")).click();
    }

}
