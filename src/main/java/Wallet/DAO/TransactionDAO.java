package Wallet.DAO;

import Wallet.Entity.Transaction;
import lombok.AllArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class TransactionDAO implements CrudOperations<Transaction>{
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
                        resultSet.getTimestamp("transactionDate"),
                        resultSet.getDouble("amount"),
                        (UUID) resultSet.getObject("category"),
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
        String sql = "INSERT INTO transaction(id, transactiondate, amount, category,account) values(?,?,?,?,?)"+
                "ON CONFLICT (id) DO UPDATE SET category=EXCLUDED.category, " +
                "transactionDate=EXCLUDED.transactionDate, amount=EXCLUDED.amount, account=EXCLUDED.account";;

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setObject(1, toSave.getId());
            statement.setTimestamp(2, (toSave.getTransactionDate()));
            statement.setDouble(3, toSave.getAmount());
            statement.setObject(4, toSave.getCategory());
            statement.setObject(5, toSave.getAccount());

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
