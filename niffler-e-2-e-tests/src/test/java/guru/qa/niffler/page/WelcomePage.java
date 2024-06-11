package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Приветственная страница
 *
 * <br><img src="WelcomePage.png" width="380" height="300"/>
 */
public class WelcomePage {
    /**
     * Кнопка LOGIN
     */
    private final SelenideElement loginButton = $("a[href*='redirect']");
    /**
     * Кнопка REGISTER
     */
    private final SelenideElement registerButton = $("a[href*='register']");

    @Step("Нажать на кнопку LOGIN")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    @Step("Нажать на кнопку REGISTER")
    public LoginPage clickRegisterButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

}
