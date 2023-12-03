package Wallet.Repository;

import Wallet.Entity.Currency;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class CurrencyCrudOperations implements CrudOperations<Currency>{
    private Connection connection;

    @Override
    public List<Currency> findAll() {
        List<Currency> currencyList = new ArrayList<>();
        String sql = "SELECT * from currency";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                currencyList.add(new Currency(
                        (UUID) resultSet.getObject("id"),
                        resultSet.getString("type")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return currencyList;
    }

    @Override
    public List<Currency> saveAll(List<Currency> toSave) {
        List<Currency> currencies = new ArrayList<>();
        for (Currency currency : toSave) {
            Currency savedCurrency = save(currency);
            if (savedCurrency != null) {
                currencies.add(savedCurrency);
            }

        }
        return currencies;
    }

    @Override
    public Currency save(Currency toSave) {
        String sql = "INSERT INTO currency(id, type) values(?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setObject(1, toSave.getId());
            statement.setString(2, toSave.getType());

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
