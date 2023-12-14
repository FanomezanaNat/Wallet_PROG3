package Wallet.DAO;

import Wallet.Entity.Currency;
import Wallet.Entity.TransferHistory;
import lombok.AllArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
public class TransferHistoryDAO implements CrudOperations<TransferHistory> {
    private Connection connection;



    @Override
    public List<TransferHistory> findAll() {
        List<TransferHistory> transferHistoryList = new ArrayList<>();
        String sql = "SELECT * FROM transferHistory";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                transferHistoryList.add(new TransferHistory(
                        (UUID) resultSet.getObject("id"),
                        (UUID) resultSet.getObject("debitTransactionId "),
                        (UUID) resultSet.getObject("destinationCurrencyId"),
                        resultSet.getTimestamp("transferDate")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transferHistoryList;

    }

    @Override
    public List<TransferHistory> saveAll(List<TransferHistory> toSave) {
        List<TransferHistory> transferHistoryList = new ArrayList<>();
        for (TransferHistory transferHistory : toSave) {
            TransferHistory savedTransferHistory = save(transferHistory);
            if (savedTransferHistory != null) {
                transferHistoryList.add(savedTransferHistory);
            }

        }
        return transferHistoryList;
    }

    @Override
    public TransferHistory save(TransferHistory toSave) {
        String sql = "INSERT INTO transferHistory (id,debitTransactionId,creditTransactionId,transferDate)values(?,?,?,?)" +
                "ON CONFLICT (id) DO UPDATE set debitTransactionId= EXCLUDED.debitTransactionId, creditTransactionId=EXCLUDED.creditTransactionId," +
                "transferDate=EXCLUDED.transferDate";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, toSave.getId());
            statement.setObject(2, toSave.getDebitTransactionId());
            statement.setObject(3, toSave.getCreditTransactionId());
            statement.setTimestamp(4, toSave.getTransferDate());
            int rowAffected = statement.executeUpdate();
            if (rowAffected > 0) {
                return toSave;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }

    List<TransferHistory> getTransferHistoryInDateRange(Timestamp startDate, Timestamp endDate) {
        List<TransferHistory> transferList = new ArrayList<>();
        String sql = "SELECT debitTransactionId, creditTransactionId, transferDate FROM transferHistory WHERE transferDate BETWEEN ? AND ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setTimestamp(1, startDate);
            statement.setTimestamp(2, endDate);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UUID debitTransactionId = (UUID) resultSet.getObject("debitTransactionId");
                UUID creditTransactionId = (UUID) resultSet.getObject("creditTransactionId");
                Timestamp transferDate = resultSet.getTimestamp("transferDate");


                TransferHistory transferHistory=new TransferHistory(debitTransactionId,creditTransactionId,transferDate);
                transferList.add(transferHistory);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transferList;
    }
}
