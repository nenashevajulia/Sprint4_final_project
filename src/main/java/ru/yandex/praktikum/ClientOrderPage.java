package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClientOrderPage {
    WebDriver driver;
    private final By orderHeader = By.className("Order_Header__BZXOb");
    private final By firstName = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastName = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By buttonNext = By.xpath(".//div[starts-with(@class,'Order_NextButton')]//button[contains(text(), 'Далее')]");


    public ClientOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public ClientOrderPage waitForLoadOrderPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(orderHeader).getText() != null
                && !driver.findElement(orderHeader).getText().isEmpty()
        ));
        return this;
    }

    public ClientOrderPage inputFirstName(String newFirstName) {
        driver.findElement(firstName).sendKeys(newFirstName);
        return this;
    }

    public ClientOrderPage inputLastName(String newLastName) {
        driver.findElement(lastName).sendKeys(newLastName);
        return this;
    }

    public ClientOrderPage inputAddress(String newAddress) {
        driver.findElement(address).sendKeys(newAddress);
        return this;
    }

    public ClientOrderPage chooseMetroStation(int metroStationNumber) {
        driver.findElement(metroStation).click();
        String titleMetroStation = ".//button[@value='%s']";
        By newMetroStation = By.xpath(String.format(titleMetroStation, metroStationNumber));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(newMetroStation));
        driver.findElement(newMetroStation).click();
        return this;
    }

    public ClientOrderPage inputPhoneNumber(String newPhoneNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(phoneNumber));
        driver.findElement(phoneNumber).sendKeys(newPhoneNumber);
        return this;
    }

    public void clickButtonNext() {
        driver.findElement(buttonNext).click();
    }
}
