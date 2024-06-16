package guru.qa.niffler.jupiter.extension;

import guru.qa.niffler.jupiter.annotation.meta.User;
import guru.qa.niffler.model.UserJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import static guru.qa.niffler.model.UserJson.simpleUser;

// Любой тест проходит через него
public class UserQueueExtension implements
        BeforeEachCallback,
        AfterEachCallback,
        ParameterResolver {
    public static final ExtensionContext.Namespace NAMESPACE
            = ExtensionContext.Namespace.create(UserQueueExtension.class);

    private static final Map<User.UserType, Queue<UserJson>> USERS_QUEUE = new ConcurrentHashMap<>();

    static {
        //Отправлены приглашения User.UserType.WITH_INVITATION и User.UserType.WITH_FRIEND
        USERS_QUEUE.put(User.UserType.COMMON, new ConcurrentLinkedQueue<>(
                List.of(
                        simpleUser("barsik", "12345")
                ))
        );
        //Приняты приглашения от User.UserType.COMMON
        USERS_QUEUE.put(User.UserType.WITH_FRIEND, new ConcurrentLinkedQueue<>(
                List.of(
                        simpleUser("dima", "12345")
                ))
        );
        //Получены приглашения от User.UserType.COMMON
        USERS_QUEUE.put(User.UserType.WITH_INVITATION, new ConcurrentLinkedQueue<>(
                List.of(
                        simpleUser("rabbit", "12345")
                ))
        );
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        // Получение тестового метода (@Test который)
        Method testMethod = context.getRequiredTestMethod();

        // Получение @BeforeEach-методов
        List<Method> beforeEachMethods = Arrays.stream(
                context.getRequiredTestClass().getDeclaredMethods()
        ).filter(i -> i.isAnnotationPresent(BeforeEach.class)).toList();

        // Общий список методов, которые необходимо обработать
        List<Method> methods = new ArrayList<>();
        methods.add(testMethod);
        methods.addAll(beforeEachMethods);

        // Общий список параметров, которые мы хотим обработать
        List<Parameter> parameters = methods.stream()
                .flatMap(m -> Arrays.stream(m.getParameters()))
                .filter(p -> p.isAnnotationPresent(User.class))
                .toList();

        // Объект, где хранятся параметры и пользователи. Далее будет сохранен в store
        Map<Parameter, UserJson> users = new HashMap<>();

        // Обрабатываем каждый из полученных параметров
        for (Parameter parameter : parameters) {
            User.UserType selector = parameter.getAnnotation(User.class).value();

            UserJson userForTest = null;

            // Получение очереди с необходимым типом пользователей
            Queue<UserJson> queue = USERS_QUEUE.get(selector);

            // "Умное ожидание" пользователя
            while (userForTest == null) {
                userForTest = queue.poll();
            }

            // Добавляем полученного из очереди пользователя в наш объект
            users.put(parameter, userForTest);
        }

        // Сохраняем данные о пользователях в store
        context.getStore(NAMESPACE).put(context.getUniqueId(), users);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        // Получаем мапу из хранилища
        Map<Parameter, UserJson> users = context.getStore(NAMESPACE).get(context.getUniqueId(), Map.class);
        for (Map.Entry<Parameter, UserJson> user : users.entrySet()) {
            // Возвращаем обратно пользователя в соответствующую очередь
            User.UserType userType = user.getKey().getAnnotation(User.class).value();
            USERS_QUEUE.get(userType).add(user.getValue());
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType().isAssignableFrom(UserJson.class)
                && parameterContext.getParameter().isAnnotationPresent(User.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        Parameter parameter = parameterContext.getParameter();
        return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId(), Map.class).get(parameter);
    }
}
