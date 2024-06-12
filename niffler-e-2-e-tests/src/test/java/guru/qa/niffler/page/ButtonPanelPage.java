package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

/**
 * Кнопки в верхней части страницы
 *
 * <br><img src="ButtonPanelPage.png" width="382" height="100"/>
 */
public class ButtonPanelPage {
    /**
     * Кнопка MAIN PAGE
     */
    private final SelenideElement mainPageButton = $x("//li[@data-tooltip-content=\"Main page\"]/a");
    /**
     * Кнопка FRIENDS
     */
    private final SelenideElement friendsButton = $x("//li[@data-tooltip-content=data-tooltip-content=\"Friends\"]/a");
    /**
     * Кнопка ALL PEOPLE
     */
    private final SelenideElement allPeopleButton = $x("//li[@data-tooltip-content=\"All people\"]/a");
    /**
     * Кнопка PROFILE
     */
    private final SelenideElement profileButton = $x("//li[@data-tooltip-content=\"Profile\"]/a");
    /**
     * Кнопка LOGOUT
     */
    private final SelenideElement logoutButton = $x("//li[@data-tooltip-content=\"Profile\"]/a");

    @Step("Кликнуть по кнопке MAIN PAGE для перехода на страницу MAIN")
    public MainPage clickMainPageButton() {
        mainPageButton.click();
        return page(MainPage.class);
    }

    @Step("Кликнуть по кнопке FRIENDS")
    public FriendsPage clickFriendsButton() {
        friendsButton.click();
        return page(FriendsPage.class);
    }

    @Step("Кликнуть по кнопке ALL PEOPLE")
    public AllPeoplePage clickAllPeopleButton() {
        allPeopleButton.click();
        return page(AllPeoplePage.class);
    }

    @Step("Кликнуть на кнопку LOGOUT")
    public LoginPage clickLogoutButton() {
        logoutButton.click();
        return page(LoginPage.class);
    }
}
