package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static ru.yandex.praktikum.constants.Colors.BLACK_PEARL;
import static ru.yandex.praktikum.constants.Colors.GREY_HOPELESSNESS;

public class ScooterOrderPage {

    WebDriver driver;
    private final By rentHeader = By.className("Order_Header__BZXOb");
    private final By date = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriod = By.xpath(".//span[@class='Dropdown-arrow']");
    private final By colourScooterBlack = By.id("black");
    private final By colourScooterGrey = By.id("grey");
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By createOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");


    public ScooterOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public ScooterOrderPage waitAboutRentHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(rentHeader).getText() != null
                && !driver.findElement(rentHeader).getText().isEmpty()
        ));
        return this;
    }

    public ScooterOrderPage inputDate(String newDate) {
        driver.findElement(date).sendKeys(newDate);
        return this;
    }

    public ScooterOrderPage inputRentalPeriod(String newRentalPeriod) {
        driver.findElement(rentalPeriod).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-menu"))).click();
        return this;
    }

    public ScooterOrderPage changeColour(Enum colour) {
        if (colour.equals(BLACK_PEARL)) {
            driver.findElement(colourScooterBlack).click();
        } else if (colour.equals(GREY_HOPELESSNESS)) {
            driver.findElement(colourScooterGrey).click();
        }
        return this;
    }

    public ScooterOrderPage inputComment(String newComment) {
        driver.findElement(comment).sendKeys(newComment);
        return this;
    }

    public void clickCreateOrderButton() {
        driver.findElement(createOrderButton).click();
    }

}
