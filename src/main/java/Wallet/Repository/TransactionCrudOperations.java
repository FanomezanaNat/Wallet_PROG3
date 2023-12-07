package Wallet.Repository;

import Wallet.Entity.Transaction;
import lombok.AllArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class TransactionCrudOperations implements CrudOperations<Transaction>{
    private Connection connection;

    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactionList = new ArrayList<>();
        String sql = "SELECT * from transaction";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                transactionList.add(new Transaction(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("label"),
                        resultSet.getString("type"),
                        resultSet.getTimestamp("transactionDate"),
                        resultSet.getInt("amount"),
                        (UUID) resultSet.getObject("account")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return transactionList;
    }

    @Override
    public List<Transaction> saveAll(List<Transaction> toSave) {
        List<Transaction> transactions = new ArrayList<>();
        for (Transaction transaction : toSave) {
            Transaction savedTransaction = save(transaction);
            if (savedTransaction != null) {
                transactions.add(savedTransaction);
            }

        }
        return transactions;
    }

    @Override
    public Transaction save(Transaction toSave) {
        String sql = "INSERT INTO transaction(id, label, type, transactiondate, amount, account) values(?,?,?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setObject(1, toSave.getId());
            statement.setString(2, toSave.getLabel());
            statement.setString(3, toSave.getType());
            statement.setTimestamp(4, (toSave.getTransactionDate()));
            statement.setInt(5, toSave.getAmount());
            statement.setObject(6, toSave.getAccount());

            int rowAffected = statement.executeUpdate();
            if (rowAffected > 0) {
                return toSave;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
