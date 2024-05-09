package guru.qa.niffler.jupiter.extension;


import guru.qa.niffler.jupiter.annotation.Category;
import guru.qa.niffler.model.CategoryJson;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;

public abstract class AbstractCategoryExtension implements BeforeEachCallback, AfterEachCallback {
    public static final ExtensionContext.Namespace NAMESPACE
            = ExtensionContext.Namespace.create(CategoryExtension.class);
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        AnnotationSupport.findAnnotation(
                context.getRequiredTestMethod(),
                Category.class
        ).ifPresent(
                cat -> {
                    CategoryJson category = new CategoryJson(null, cat.category(), cat.username());

                    category = createCategory(category);

                    context.getStore(NAMESPACE).put(
                            context.getUniqueId(), category
                    );
                }
        );

    }
    @Override
    public void afterEach(ExtensionContext context){
        CategoryJson categoryJson = context.getStore(NAMESPACE).get(context.getUniqueId(), CategoryJson.class);
        removeCategory(categoryJson);
    }



    protected abstract CategoryJson createCategory(CategoryJson category);
    protected abstract void removeCategory(CategoryJson category);


}
