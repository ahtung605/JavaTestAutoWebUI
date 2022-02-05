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
public class TC_2
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
        System.out.println("TK-2. Добавление товаров в корзину кликом на заголовок товара");
        driver.get("https://www.saucedemo.com/");

        // авторизация
        driver.findElement((By.id("user-name"))).sendKeys("standard_user");
        driver.findElement((By.id("password"))).sendKeys("secret_sauce");
        driver.findElement((By.id("login-button"))).submit();

        // поиск заголовка товара и переход на страницу товара
        driver.findElement(By.xpath(".//a[@id='item_0_title_link']/div[@class='inventory_item_name']")).click();
        // поиск и нажатие кнопки добавления товара
        driver.findElement(By.xpath(".//button[@class='btn btn_primary btn_small btn_inventory']")).click();

        // проверка изменения названия кнопки
        String textButton = driver.findElement(By.xpath(".//button[@class='btn btn_secondary btn_small btn_inventory']")).getText();
        System.out.println((textButton.equals("REMOVE")) ? "ОК, название кнопки изменилось." : "Проблема с кнопкой!");

        // проверка на добавление товара в корзину (+1)
        String count = driver.findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText();
        System.out.println((Integer.parseInt(count) > 0) ? "Товар добавлен." : "Что-то пошло не так, товар не добавлен.");

        // пауза и закрытие браузера
        Thread.sleep(2000); // УБРАТЬ В "БОЕВОМ" РЕЖИМЕ
        driver.quit();

        System.out.println("Товаров в корзине - " + count);
        System.out.println( "Успешное выполнение TK-2." );
    }
}
