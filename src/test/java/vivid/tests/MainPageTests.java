package vivid.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import vivid.tests.pages.MainPage;


public class MainPageTests extends TestBase {
    MainPage mainPage = new MainPage();

    @Test
    public void checkMainPage() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        mainPage
                .openPage()
                //.playVideoBlock()
                .checkOpenAccountButton();
    }
}