package DAOTest;

import Wallet.DAO.TransferHistoryDAO;
import Wallet.DatabaseConfiguration.DatabaseConnection;
import Wallet.Entity.Transaction;
import Wallet.Entity.TransferHistory;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransferHistoryDAOTest {
    @Test
    void testFindAll() {
        DatabaseConnection connectionManager = new DatabaseConnection();
        try (Connection connection = connectionManager.getConnection()) {
            TransferHistoryDAO historyDAO = new TransferHistoryDAO(connection);
            if (connection != null) {
                assertEquals(0, historyDAO.findAll().size());
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching transfer history: " + e.getMessage());
            System.out.println("Failed to retrieve transfer history. Please try again later.");
        }

    }

    @Test
    void testSaveAll() {
        DatabaseConnection connectionManager = new DatabaseConnection();
        try (Connection connection = connectionManager.getConnection()) {
            TransferHistoryDAO historyDAO = new TransferHistoryDAO(connection);
            if (connection != null) {
                TransferHistory transferHistory1 = new TransferHistory(UUID.randomUUID(), UUID.fromString("98ac0d10-92e3-11ee-b9d1-0242ac120002"), UUID.fromString("98ac0ee6-92e3-11ee-b9d1-0242ac120002"), new Timestamp(System.currentTimeMillis()));
                TransferHistory transferHistory2 = new TransferHistory(UUID.randomUUID(), UUID.fromString("98ac0d10-92e3-11ee-b9d1-0242ac120002"), UUID.fromString("98ac0ee6-92e3-11ee-b9d1-0242ac120002"), new Timestamp(System.currentTimeMillis()));
                List<TransferHistory> transferHistoryToSave = List.of(transferHistory1, transferHistory2);
                assertEquals(2, historyDAO.saveAll(transferHistoryToSave).size());
                for (TransferHistory transferHistory : transferHistoryToSave) {
                    historyDAO.delete(transferHistory);
                }
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching transfer history: " + e.getMessage());
            System.out.println("Failed to retrieve transfer history. Please try again later.");
        }
    }

    @Test
    void testSave() {
        DatabaseConnection connectionManager = new DatabaseConnection();
        try (Connection connection = connectionManager.getConnection()) {
            TransferHistoryDAO historyDAO = new TransferHistoryDAO(connection);
            if (connection != null) {
                TransferHistory transferHistory = new TransferHistory(UUID.randomUUID(), UUID.fromString("98ac0d10-92e3-11ee-b9d1-0242ac120002"), UUID.fromString("98ac0ee6-92e3-11ee-b9d1-0242ac120002"), new Timestamp(System.currentTimeMillis()));
                TransferHistory createdTransferHistory = historyDAO.save(transferHistory);
                assertNotNull(createdTransferHistory);
                historyDAO.delete(transferHistory);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching transfer history: " + e.getMessage());
            System.out.println("Failed to retrieve transfer history. Please try again later.");
        }
    }

    @Test
    void testDelete() {
        DatabaseConnection connectionManager = new DatabaseConnection();
        try (Connection connection = connectionManager.getConnection()) {
            TransferHistoryDAO historyDAO = new TransferHistoryDAO(connection);
            if (connection != null) {
                TransferHistory transferHistory = new TransferHistory(UUID.randomUUID(), UUID.fromString("98ac0d10-92e3-11ee-b9d1-0242ac120002"), UUID.fromString("98ac0ee6-92e3-11ee-b9d1-0242ac120002"), new Timestamp(System.currentTimeMillis()));
                TransferHistory createdTransferHistory = historyDAO.save(transferHistory);
                TransferHistory deletedTransferHistory = historyDAO.delete(transferHistory);
                assertEquals(createdTransferHistory, deletedTransferHistory);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching transfer history: " + e.getMessage());
            System.out.println("Failed to retrieve transfer history. Please try again later.");
        }
    }
}
