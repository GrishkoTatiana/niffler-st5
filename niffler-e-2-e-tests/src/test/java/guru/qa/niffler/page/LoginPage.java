package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

/**
 * Страница авторизации
 *
 * <br><img src="LoginPage.png" width="480" height="558"/>480x558
 */
public class LoginPage {
    /**
     * Поле Username
     */
    private final SelenideElement usernameInput = $x("//input[@placeholder='Type your username']");
    /**
     * Поле Password
     */
    private final SelenideElement passwordInput = $x("//input[@placeholder='Type your password']");
    /**
     * Кнопка LOGIN
     */
    private final SelenideElement loginButton = $x("//button[normalize-space(text())='Sign In']");

    @Step("В поле Username ввести текст: '{username}'")
    public void setUsername(String username) {
        usernameInput.setValue(username);
    }

    @Step("В поле Password ввести текст: '{password}'")
    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    @Step("Нажать кнопку LOGIN")
    public MainPage clickLoginButton() {
        loginButton.click();
        return page(MainPage.class);
    }
}
