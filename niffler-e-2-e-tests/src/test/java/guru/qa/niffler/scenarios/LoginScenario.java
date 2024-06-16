package guru.qa.niffler.scenarios;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.page.LoginPage;
import guru.qa.niffler.page.MainPage;
import guru.qa.niffler.page.WelcomePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.page;

/**
 * Сценарий авторизации
 */
public class LoginScenario {

    public final static String WELCOME_PAGE_URL = "http://127.0.0.1:3000/";

    @Step("Вход пользователем под учётной записью {username}")
    public static MainPage login(String username, String password) {
        WelcomePage welcomePage = Selenide.open(WELCOME_PAGE_URL, WelcomePage.class);
        LoginPage loginPage = welcomePage.clickLoginButton();
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        return page(MainPage.class);
    }
}
