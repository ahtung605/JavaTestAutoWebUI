package org.example.homework_7;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Konstantin Babenko
 * @create 22.02.2022
 */

public class StartPage extends AbstractPage {

    @FindBy(xpath = ".//button[@id='react-burger-menu-btn']")
    private WebElement menu;

    @FindBy(xpath = ".//a[@id='logout_sidebar_link']")
    private WebElement exit;

    public StartPage(WebDriver driver) {
        super(driver);
    }

    public void logOut() {
        this.menu.click();
        this.exit.click();
    }
}
