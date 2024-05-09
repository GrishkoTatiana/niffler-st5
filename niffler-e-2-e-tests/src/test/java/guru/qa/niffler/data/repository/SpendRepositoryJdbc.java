package guru.qa.niffler.data.repository;

import guru.qa.niffler.data.DataBase;
import guru.qa.niffler.data.entity.CategoryEntity;
import guru.qa.niffler.data.entity.SpendEntity;
import guru.qa.niffler.data.jdbc.DataSourceProvider;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SpendRepositoryJdbc implements SpendRepository{

    private static final DataSource spendDataSource = DataSourceProvider.dataSource(DataBase.SPEND);
    @Override
    public CategoryEntity createCategory(CategoryEntity category) {
        try (Connection connect = spendDataSource.getConnection(); PreparedStatement ps = connect.prepareStatement(
                "INSERT INTO category (category, username) VALUES (?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            ps.setString(1, category.getCategory());
            ps.setString(2, category.getUsername());
            ps.executeUpdate();

            UUID generatedId = null;
            try(ResultSet resultSet = ps.getGeneratedKeys()) {
                if(resultSet.next()) {
                    generatedId = UUID.fromString(resultSet.getString("id"));
                } else
                {
                    throw new IllegalStateException();
                }
            }
            category.setId(generatedId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public CategoryEntity editCategory(CategoryEntity category) {
        try (Connection connect = spendDataSource.getConnection(); PreparedStatement ps = connect.prepareStatement(
                "UPDATE category SET category = ?, username = ? WHERE id = ?"
        )) {
            ps.setString(1, category.getCategory());
            ps.setString(2, category.getUsername());
            ps.setObject(3, category.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public void removeCategory(CategoryEntity category) {
        try (Connection connect = spendDataSource.getConnection(); PreparedStatement ps = connect.prepareStatement(
                "DELETE FROM category WHERE id = ?"
        )) {
            ps.setObject(1, category.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SpendEntity createSpend(SpendEntity spend) {
        try (Connection connect = spendDataSource.getConnection(); PreparedStatement ps = connect.prepareStatement(
                "INSERT INTO spend (username, currency, spendDate, amount, description, category) VALUES (?, ?, ?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            ps.setString(1, spend.getUsername());
            ps.setObject(2, spend.getCurrency());
            ps.setDate(3, (Date) spend.getSpendDate());
            ps.setDouble(4, spend.getAmount());
            ps.setString(5, spend.getDescription());
            ps.setObject(6, spend.getCategory());
            ps.executeUpdate();

            UUID generatedId = null;
            try(ResultSet resultSet = ps.getGeneratedKeys()) {
                if(resultSet.next()) {
                    generatedId = UUID.fromString(resultSet.getString("id"));
                } else
                {
                    throw new IllegalStateException();
                }
            }
            spend.setId(generatedId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return spend;
    }

    @Override
    public SpendEntity editSpend(SpendEntity spend) {
        try (Connection connect = spendDataSource.getConnection(); PreparedStatement ps = connect.prepareStatement(
                "UPDATE spend SET username = ?, currency = ?, spendDate = ?, amount = ?, description = ?, category = ? WHERE id = ?"
        )) {
            ps.setString(1, spend.getUsername());
            ps.setObject(2, spend.getCurrency());
            ps.setDate(3, (Date) spend.getSpendDate());
            ps.setDouble(4, spend.getAmount());
            ps.setString(5, spend.getDescription());
            ps.setObject(6, spend.getCategory());
            ps.setObject(7, spend.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return spend;
    }

    @Override
    public void removeSpend(SpendEntity spend) {
        try (Connection connect = spendDataSource.getConnection(); PreparedStatement ps = connect.prepareStatement(
                "DELETE FROM spend WHERE id = ?"
        )) {
            ps.setObject(1, spend.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
