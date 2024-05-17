package guru.qa.niffler.jupiter.annotation.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
public @interface User {
    UserType value() default UserType.COMMON;
    enum UserType {
        COMMON, WITH_FRIEND, WITH_INVITATION
    }
}
