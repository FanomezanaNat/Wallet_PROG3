package Wallet.Repository;

import Wallet.Entity.Account;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class AccountCrudOperations implements CrudOperations<Account>{
    private Connection connection;

    @Override
    public List<Account> findAll() {
        List<Account> accountList = new ArrayList<>();
        String sql = "SELECT * from account";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accountList.add(new Account(
                        resultSet.getInt("id"),
                        (UUID) resultSet.getObject("idCurrency")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return accountList;
    }

    @Override
    public List<Account> saveAll(List<Account> toSave) {
        List<Account> accounts = new ArrayList<>();
        for (Account account : toSave) {
            Account savedAccount = save(account);
            if (savedAccount != null) {
                accounts.add(savedAccount);
            }

        }
        return accounts;
    }

    @Override
    public Account save(Account toSave) {
        String sql = "INSERT INTO account(id, idcurrency) values(?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, toSave.getId());
            statement.setObject(2, toSave.getIdCurrency());

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
