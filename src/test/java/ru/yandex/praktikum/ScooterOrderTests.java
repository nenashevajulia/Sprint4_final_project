package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.constants.Colors.BLACK_PEARL;
import static ru.yandex.praktikum.constants.Colors.GREY_HOPELESSNESS;
import static ru.yandex.praktikum.constants.OrderButton.DOWN_ORDER_BUTTON;
import static ru.yandex.praktikum.constants.OrderButton.UP_ORDER_BUTTON;
import static ru.yandex.praktikum.constants.ScooterOrderPageLocators.*;


@RunWith(Parameterized.class)
public class ScooterOrderTests {
    private WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phoneNumber;
    private final String date;
    private final String rentalPeriod;
    private final Enum colour;
    private final String comment;
    private final String expectedResult = "Заказ оформлен";
    private final Enum orderButton;

    public ScooterOrderTests(Enum orderButton, String firstName, String lastName, String address, int metroStation, String phoneNumber,
                             String date, String rentalPeriod, Enum colour, String comment) {
        this.orderButton = orderButton;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {UP_ORDER_BUTTON, "Елизавета", "Тихомирова", "Малый Черкасский пер., 19", 12, "79981478965", "25.09.2023", ONE_DAY, BLACK_PEARL, "комментарий заказа"},
                {UP_ORDER_BUTTON, "Герман", "Никитин", "Саринский пр-д, 95", 71, "89874563259", "05.09.2023", FIVE_DAYS, GREY_HOPELESSNESS, "комментарий заказа"},
                {UP_ORDER_BUTTON, "Артем", "Казаков", "ул. Остоженка, 94", 100, "+79631478965", "13.09.2023", SEVEN_DAYS, BLACK_PEARL, "комментарий заказа"},
                {DOWN_ORDER_BUTTON, "Александра", "Нефедова", "Чистый пер., 91", 12, "79981478966", "25.09.2023", ONE_DAY, GREY_HOPELESSNESS, "комментарий заказа"},
                {DOWN_ORDER_BUTTON, "Алиса", "Иванова", "Новый Арбат ул., 59", 71, "89874563257", "05.09.2023", FIVE_DAYS, BLACK_PEARL, "комментарий заказа"},
                {DOWN_ORDER_BUTTON, "Олеся", "Макарова", "Малая Бронная ул., 42, Москва", 100, "+79631478961", "13.09.2023", SEVEN_DAYS, GREY_HOPELESSNESS, "комментарий заказа"},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(site);
    }

    @Test
    public void scooterOrderTests() {
        new MainPage(driver)
                .waitForLoadHomePage()
                .clickOrderButton(orderButton);

        new ClientOrderPage(driver)
                .waitForLoadOrderPage()
                .inputFirstName(firstName)
                .inputLastName(lastName)
                .inputAddress(address)
                .chooseMetroStation(metroStation)
                .inputPhoneNumber(phoneNumber)
                .clickButtonNext();

        new ScooterOrderPage(driver)
                .waitAboutRentHeader()
                .inputDate(date)
                .inputRentalPeriod(rentalPeriod)
                .changeColour(colour)
                .inputComment(comment)
                .clickCreateOrderButton();

        ModalBox modalBox = new ModalBox(driver);
        modalBox.clickButtonYes();

        assertTrue(modalBox.getOrderNumberHeader().contains(expectedResult));
    }

    @After
    public void teardown() {
        driver.quit();
    }


}
