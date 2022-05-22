package vivid.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import vivid.tests.pages.OpenAccountPage;

import java.util.stream.Stream;

public class OpenAccountNegativeTests extends TestBase {
    OpenAccountPage openAccountPage = new OpenAccountPage();

        static Stream<Arguments> dataForNegativeOpenAccountTestsWithPhone() {
        return Stream.of(
                Arguments.of(" ", "Required"),
                Arguments.of("+", "Please provide a valid phone number"),
                Arguments.of("393509577691", "Please provide a valid phone number"),
                Arguments.of("++39 3509577691", "Please provide a valid phone number"),
                Arguments.of("+++39 3509577691", "Please provide a valid phone number")
        );
    }

    @MethodSource(value = "dataForNegativeOpenAccountTestsWithPhone")
    @ParameterizedTest(name = "Validation the Phone input field {0}")
    @DisplayName("Check validation the Phone input field")
    void OpenFreeAccountWithWrongPhone(String wrongPhone, String errorMessage) {
        openAccountPage
                .OpenAccountFromMainBlock()
                .checkHeader()
                .checkSubtitile()
                .inputPhone(wrongPhone)
                .tapInviteMe()
                .checkUnsuccesfulInvite(errorMessage);
    }
    static Stream<Arguments> dataForNegativeOpenAccountTestsWithEmail() {
        return Stream.of(
                Arguments.of(" ", "Required"),
                Arguments.of("+", "Please provide a valid email address"),
                Arguments.of("Å", "Please provide a valid email address"),
                Arguments.of("email.domain.com", "Please provide a valid email address"),
                Arguments.of("#@%^%#$@#$@#.com", "Please provide a valid email address")
        );
    }

    @MethodSource(value = "dataForNegativeOpenAccountTestsWithEmail")
    @ParameterizedTest(name = "Validation the Email input field {0}")
    void OpenFreeAccountWithWrongEmail(String wrongEmail, String errorMessage) {
        openAccountPage
                .OpenAccountFromMainBlock()
                .checkHeader()
                .checkSubtitile()
                .switchToEmail()
                .inputEmail(wrongEmail)
                .tapInviteMe()
                .checkUnsuccesfulInvite(errorMessage);
    }
}

