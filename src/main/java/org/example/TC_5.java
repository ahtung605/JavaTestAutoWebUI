package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class TC_5
{
    public static void main( String[] args ) throws InterruptedException {
        // подготовка
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        // выполнение  ТК
        System.out.println("TK-5. Удаление товара из корзины");
        driver.get("https://www.saucedemo.com/");

        // авторизация
        driver.findElement((By.id("user-name"))).sendKeys("standard_user");
        driver.findElement((By.id("password"))).sendKeys("secret_sauce");
        driver.findElement((By.id("login-button"))).submit();

        // поиск товара и нажатие кнопки добавления товара
        driver.findElement(By.xpath(".//a[@id='item_0_title_link']/following::button[@class='btn btn_primary btn_small btn_inventory']")).click();

        // проверка изменения названия кнопки
        String textButton = driver.findElement(By.xpath(".//a[@id='item_0_title_link']/following::button[@class='btn btn_secondary btn_small btn_inventory']")).getText();
        System.out.println((textButton.equals("REMOVE")) ? "ОК, название кнопки изменилось." : "Проблема с кнопкой!");

        // проверка на добавление товара в корзину (+1)
        String count = driver.findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText();
        System.out.println((Integer.parseInt(count) > 0) ? "Товар добавлен." : "Что-то пошло не так, товар не добавлен.");

        // пауза для визуализации процесса
        Thread.sleep(1000); // УБРАТЬ В "БОЕВОМ" РЕЖИМЕ

        // открываем корзину
        driver.findElement(By.xpath(".//a[@class='shopping_cart_link']")).click();
        // пауза для визуализации процесса
        Thread.sleep(2000); // УБРАТЬ В "БОЕВОМ" РЕЖИМЕ

        // убираем товар из корзины
        driver.findElement(By.xpath(".//button[@class='btn btn_secondary btn_small cart_button']")).click();
        // проверка на удаление товара из корзины (-1)
        System.out.println((driver.findElements(By.xpath(".//span[@class='shopping_cart_badge']")).isEmpty()) ? "Товар удален. Корзина пуста." : "Что-то пошло не так, товар не удален.");

        // пауза и закрытие браузера
        Thread.sleep(2000); // УБРАТЬ В "БОЕВОМ" РЕЖИМЕ
        driver.quit();

        System.out.println( "Успешное выполнение TK-5." );
    }
}
