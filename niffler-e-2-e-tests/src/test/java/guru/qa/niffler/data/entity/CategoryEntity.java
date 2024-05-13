package guru.qa.niffler.data.entity;

import guru.qa.niffler.model.CategoryJson;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter

public class CategoryEntity implements Serializable {

    private UUID id;
    private String category;
    private String username;

    public static CategoryEntity fromJson(CategoryJson categoryJson) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryJson.id());
        categoryEntity.setCategory(categoryJson.category());
        categoryEntity.setUsername(categoryJson.username());
        return categoryEntity;
    }
}
