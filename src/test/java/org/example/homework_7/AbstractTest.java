package org.example.homework_7;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.example.homework_6.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * @author Konstantin Babenko
 * @create 22.02.2022
 */

public abstract class AbstractTest {

    //    private static WebDriver driver;
    private static EventFiringWebDriver eventDriver;
    static Logger logger = LoggerFactory.getLogger(org.example.homework_7.AbstractTest.class);

    @BeforeAll
    // вызов перед ВСЕМИ тестами
    static void init() {
        // подготовка драйвера
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");
        options.setPageLoadTimeout(Duration.ofSeconds(10));
//        driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        eventDriver = new EventFiringWebDriver(new ChromeDriver(options));
        eventDriver.register(new MyWebDriverEventListener());
        eventDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeEach
        // вызов при КАЖДОМ тесте
    void getStartPage() {
        Assertions.assertDoesNotThrow(() -> eventDriver.get("https://www.saucedemo.com/"), "Стартовая страница не доступна!!!");
        logger.info("Переход на стартовую страницу");
        Assertions.assertTrue(true);
    }

    @AfterAll
    // вызов после ВСЕХ тестов
    static void closeAll() throws InterruptedException {
        // закрытие браузера
        logger.info("Закрытие браузера. Все ТК выполнены.");
        if (eventDriver != null) eventDriver.quit();
//        System.out.println( "Все ТК выполнены." );
    }

    @Step("Авторизация")
    void authorisation() {
        logger.info("Авторизация на сайте магазина");
        new LoginPage(getDriver()).loginIn("standard_user", "secret_sauce");
        logger.info("Проверка авторизации");
        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"), "Авторизация не прошла!");
    }

    public WebDriver getDriver() {
        return this.eventDriver;
    }

}
