package guru.qa.niffler.test;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.jupiter.annotation.meta.User;
import guru.qa.niffler.jupiter.annotation.meta.WebTest;
import guru.qa.niffler.jupiter.extension.UserQueueExtension;
import guru.qa.niffler.model.UserJson;
import guru.qa.niffler.page.AllPeoplePage;
import guru.qa.niffler.page.MainPage;
import guru.qa.niffler.scenarios.LoginScenario;
import io.qameta.allure.AllureId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static guru.qa.niffler.jupiter.annotation.meta.User.UserType.*;

@WebTest
@ExtendWith(UserQueueExtension.class)
public class UsersQueueExampleTest {

    @Test
    @AllureId("1")
    void checkInviteSendAndFriendTest(@User(COMMON) UserJson testUser, @User(WITH_FRIEND) UserJson anotherWithFriend, @User(WITH_INVITATION) UserJson anotherWithInvitation) {
        //Сценарий логина
        MainPage mainPage = LoginScenario.login(testUser.username(), testUser.testData().password());
        //Кликнуть по кнопке ALL PEOPLE
        AllPeoplePage allPeoplePage = mainPage.getButtonPanelPage().clickAllPeopleButton();
        Assertions.assertAll(
                //Проверить, что у пользователя '{username}' в колонке ACTIONS отображается статус: '{status}'
                () -> allPeoplePage.checkStatusByUsername(anotherWithFriend.username(), "You are friends"),
                //Проверить, что у пользователя '{username}' в колонке ACTIONS отображается статус: '{status}'
                () -> allPeoplePage.checkStatusByUsername(anotherWithInvitation.username(), "Pending invitation")
        );
    }


    @Test
    @AllureId("2")
    void loginTest1(@User(COMMON) UserJson testUser, @User(WITH_INVITATION) UserJson anotherWithInvitation) {
        //Сценарий логина
        MainPage mainPage = LoginScenario.login(anotherWithInvitation.username(), anotherWithInvitation.testData().password());
        //Кликнуть по кнопке ALL PEOPLE
        AllPeoplePage allPeoplePage = mainPage.getButtonPanelPage().clickAllPeopleButton();
        //Проверить, что у пользователя '{username}' в колонке ACTIONS отображается статус: '{status}'
        allPeoplePage.checkInviteByUsername(testUser.username());
    }

    @Test
    void loginTest2(@User(COMMON) UserJson testUser) {
        Selenide.open("http://127.0.0.1:3000/");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(testUser.username());
        $("input[name='password']").setValue(testUser.testData().password());
        $("button[type='submit']").click();
        $(".header__avatar").should(visible);
    }

    @Test
    void loginTest3(UserJson testUser) {
        Selenide.open("http://127.0.0.1:3000/");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(testUser.username());
        $("input[name='password']").setValue(testUser.testData().password());
        $("button[type='submit']").click();
        $(".header__avatar").should(visible);
    }

    @Test
    void loginTest4(UserJson testUser) {
        Selenide.open("http://127.0.0.1:3000/");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(testUser.username());
        $("input[name='password']").setValue(testUser.testData().password());
        $("button[type='submit']").click();
        $(".header__avatar").should(visible);
    }
}
