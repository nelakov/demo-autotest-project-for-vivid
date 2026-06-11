package vivid.tests;

import org.junit.jupiter.api.Test;
import vivid.tests.pages.MainPage;


public class MainPageTests extends TestBase {
    MainPage mainPage = new MainPage();

    @Test
    public void checkMainPage() {
        mainPage
                .openPage()
                .checkOpenAccountButton();
    }
}