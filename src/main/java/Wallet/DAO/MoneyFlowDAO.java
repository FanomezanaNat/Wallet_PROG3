package Wallet.DAO;

import Wallet.Entity.MoneyFlow;
import Wallet.Entity.Transaction;
import lombok.AllArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class MoneyFlowDAO {
    private Connection connection;

    public MoneyFlow getMoneyFlowByDateRange(UUID idAccount, Date startDate, Date endDate){
        List<Transaction> transactionList = new ArrayList<>();
        String sql = "select sum(CASE WHEN t.name = 'credit' THEN transaction.amount ELSE 0 END) TotalEntrance, " +
                "sum(CASE WHEN t.name = 'debit' THEN transaction.amount ELSE 0 END) TotalExit " +
                "from transaction " +
                "INNER JOIN category ON transaction.category = category.id " +
                "INNER JOIN type t on category.type = t.id " +
                "where account = ? " +
                "AND transaction.transactionDate BETWEEN ? AND ? ";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setObject(1, idAccount);
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new MoneyFlow(
                        resultSet.getDouble("TotalEntrance"),
                        resultSet.getDouble("TotalExit")
                );
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
