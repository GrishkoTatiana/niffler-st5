package guru.qa.niffler.jupiter.extension;

import guru.qa.niffler.jupiter.annotation.Spend;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.model.SpendJson;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.platform.commons.support.AnnotationSupport;

public abstract class AbstractSpendExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {
    public static final ExtensionContext.Namespace NAMESPACE
            = ExtensionContext.Namespace.create(SpendExtension.class);
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        AnnotationSupport.findAnnotation(
                context.getRequiredTestMethod(),
                Spend.class
        ).ifPresent(
                cat -> {
                    CategoryJson category = context.getStore(CategoryExtension.NAMESPACE).get(
                            context.getUniqueId(),
                            CategoryJson.class
                    );
                    SpendJson spend = new SpendJson(null, null, category.category(), cat.currency(),cat.amount(), cat.description(), category.username());

                    spend = createSpend(spend);

                    context.getStore(NAMESPACE).put(
                            context.getUniqueId(), spend
                    );
                }
        );

    }
    @Override
    public void afterEach(ExtensionContext context){
        SpendJson spendJson = context.getStore(NAMESPACE).get(context.getUniqueId(), SpendJson.class);
        removeSpend(spendJson);
    }

    protected abstract SpendJson createSpend(SpendJson spend);
    protected abstract void removeSpend(SpendJson spend);
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext
                .getParameter()
                .getType()
                .isAssignableFrom(SpendJson.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId());
    }

}
