package vivid.tests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private final SelenideElement
            mainTitle = $(".videoBlock__title__wMHuI"),
            mainDescription = $(".videoBlock__description__VubX0"),
            openAccountButton = $(byText("Open free account"));


    @Step("Opening main page")
    public MainPage openPage() {
        mainTitle.shouldBe(visible);
        mainTitle.shouldHave(text("Let your money grow"));
        mainDescription.shouldBe(visible);
        mainDescription.shouldHave(text("Grow your money easily with commission-free and instant investing. Turn your everyday spendings into stock rewards. Get cashback and save more money on fees. Open a free account in minutes and see your savings grow."));
        return this;
    }

    @Step("The Open Free Account Button visible and text correct")
    public MainPage checkOpenAccountButton() {
        openAccountButton.shouldBe(visible);
        openAccountButton.shouldHave(text("Open free account"));
        openAccountButton.click();
        return this;
    }
}
