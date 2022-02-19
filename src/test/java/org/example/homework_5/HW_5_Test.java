package org.example.homework_5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

/**
 * @author Konstantin Babenko
 * @create 19.02.2022
 */

public class HW_5_Test extends AbstractTest {

    @Test
    @DisplayName("TK-1. Авторизация на сайте магазина")
    void authorisationTest() {
        logger.info("TK-1. Авторизация на сайте магазина");
        getDriver().findElement((By.id("user-name"))).sendKeys("standard_user");
        getDriver().findElement((By.id("password"))).sendKeys("secret_sauce");
        getDriver().findElement((By.id("login-button"))).submit();

        logger.info("TK-1. Проверка авторизации");
        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"), "Авторизация не прошла!");
    }

    @Test
    @DisplayName("TK-2. Добавление товаров в корзину кликом на заголовок товара")
    void addGoodClickTitleTest() throws InterruptedException {
        logger.info("TK-2. Авторизация на сайте магазина");
        authorisation();

        logger.info("TK-2. Проверка авторизации");
        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"), "Авторизация не прошла!");

        logger.info("TK-2. Добавление товаров в корзину кликом на заголовок товара");
//        ----------Так и не смог побороть Actions в этом ТК-----------
//        Actions findTitleGoodAdd = new Actions(getDriver());
//        findTitleGoodAdd.click(getDriver().findElement(By.xpath(".//a[@id='item_0_title_link']/div[@class='inventory_item_name']")))
//                .pause(10000)
//                .click(getDriver().findElement(By.xpath(".//button[@class='btn btn_primary btn_small btn_inventory']")))
//                .build()
//                .perform();
        // поиск заголовка товара и переход на страницу товара
        getDriver().findElement(By.xpath(".//a[@id='item_0_title_link']/div[@class='inventory_item_name']")).click();
        // поиск и нажатие кнопки добавления товара
        getDriver().findElement(By.xpath(".//button[@class='btn btn_primary btn_small btn_inventory']")).click();

        // проверка изменения названия кнопки
        logger.info("TK-2. Проверка изменения названия кнопки");
        Assertions.assertTrue(getDriver().findElement(By.xpath(".//button[@class='btn btn_secondary btn_small btn_inventory']")).getText().equals("REMOVE"), "Что-то пошло не так, товар не добавлен.");

        // проверка на добавление товара в корзину (+1)
        logger.info("TK-2. Проверка на добавление товара в корзину (+1)");
        Assertions.assertTrue(getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText().equals("1"), "Что-то пошло не так, товар не добавлен.");
    }

    @Test
    @DisplayName("TK-3. Добавление товаров в корзину кликом на картинку товара")
    void addGoodClickImageTest() throws InterruptedException {
        logger.info("TK-3. Авторизация на сайте магазина");
        authorisation();

        logger.info("TK-3. Проверка авторизации");
        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"), "Авторизация не прошла!");

        logger.info("TK-3. Добавление товаров в корзину кликом на картинку товара");
        // поиск картинки товара и переход на страницу товара
        getDriver().findElement(By.xpath(".//div[@class='inventory_item_img']/a[@id='item_1_img_link']")).click();
        // поиск и нажатие кнопки добавления товара
        getDriver().findElement(By.xpath(".//button[@class='btn btn_primary btn_small btn_inventory']")).click();

        // проверка изменения названия кнопки
        logger.info("TK-3. Проверка изменения названия кнопки");
        Assertions.assertTrue(getDriver().findElement(By.xpath(".//button[@class='btn btn_secondary btn_small btn_inventory']")).getText().equals("REMOVE"), "Что-то пошло не так, товар не добавлен.");

        // проверка на добавление товара в корзину (+1)
        logger.info("TK-3. Проверка на добавление товара в корзину (+1)");
        Assertions.assertFalse(getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText().equals(""), "Что-то пошло не так, товар не добавлен.");
    }

    @Test
    @DisplayName("TK-4. Добавление товаров в корзину без перехода на страницу товара")
    void addGoodClickButtonTest() throws InterruptedException {
        logger.info("TK-4. Авторизация на сайте магазина");
        authorisation();

        logger.info("TK-4. Проверка авторизации");
        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"), "Авторизация не прошла!");

        logger.info("TK-4. Добавление товаров в корзину без перехода на страницу товара");
        // поиск товара и нажатие кнопки добавления товара
        getDriver().findElement(By.xpath(".//a[@id='item_0_title_link']/following::button[@class='btn btn_primary btn_small btn_inventory']")).click();

        // проверка изменения названия кнопки
        logger.info("TK-4. Проверка изменения названия кнопки");
        Assertions.assertTrue(getDriver().findElement(By.xpath(".//a[@id='item_0_title_link']/following::button[@class='btn btn_secondary btn_small btn_inventory']")).getText().equals("REMOVE"), "Что-то пошло не так, товар не добавлен.");

        // проверка на добавление товара в корзину (+1)
        logger.info("TK-4. Проверка на добавление товара в корзину (+1)");
        Assertions.assertFalse(getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText().equals(""), "Что-то пошло не так, товар не добавлен.");
    }

    @Test
    @DisplayName("TK-5. Удаление товара из корзины")
    void removeGoodTest() throws InterruptedException {
        logger.info("TK-5. Авторизация на сайте магазина");
        authorisation();

        logger.info("TK-5. Проверка авторизации");
        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"), "Авторизация не прошла!");

        logger.info("TK-5. Добавление товаров в корзину");
        // поиск товара и нажатие кнопки добавления товара
        getDriver().findElement(By.xpath(".//a[@id='item_0_title_link']/following::button[@class='btn btn_primary btn_small btn_inventory']")).click();
        // запоминаем счетчик товаров - при выполнении всех тестов пакетом корзина не обнуляется!!!!!
        String count = getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText();

        // проверка изменения названия кнопки
        logger.info("TK-5. Проверка изменения названия кнопки");
        Assertions.assertTrue(getDriver().findElement(By.xpath(".//a[@id='item_0_title_link']/following::button[@class='btn btn_secondary btn_small btn_inventory']")).getText().equals("REMOVE"), "Что-то пошло не так, товар не добавлен.");

        // проверка на добавление товара в корзину (+1)
        logger.info("TK-5. Проверка на добавление товара в корзину (+1)");
        Assertions.assertFalse(getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText().equals(""), "Что-то пошло не так, товар не добавлен.");

        // открываем корзину
        getDriver().findElement(By.xpath(".//a[@class='shopping_cart_link']")).click();

        logger.info("TK-5. Проверка открытия корзины");
        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/cart.html"), "Корзина не открылась!");

        // убираем товар из корзины
        logger.info("TK-5. Убираем товар из корзины");
        getDriver().findElement(By.xpath(".//button[@class='btn btn_secondary btn_small cart_button']")).click();

        // проверка на удаление товара из корзины (-1)
        logger.info("TK-5. Проверка на удаление товара из корзины (-1)");
        if (Integer.parseInt(count) == 1){
            Assertions.assertTrue(getDriver().findElement(By.xpath(".//a[@class='shopping_cart_link']")).getText().equals(""), "Что-то пошло не так, товар не удален.");
        } else {
            Assertions.assertTrue(getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText().equals(String.valueOf(Integer.parseInt(count)-1)), "Что-то пошло не так, товар не удален.");
        }
    }

    @Test
    @DisplayName("TK-6. Удаление товара из корзины без перехода на страницу корзины")
    void removeGoodNoChangePageTest() throws InterruptedException {
        logger.info("TK-6. Авторизация на сайте магазина");
        authorisation();

        logger.info("TK-6. Проверка авторизации");
        Assertions.assertTrue(getDriver().getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"), "Авторизация не прошла!");

        logger.info("TK-6. Добавление товаров в корзину");
        // поиск товара и нажатие кнопки добавления товара
        getDriver().findElement(By.xpath(".//a[@id='item_0_title_link']/following::button[@class='btn btn_primary btn_small btn_inventory']")).click();
        // запоминаем счетчик товаров - при выполнении всех тестов пакетом корзина не обнуляется!!!!!
        String count = getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText();

        // проверка изменения названия кнопки
        logger.info("TK-6. Проверка изменения названия кнопки");
        Assertions.assertTrue(getDriver().findElement(By.xpath(".//a[@id='item_0_title_link']/following::button[@class='btn btn_secondary btn_small btn_inventory']")).getText().equals("REMOVE"), "Что-то пошло не так, товар не добавлен.");

        // проверка на добавление товара в корзину (+1)
        logger.info("TK-6. Проверка на добавление товара в корзину (+1)");
        Assertions.assertFalse(getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText().equals(""), "Что-то пошло не так, товар не добавлен.");

        // убираем товар из корзины
        logger.info("TK-6. Убираем товар из корзины");
        getDriver().findElement(By.xpath(".//button[@class='btn btn_secondary btn_small btn_inventory']")).click();

        // проверка на удаление товара из корзины (-1)
        logger.info("TK-6. Проверка на удаление товара из корзины (-1)");
        if (Integer.parseInt(count) == 1){
            Assertions.assertTrue(getDriver().findElement(By.xpath(".//a[@class='shopping_cart_link']")).getText().equals(""), "Что-то пошло не так, товар не удален.");
        } else {
            Assertions.assertTrue(getDriver().findElement(By.xpath(".//span[@class='shopping_cart_badge']")).getText().equals(String.valueOf(Integer.parseInt(count)-1)), "Что-то пошло не так, товар не удален.");
        }
    }
}
