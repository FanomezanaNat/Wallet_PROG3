package Wallet.DAO;

import Wallet.DatabaseConfiguration.DatabaseConnection;
import Wallet.Entity.CategoryAmount;
import lombok.AllArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor

public class CategoryAmountDAO {
    private final DatabaseConnection databaseConnection;


    public List<CategoryAmount> getCategoryAmountsByDateRangeWithZero(UUID accountId, Timestamp startDate, Timestamp endDate) {
        List<CategoryAmount> categoryAmounts = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection()) {
            if (connection != null) {
                String sql = "SELECT * FROM getCategoryAmountsByDateRangeWithZero(?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setObject(1, accountId);
                    statement.setTimestamp(2, startDate);
                    statement.setTimestamp(3, endDate);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            String categoryName = resultSet.getString("categoryName");
                            double totalAmount = resultSet.getDouble("totalAmount");

                            CategoryAmount categoryAmount = new CategoryAmount(categoryName, totalAmount);
                            categoryAmounts.add(categoryAmount);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryAmounts;
    }


}


