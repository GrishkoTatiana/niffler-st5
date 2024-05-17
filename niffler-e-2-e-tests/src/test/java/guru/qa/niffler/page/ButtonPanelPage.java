package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Кнопки в верхней части страницы
 */
public class ButtonPanelPage {
    /**
     * Кнопка MAIN PAGE
     */
    private SelenideElement mainPageButton = $x("//li[@data-tooltip-content=\"Main page\"]/a");

    /**
     * Кнопка FRIENDS
     */
    private SelenideElement friendsButton = $x("//li[@data-tooltip-content=data-tooltip-content=\"Friends\"]/a");

    /**
     * Кнопка ALL PEOPLE
     */
    private SelenideElement allPeopleButton = $x("//li[@data-tooltip-content=\"All people\"]/a");

    /**
     * Кнопка PROFILE
     */
    private SelenideElement profileButton = $x("//li[@data-tooltip-content=\"Profile\"]/a");

    /**
     * Кнопка LOGOUT
     */
    private SelenideElement logoutButton = $x("//li[@data-tooltip-content=\"Profile\"]/a");

    @Step("Кликнуть по кнопке MAIN PAGE для перехода на страницу MAIN")
    public MainPage clickMainPageButton() {
        mainPageButton.click();
        return new MainPage();
    }

    @Step("Кликнуть по кнопке FRIENDS")
    public FriendsPage clickFriendsButton() {
        friendsButton.click();
        return new FriendsPage();
    }

    @Step("Кликнуть по кнопке ALL PEOPLE")
    public AllPeoplePage clickAllPeopleButton() {
        allPeopleButton.click();
        return new AllPeoplePage();
    }

    @Step("Кликнуть на кнопку LOGOUT")
    public LoginPage clickLogoutButton() {
        logoutButton.click();
        return new LoginPage();
    }

}
