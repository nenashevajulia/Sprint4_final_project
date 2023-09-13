package ru.yandex.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.praktikum.constants.OrderButton.DOWN_ORDER_BUTTON;
import static ru.yandex.praktikum.constants.OrderButton.UP_ORDER_BUTTON;

public class MainPage {
    WebDriver driver;
    private final By homeHeader = By.className("Home_Header__iJKdX");
    private final By questionsAboutImportantHeader = By.className("Home_FourPart__1uthg");
    private final By upperOrderButton = By.className("Button_Button__ra12g");
    private final By downOrderButton = By.xpath(".//button[contains(@class,'Button_Button__ra12g Button_Middle__1CSJM') and text()='Заказать']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    public MainPage waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(homeHeader).getText() != null
                && !driver.findElement(homeHeader).getText().isEmpty()
        ));
        return this;
    }

    public void waitLoadAfterClickQuestion(By accordionLabel) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(accordionLabel).getText() != null
                && !driver.findElement(accordionLabel).getText().isEmpty()
        ));
    }


    public MainPage scrollToQuestions() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionsAboutImportantHeader));
        return this;
    }

    public MainPage clickQuestion(By question) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(question))
                .click();
        return this;
    }


    public void clickUpperOrderButton() {
        driver.findElement(upperOrderButton).click();
    }

    public void scrollToDownOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
    }

    public void clickDownOrderButton() {
        driver.findElement(downOrderButton).click();
    }

    public void clickOrderButton(Enum button) {
        if (button.equals(UP_ORDER_BUTTON)) {
            clickUpperOrderButton();
        } else if (button.equals(DOWN_ORDER_BUTTON)) {
            scrollToDownOrderButton();
            clickDownOrderButton();
        }
    }
}
