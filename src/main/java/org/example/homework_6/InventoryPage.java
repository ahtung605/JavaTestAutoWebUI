package org.example.homework_6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Konstantin Babenko
 * @create 20.02.2022
 */

public class InventoryPage extends AbstractPage {

    @FindBy(xpath = ".//a[@id='item_0_title_link']/div[@class='inventory_item_name']")
    private WebElement goodPage;

    @FindBy(xpath = ".//button[@class='btn btn_primary btn_small btn_inventory']")
    private WebElement addGoodButton;

    @FindBy(xpath = ".//div[@class='inventory_item_img']/a[@id='item_1_img_link']")
    private WebElement goodImage;

    @FindBy(xpath = ".//a[@id='item_0_title_link']/following::button[@class='btn btn_primary btn_small btn_inventory']")
    private WebElement addGoodButtonOnPage;

    @FindBy(xpath = ".//a[@class='shopping_cart_link']")
    private WebElement cart;

    @FindBy(xpath = ".//button[@class='btn btn_secondary btn_small cart_button']")
    private WebElement delGoodButton;

    @FindBy(xpath = ".//button[@class='btn btn_secondary btn_small btn_inventory']")
    private WebElement delGoodButtonOnPage;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void goodPageClick() {
        this.goodPage.click();
    }

    public void addGoodButton() {
        this.addGoodButton.click();
    }

    public void goodImageClick() {
        this.goodImage.click();
    }

    public void addGoodButtonOnPage() {
        this.addGoodButtonOnPage.click();
    }

    public void cart() {
        this.cart.click();
    }

    public void delGoodButton() {
        this.delGoodButton.click();
    }

    public void delGoodButtonOnPage() {
        this.delGoodButtonOnPage.click();
    }

}
