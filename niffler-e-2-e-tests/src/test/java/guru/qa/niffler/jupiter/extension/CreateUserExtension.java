package guru.qa.niffler.jupiter.extension;

import guru.qa.niffler.jupiter.annotation.Category;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.model.UserJson;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.platform.commons.support.AnnotationSupport;

public abstract class CreateUserExtension implements BeforeEachCallback, ParameterResolver {
    public static final ExtensionContext.Namespace NAMESPACE
            = ExtensionContext.Namespace.create(CreateUserExtension.class);



    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        AnnotationSupport.findAnnotation(
                extensionContext.getRequiredTestMethod(),
                User.class
        ).ifPresent(cat -> {
                    CategoryJson categoryJson = new CategoryJson(
                            null,
                            cat.category(),
                            cat.username()
                    );
                    extensionContext
                            .getStore(NAMESPACE)
                            .put(extensionContext.getUniqueId(), createCategory(categoryJson));
                }
        createUser();
    }

    private UserJson createUser(UserJson user) {
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return false;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return null;
    }
}
