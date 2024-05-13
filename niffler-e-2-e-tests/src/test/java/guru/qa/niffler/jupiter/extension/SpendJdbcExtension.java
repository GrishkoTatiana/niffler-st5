package guru.qa.niffler.jupiter.extension;

import guru.qa.niffler.data.entity.CategoryEntity;
import guru.qa.niffler.data.entity.SpendEntity;
import guru.qa.niffler.data.repository.SpendRepository;
import guru.qa.niffler.model.SpendJson;

import java.sql.Date;
import java.time.LocalDate;

public class SpendJdbcExtension extends AbstractSpendExtension{
    private final SpendRepository spendRepository = SpendRepository.getInstance();

    @Override
    protected SpendJson createSpend(SpendJson spendJson) {
        CategoryEntity categoryEntity = spendRepository.findCategoryByUsernameAndCategory(spendJson.category(), spendJson.username());
        SpendEntity spendEntity = new SpendEntity();
        spendEntity.setUsername(spendJson.username());
        spendEntity.setSpendDate(Date.valueOf(LocalDate.now()));
        spendEntity.setCurrency(spendJson.currency());
        spendEntity.setDescription(spendJson.description());
        spendEntity.setAmount(spendJson.amount());
        spendEntity.setCategory(categoryEntity);
        spendJson = SpendJson.fromEntity(spendRepository.createSpend(spendEntity));
        return spendJson;
    }

    @Override
    protected void removeSpend(SpendJson spendJson) {
        SpendEntity spendEntity = new SpendEntity();
        spendEntity.setId(spendJson.id());
        spendRepository.removeSpend(spendEntity);
    }
}
