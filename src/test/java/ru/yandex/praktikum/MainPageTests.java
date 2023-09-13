package ru.yandex.praktikum;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.constants.HomePageLocators.*;
@RunWith(Parameterized.class)

public class MainPageTests {
    private WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final By question;
    private final By answer;
    private final String expectedResult;

    public MainPageTests(By question, By answer, String expectedResult) {
        this.question = question;
        this.answer = answer;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{

                {QUESTION_1, ANSWER_1, TEXT_ANSWER_1},
                {QUESTION_2, ANSWER_2, TEXT_ANSWER_2},
                {QUESTION_3, ANSWER_3, TEXT_ANSWER_3},
                {QUESTION_4, ANSWER_4, TEXT_ANSWER_4},
                {QUESTION_5, ANSWER_5, TEXT_ANSWER_5},
                {QUESTION_6, ANSWER_6, TEXT_ANSWER_6},
                {QUESTION_7, ANSWER_7, TEXT_ANSWER_7},
                {QUESTION_8, ANSWER_8, TEXT_ANSWER_8},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(site);
    }
    @Test
    public void checkQuestions() {
        new MainPage(driver)
                .waitForLoadHomePage()
                .scrollToQuestions()
                .clickQuestion(question)
                .waitLoadAfterClickQuestion(answer);
        String result = driver.findElement(answer).getText();

        assertEquals(expectedResult, result);
    }
    @After
    public void teardown() {
        driver.quit();
    }
}
