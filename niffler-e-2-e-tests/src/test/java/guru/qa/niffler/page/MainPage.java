package guru.qa.niffler.page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Главная страница
 */
public class MainPage {
    /**
     * Панель кнопок
     */
    private ButtonPanelPage buttonPanelPage;
    public MainPage() {
        $x("//h2[normalize-space(text())='Add new spending']").shouldBe(visible);
        buttonPanelPage = new ButtonPanelPage();
    }

    public ButtonPanelPage getButtonPanelPage() {
        return buttonPanelPage;
    }

}
