package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ModalBox {
    WebDriver driver;
    private final By buttonYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    private final By orderNumberHeader = By.xpath(".//div[text()='Заказ оформлен']");

    public ModalBox(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonYes() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(buttonYes)).click();
    }

    public String getOrderNumberHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(orderNumberHeader).getText() != null
                && !driver.findElement(orderNumberHeader).getText().isEmpty()
        ));
        return driver.findElement(orderNumberHeader).getText();
    }
}
