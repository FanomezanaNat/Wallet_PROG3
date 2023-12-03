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
                        resultSet.getString("type"),
                        resultSet.getString("transactionDate"),
                        resultSet.getInt("amount"),
                        resultSet.getInt("idAccount")
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
        String sql = "INSERT INTO transaction(id, type, transactiondate, amount, idaccount) values(?,?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setObject(1, toSave.getId());
            statement.setString(2, toSave.getType());
            statement.setTimestamp(3, Timestamp.valueOf(toSave.getTransactionDate()));
            statement.setInt(4, toSave.getAmount());
            statement.setInt(5, toSave.getIdAccount());

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
