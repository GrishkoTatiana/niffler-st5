package guru.qa.niffler.test;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.jupiter.annotation.meta.User;
import guru.qa.niffler.jupiter.annotation.meta.WebTest;
import guru.qa.niffler.jupiter.extension.UserQueueExtension;
import guru.qa.niffler.model.UserJson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static guru.qa.niffler.jupiter.annotation.meta.User.UserType.COMMON;
import static guru.qa.niffler.jupiter.annotation.meta.User.UserType.WITH_FRIEND;

@WebTest
@ExtendWith(UserQueueExtension.class)
public class UsersQueueExampleTest {

    @Test
    void loginTest0(@User(COMMON)UserJson testUser, @User(WITH_FRIEND) UserJson anotherFriends) {
        Selenide.open("http://127.0.0.1:3000/");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(testUser.username());
        $("input[name='password']").setValue(testUser.testData().password());
        $("button[type='submit']").click();
        $(".header__avatar").should(visible);
    }


    @Test
    void loginTest1(UserJson testUser) {
        Selenide.open("http://127.0.0.1:3000/");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(testUser.username());
        $("input[name='password']").setValue(testUser.testData().password());
        $("button[type='submit']").click();
        $(".header__avatar").should(visible);
    }

    @Test
    void loginTest2(UserJson testUser) {
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
