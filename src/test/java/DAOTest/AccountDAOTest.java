package DAOTest;

import Wallet.DAO.AccountDAO;
import Wallet.DatabaseConfiguration.DatabaseConnection;
import Wallet.Entity.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class AccountDAOTest {

    @Test
    void testFindAll() {
        DatabaseConnection connectionManager = new DatabaseConnection();
        try (Connection connection = connectionManager.getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            if (connection != null) {
                assertEquals(3, accountDAO.findAll().size());
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching accounts: " + e.getMessage());
            System.out.println("Failed to retrieve accounts. Please try again later.");
        }
    }

    @Test
    void testSaveAll() {
        DatabaseConnection connectionManager = new DatabaseConnection();
        try (Connection connection = connectionManager.getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            if (connection != null) {
                Account account1 = new Account(UUID.fromString("77386308-7908-4642-8b2a-7f2e54e9b2d3"), "", 300000.0, new Timestamp(System.currentTimeMillis()), "Mobile Money", UUID.fromString("98abfe06-92e3-11ee-b9d1-0242ac120002"));
                Account account2 = new Account(UUID.fromString("71be46cc-4418-4922-836f-4af0dd7977e7"), "", 25000.0, new Timestamp(System.currentTimeMillis()), "Cash", UUID.fromString("98abfe06-92e3-11ee-b9d1-0242ac120002"));
                List<Account> accountsToSave = List.of(account1, account2);
                assertEquals(2, accountDAO.saveAll(accountsToSave).size());
                for (Account account : accountsToSave) {
                    accountDAO.delete(account);
                }
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching accounts: " + e.getMessage());
            System.out.println("Failed to retrieve accounts. Please try again later.");
        }
    }

    @Test
    void testSave() {
        DatabaseConnection connectionManager = new DatabaseConnection();
        try (Connection connection = connectionManager.getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            if (connection != null) {
                Account account = new Account(UUID.fromString("77386308-7908-4642-8b2a-7f2e54e9b2d3"), "", 300000.0, new Timestamp(System.currentTimeMillis()), "Mobile Money", UUID.fromString("98abfe06-92e3-11ee-b9d1-0242ac120002"));
                Account createdAccount = accountDAO.save(account);
                assertNotNull(createdAccount);
                accountDAO.delete(account);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching accounts: " + e.getMessage());
            System.out.println("Failed to retrieve accounts. Please try again later.");
        }
    }

    @Test
    void testDelete() {
        DatabaseConnection connectionManager = new DatabaseConnection();
        try (Connection connection = connectionManager.getConnection()) {
            AccountDAO accountDAO = new AccountDAO(connection);
            if (connection != null) {
                Account account = new Account(UUID.fromString("77386308-7908-4642-8b2a-7f2e54e9b2d3"), "", 300000.0, new Timestamp(System.currentTimeMillis()), "Mobile Money", UUID.fromString("98abfe06-92e3-11ee-b9d1-0242ac120002"));
                Account createdAccount = accountDAO.save(account);
                Account deletedAccount = accountDAO.delete(account);
                assertEquals(createdAccount, deletedAccount);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching accounts: " + e.getMessage());
            System.out.println("Failed to retrieve accounts. Please try again later.");
        }
    }
}
