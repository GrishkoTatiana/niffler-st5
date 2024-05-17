package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Приветственная страница
 */
public class WelcomePage {
    /**
     * Кнопка LOGIN
     */
    SelenideElement loginPage = $("a[href*='redirect']");
    /**
     * Кнопка REGISTER
     */
    SelenideElement registerPage = $x("a[normalize-space(text())='Register']");

    public WelcomePage() {

    }

}
