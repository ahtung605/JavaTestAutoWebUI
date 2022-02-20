package org.example.homework_6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * @author Konstantin Babenko
 * @create 20.02.2022
 */

public class LoginTest extends AbstractTest {

    @Test
    @DisplayName("TK-1. Авторизация на сайте магазина")
    void authorisationTest() {
        logger.info("TK-1. Авторизация на сайте магазина");
        new LoginPage(getDriver()).setLogin("standard_user").setPassword("secret_sauce").loginIn();
        logger.info("TK-1. Проверка авторизации");
        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"), "Авторизация не прошла!");
    }

//    // второй вариант реализации (оставляю для себя)
//    @Test
//    @DisplayName("TK-1. Авторизация на сайте магазина")
//    void authorisation2Test() {
//        logger.info("TK-1. Авторизация на сайте магазина (2)");
//        new LoginPage(getDriver()).loginIn("standard_user","secret_sauce");
//        logger.info("TK-1. Проверка авторизации (2)");
//        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"), "Авторизация не прошла!");
//    }

    @AfterEach
    void logout() throws InterruptedException {
        logger.info("Разлогинивание");
        new StartPage(getDriver()).logOut();
        logger.info("Проверка разлогинивания");
        new WebDriverWait(getDriver(), Duration.ofSeconds(5));
//        Thread.sleep(2000); // УБРАТЬ В "БОЕВОМ" РЕЖИМЕ
        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/"), "Авторизация не прошла!");
    }

}
