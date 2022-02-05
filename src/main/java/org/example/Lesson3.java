package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class Lesson3
{
    public static void main( String[] args ) throws InterruptedException {
        WebDriverManager.chromedriver().setup(); // установка актуального драйвера для браузера

        ChromeOptions options = new ChromeOptions(); // установка опций для запуска Chrome
        options.addArguments("start-maximized"); // открытие полноэкранного режима
        options.addArguments("--incognito"); // открытие страницы в режиме инкогнито
        options.addArguments("disable-popup-blocking"); // блокировка всплывающих окон

        WebDriver driver = new ChromeDriver(options); // передача опций в браузер
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); // неявное ожидание не более 3 секунд

        driver.get("https://www.bbc.com/"); // стартовая страница браузера

        WebElement webElement = driver.findElement(By.xpath("//*[@id=\"orb-nav-links\"]/ul/li[3]/a"));
        webElement.click();
        driver.findElement(By.xpath("//*[@id=\"u4674795905908347\"]/div/nav/div[1]/div/ul/li[10]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"sp-nav-secondary\"]/li[4]/a")).click();

        driver.findElement(By.id("orb-search-q")).sendKeys("ROC");
        driver.findElement(By.id("orb-search-q")).submit();

//        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(webElement)); // явное ожиданиие не более 5 секунд
//        WebDriverWait webDriverWait = new WebDriverWait(driver, 5); // явное ожиданиие не более 5 секунд
        Thread.sleep(1000); // принудительное ожидание 1000 милисекунд
        driver.quit();
        System.out.println( "Успешное окончание выполнения программы урока!!!" );
    }
}
