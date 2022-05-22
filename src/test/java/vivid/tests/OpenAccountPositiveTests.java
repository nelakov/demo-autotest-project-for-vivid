package vivid.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vivid.helpers.DriverUtils;
import vivid.tests.pages.OpenAccountPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OpenAccountPositiveTests extends TestBase {
    OpenAccountPage openAccountPage = new OpenAccountPage();
    Faker faker = new Faker();
    String email = faker.internet().emailAddress();
    String phone = String.valueOf(faker.phoneNumber());

    @Test
    public void checkStartOpenFreeAccountFromDifferentStartedPoints() {
        openAccountPage
                .OpenAccountFromMainBlock()
                .closePage();
    }

    //TODO: waiting fix for displayed phone tab
    @Test
    public void OpenFreeAccountWithPhone() {
        openAccountPage
                .OpenAccountFromMainBlock()
                .checkHeader()
                .checkSubtitile()
                .inputPhone("+" + phone)
                .tapInviteMe()
                .checkSuccessfulInviteWithPhone();
    }

    @Test
    public void OpenFreeAccountWithEmail() {
        openAccountPage
                .OpenAccountFromMainBlock()
                .checkHeader()
                .checkSubtitile()
                .switchToEmail()
                .inputEmail(email)
                .tapInviteMe()
                .checkSuccessfulInviteWithEmail();
    }

    @Test
    @DisplayName("Page console log should not have errors")
    void consoleShouldNotHaveErrorsTest() {
        step("Open url 'https://vivid.money/en-eu/'", () ->
                open("https://vivid.money/en-eu/"));

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";
            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }

}
