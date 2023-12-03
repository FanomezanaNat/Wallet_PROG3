package Wallet.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection getConnection()throws SQLException {
        return DriverManager.getConnection(
                System.getenv("url"),
                System.getenv("user"),
                System.getenv("password")
        );
    }
}
