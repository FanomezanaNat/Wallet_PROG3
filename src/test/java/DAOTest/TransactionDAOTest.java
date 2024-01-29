package DAOTest;


import Wallet.DAO.TransactionDAO;
import Wallet.DatabaseConfiguration.DatabaseConnection;
import Wallet.Entity.Transaction;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionDAOTest {
    @Test
    void testFindAll() {
        DatabaseConnection connectionManager = new DatabaseConnection();
        try (Connection connection = connectionManager.getConnection()) {
            TransactionDAO transactionDAO = new TransactionDAO(connection);
            if (connection != null) {
                assertEquals(3, transactionDAO.findAll().size());
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching transactions: " + e.getMessage());
            System.out.println("Failed to retrieve transactions. Please try again later.");
        }

    }

    @Test
    void testSaveAll() {
        DatabaseConnection connectionManager = new DatabaseConnection();
        try (Connection connection = connectionManager.getConnection()) {
            TransactionDAO transactionDAO = new TransactionDAO(connection);
            if (connection != null) {
                Transaction transaction1 = new Transaction(UUID.randomUUID(), new Timestamp(System.currentTimeMillis()), 50000.0, UUID.fromString("98ac1b3e-92e3-11ee-b9d1-0242ac120008"), UUID.fromString("98ac0770-92e3-11ee-b9d1-0242ac120002"));
                Transaction transaction2 = new Transaction(UUID.randomUUID(), new Timestamp(System.currentTimeMillis()), 5000.0, UUID.fromString("98ac1b3e-92e3-11ee-b9d1-0242ac120007"), UUID.fromString("98ac0770-92e3-11ee-b9d1-0242ac120002"));
                List<Transaction> transactionsToSave = List.of(transaction1, transaction2);
                assertEquals(2, transactionDAO.saveAll(transactionsToSave).size());
                for (Transaction transaction : transactionsToSave) {
                    transactionDAO.delete(transaction);
                }
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching currencies: " + e.getMessage());
            System.out.println("Failed to retrieve currencies. Please try again later.");
        }
    }

    @Test
    void testSave() {
        DatabaseConnection connectionManager = new DatabaseConnection();
        try (Connection connection = connectionManager.getConnection()) {
            TransactionDAO transactionDAO = new TransactionDAO(connection);
            if (connection != null) {
                Transaction transaction = new Transaction(UUID.randomUUID(), new Timestamp(System.currentTimeMillis()), 50000.0, UUID.fromString("98ac1b3e-92e3-11ee-b9d1-0242ac120008"), UUID.fromString("98ac0770-92e3-11ee-b9d1-0242ac120002"));
                Transaction createdTransaction = transactionDAO.save(transaction);
                assertNotNull(createdTransaction);
                transactionDAO.delete(transaction);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching transactions: " + e.getMessage());
            System.out.println("Failed to retrieve transactions. Please try again later.");
        }
    }

    @Test
    void testDelete() {
        DatabaseConnection connectionManager = new DatabaseConnection();
        try (Connection connection = connectionManager.getConnection()) {
            TransactionDAO transactionDAO = new TransactionDAO(connection);
            if (connection != null) {
                Transaction transaction = new Transaction(UUID.randomUUID(), new Timestamp(System.currentTimeMillis()), 50000.0, UUID.fromString("98ac1b3e-92e3-11ee-b9d1-0242ac120008"), UUID.fromString("98ac0770-92e3-11ee-b9d1-0242ac120002"));
                Transaction createdTransaction = transactionDAO.save(transaction);
                Transaction deletedTransaction = transactionDAO.delete(transaction);
                assertEquals(createdTransaction, deletedTransaction);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching transactions: " + e.getMessage());
            System.out.println("Failed to retrieve transactions. Please try again later.");
        }
    }
}
