package guru.qa.niffler.page;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница ALL PEOPLE
 *
 * <br><img src="AllPeoplePage.png" width="834" height="355"/>
 */
public class AllPeoplePage {
    /**
     * Панель кнопок
     */
    private ButtonPanelPage buttonPanelPage;
    public AllPeoplePage() {
        $x("//h2[normalize-space(text())='Avatar']");
    }


    @Step("Проверить, что у пользователя '{username}' в колонке ACTIONS отображается статус: '{status}'")
    public void checkStatusByUsername(String username, String status) {
        $x("//tr[td[normalize-space(text())='" + username + "']]/td[4]/div/div[1]").shouldHave(text(status));
        buttonPanelPage = new ButtonPanelPage();
    }

    @Step("Проверить, чтo получено приглашение от {username}}")
    public void checkInviteByUsername(String username) {
        $x("//tr[td[normalize-space(text())='" + username + "']]//div[@data-tooltip-id=\"submit-invitation\"]").shouldBe(visible);
    }

    public ButtonPanelPage getButtonPanelPage() {
        return buttonPanelPage;
    }
}
