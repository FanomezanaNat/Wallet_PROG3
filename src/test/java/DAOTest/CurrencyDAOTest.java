package DAOTest;

import Wallet.DAO.AccountDAO;
import Wallet.DAO.CurrencyDAO;
import Wallet.DatabaseConfiguration.DatabaseConnection;
import Wallet.Entity.Account;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyDAOTest {

    @Test
    void testFindAll(){
        DatabaseConnection connectionManager = new DatabaseConnection();
        try (Connection connection = connectionManager.getConnection()){
            CurrencyDAO currencyDAO = new CurrencyDAO(connection);
            if (connection != null){
                assertEquals(2,currencyDAO.findAll().size());
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching accounts: " + e.getMessage());
            System.out.println("Failed to retrieve accounts. Please try again later.");
        }
    }

}
