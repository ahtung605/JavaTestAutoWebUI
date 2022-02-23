package org.example.homework_7;

import io.qameta.allure.*;
import org.example.homework_6.LoginPage;
import org.example.homework_6.StartPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

/**
 * @author Konstantin Babenko
 * @create 22.02.2022
 */

public class LoginTest extends AbstractTest {

    @Test
    @DisplayName("TK-1. ")
    @Description("Авторизация на сайте магазина")
    @Link("http://google.com")
    @Severity(SeverityLevel.BLOCKER)
    void authorisationTest() throws IOException {
        logger.info("TK-1. Авторизация на сайте магазина");
        new LoginPage(getDriver()).setLogin("standard_user").setPassword("secret_sauce").loginIn();
        logger.info("TK-1. Проверка авторизации");
        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"), "Авторизация не прошла!");
        // скрин результата проверки
        File file = MyUtils.makeScreenshot(getDriver(), "org.example.HW-7.InventoryPageTest.TC2_" + System.currentTimeMillis() + ".png");
        saveScreenshot(Files.readAllBytes(file.toPath()));
        MyUtils.getLog(getDriver());
    }

    @Step("Создание скриншота")
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    @AfterEach
    void logout() throws InterruptedException {
        logger.info("Разлогинивание");
        new StartPage(getDriver()).logOut();
        logger.info("Проверка разлогинивания");
        new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/"), "Авторизация не прошла!");
    }

}
