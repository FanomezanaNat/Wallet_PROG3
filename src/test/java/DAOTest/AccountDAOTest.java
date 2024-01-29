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
    void testSave(){
        DatabaseConnection connectionManager = new DatabaseConnection();
        try (Connection connection = connectionManager.getConnection()){
            AccountDAO accountDAO = new AccountDAO(connection);
            if (connection != null){
                Account account = new Account(UUID.fromString("77386308-7908-4642-8b2a-7f2e54e9b2d3"),"",300000.0,new Timestamp(System.currentTimeMillis()),"Mobile Money",UUID.fromString("98abfe06-92e3-11ee-b9d1-0242ac120002"));
                Account createdAccount = accountDAO.save(account);
                assertNotNull(createdAccount);
                accountDAO.delete(account);
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching accounts: " + e.getMessage());
            System.out.println("Failed to retrieve accounts. Please try again later.");
        }
    }
}
