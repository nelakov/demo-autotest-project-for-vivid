package vivid.tests.pages;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class OpenAccountPage {
    private SelenideElement
            buttonOpenAccount = $(byText("Open free account")),
            inviteFormHeader = $(".inviteForm__title__YudQx > div"),
            inviteFormSubtitle = $(".inviteForm__subtitle__QmS8b"),
            switcherToEmail = $(".tabs__tab__RgT_f:nth-child(2)"),
            inputFieldPhone = $(By.cssSelector("[name='PHONE']")),
            inputFieldEmail = $(By.cssSelector("[name='EMAIL']")),
            congratsBlockTitle = $(".messageBlock__title__I9Ic_"),
            congratsBlockContent = $(".messageBlock__content__E36Ex"),
            errorMessege = $(".FormRow__errorBlock__oV8XY.FormRow__errorBlock_showed__Nkbc2"),
            closePage = $(".styles__closeIcon__hI3nh"),
            inviteMeButton = $(".button__fluid__H8ZcO > .button__inner__oQ4Tw");

    @Step("Tap Button Open Account from video block")
    @Test
    public OpenAccountPage OpenAccountFromMainBlock() {
        open("/");
        buttonOpenAccount.click();
        return this;
    }


    @Step("Tap Button Open Account from footer")
    @Test
    public OpenAccountPage OpenAccountFromFooter() {
        executeJavaScript("window.scrollTo(0,3286.39990234375)");
        executeJavaScript("window.scrollTo(0,5055.2001953125)");
        executeJavaScript("window.scrollTo(0,6210.39990234375)");
        buttonOpenAccount.click();
        return this;
    }

    @Step("Close Page")
    @Test
    public OpenAccountPage closePage() {
        closePage.click();
        return this;
    }

    @Step("Invite Form Have Header Text")
    @Test
    public OpenAccountPage checkHeader() {
        inviteFormHeader.shouldBe(visible);
        inviteFormHeader.shouldHave(text("Open your free account now"));
        return this;
    }

    @Step("Invite Form Have Subtitle Text")
    @Test
    public OpenAccountPage checkSubtitile() {
        inviteFormSubtitle.shouldBe(visible);
        inviteFormSubtitle.shouldHave(text("Enter your phone number or e-mail and we'll send you a link to download the Vivid app"));
        return this;
    }

    @Step("Input phone")
    @Test
    public OpenAccountPage inputPhone(String phone) {
        executeJavaScript("document.querySelector('[name=PHONE]').style.opacity = 1");
        inputFieldPhone.sendKeys((CharSequence) phone);
        return this;
    }

    @Step("Switch to Email tab")
    @Test
    public OpenAccountPage switchToEmail() {
        switcherToEmail.click();
        return this;
    }

    @Step("Input email")
    @Test
    public OpenAccountPage inputEmail(String email) {
        executeJavaScript("document.querySelector('[name=EMAIL]').style.opacity = 100");
        inputFieldEmail.sendKeys(email);
        return this;
    }

    @Step("Invite me!")
    @Test
    public OpenAccountPage tapInviteMe() {
        inviteMeButton.click();
        return this;
    }

    @Step("Successful invite with Phone")
    @Test
    public OpenAccountPage checkSuccessfulInviteWithPhone() {
        congratsBlockTitle.shouldBe(visible);
        congratsBlockContent.shouldBe(visible);
        congratsBlockTitle.shouldHave(text("Congrats \uD83C\uDF89"));
        congratsBlockContent.shouldHave(text("Yay! The download link is on the way. Please check your phone"));
        return this;
    }

    @Step("Successful invite with Email")
    @Test
    public OpenAccountPage checkSuccessfulInviteWithEmail() {
        congratsBlockTitle.shouldBe(visible);
        congratsBlockContent.shouldBe(visible);
        congratsBlockTitle.shouldHave(text("Congrats \uD83C\uDF89"));
        congratsBlockContent.shouldHave(text("Yay! The download link is on the way. Please check your e-mail"));
        return this;
    }

    @Step("Unsuccessful invite")
    @Test
    public OpenAccountPage checkUnsuccesfulInvite(String errorMessage) {
        errorMessege.shouldBe(visible);
        errorMessege.shouldHave(text(errorMessage));
        errorMessege.getCssValue("color").contains("#c40b08");
        return this;
    }
}
