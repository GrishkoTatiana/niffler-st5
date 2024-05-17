package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница авторизации
 */
public class LoginPage {
    /**
     * Кнопка LOGIN
     */
    private SelenideElement mainPageButton = $x("//li[@data-tooltip-content=\"Main page\"]/a");
}
