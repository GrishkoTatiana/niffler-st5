package guru.qa.niffler.data.repository;

import guru.qa.niffler.data.entity.CategoryEntity;
import guru.qa.niffler.data.entity.SpendEntity;

public interface SpendRepository {

    static SpendRepository getInstance() {
//        if ("sjdbc".equals(System.getProperty("repo"))) {
//            return new SpendRepositorySpringJdbc();
//        }
        return new SpendRepositoryJdbc();
    }

    CategoryEntity createCategory(CategoryEntity category);
    CategoryEntity findCategoryByUsernameAndCategory(String categoryName, String username);
    CategoryEntity editCategory(CategoryEntity category);
    void removeCategory(CategoryEntity category);

    SpendEntity createSpend(SpendEntity spend);
    SpendEntity editSpend(SpendEntity spend);
    void removeSpend(SpendEntity spend);


}
