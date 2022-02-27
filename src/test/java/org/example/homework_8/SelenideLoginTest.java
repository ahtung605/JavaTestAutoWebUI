package org.example.homework_8;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * @author Konstantin Babenko
 * @create 27.02.2022
 */

public class SelenideLoginTest {

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
    }

    @Test
    void logintest() {
        open("https://www.saucedemo.com/");
        $(By.id("user-name")).sendKeys("standard_user");
        $(By.id("password")).sendKeys("secret_sauce");
        $(By.id("login-button")).submit();
        // проверка залогинирования
        $(By.xpath(".//a[@class='shopping_cart_link']")).shouldBe(visible);
    }

}
