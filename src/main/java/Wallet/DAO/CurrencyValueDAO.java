package Wallet.DAO;

import Wallet.Entity.CurrencyValue;
import lombok.AllArgsConstructor;

import java.sql.*;
import java.util.UUID;

@AllArgsConstructor
public class CurrencyValueDAO {
    private Connection connection;


    public CurrencyValue findByDate(Timestamp date) {
        String sql = "SELECT * FROM CurrencyValue WHERE sourcecurrencyid = '98ac0590-92e3-11ee-b9d1-0242ac120002' " +
                "AND destinationcurrencyid = '98abfe06-92e3-11ee-b9d1-0242ac120002' " +
                "AND currencyvalue.dateeffect <= ? ORDER BY dateeffect DESC LIMIT 1";


        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setTimestamp(3, date);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new CurrencyValue(
                        (UUID) resultSet.getObject("id"),
                        (UUID) resultSet.getObject("sourceCurrencyId"),
                        (UUID) resultSet.getObject("destinationCurrencyId"),
                        resultSet.getDouble("amount"),
                        resultSet.getTimestamp("dateEffect")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Double findAverageCurrencyByDate(String parameter, Date date) {
        String sql = "SELECT"+ parameter +"(amount) averageAmount, date(dateeffect) \"date\" FROM CurrencyValue " +
                "WHERE sourcecurrencyid = '98ac0590-92e3-11ee-b9d1-0242ac120002' " +
                "AND destinationcurrencyid = '98abfe06-92e3-11ee-b9d1-0242ac120002' " +
                "AND date(dateeffect) = ? group by \"date\"";


        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setDate(3, date);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("averageAmount");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
