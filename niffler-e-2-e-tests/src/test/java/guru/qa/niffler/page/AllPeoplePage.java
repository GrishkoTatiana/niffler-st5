package guru.qa.niffler.page;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница ALL PEOPLE
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

    public ButtonPanelPage getButtonPanelPage() {
        return buttonPanelPage;
    }
}
