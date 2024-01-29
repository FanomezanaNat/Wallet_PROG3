package Wallet;

import Wallet.DatabaseConfiguration.DatabaseConnection;
import Wallet.Entity.Account;
import Wallet.Entity.Currency;
import Wallet.Entity.Transaction;
import Wallet.DAO.AccountDAO;
import Wallet.DAO.CurrencyDAO;
import Wallet.DAO.TransactionDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection connectionManager = new DatabaseConnection();

        try (Connection connection = connectionManager.getConnection()){
            AccountDAO accountDAO = new AccountDAO(connection);
            if (connection != null){
                //Find all accounts
                List<Account> allAccounts = accountDAO.findAll();
                System.out.println("All Accounts:");
                for (Account account : allAccounts) {
                    System.out.println(account.toString());
                }
                // Creating a new account
                Account newAccount = new Account( UUID.randomUUID(), "", 500000.0,new Timestamp(System.currentTimeMillis()),"Bank",UUID.fromString("98abfe06-92e3-11ee-b9d1-0242ac120002"));
                Account createdAccount = accountDAO.save(newAccount);
                if (createdAccount != null) {
                    System.out.println("New account created: " + createdAccount);
                } else {
                    System.out.println("Failed to create a new account.");
                }

            }

        } catch (SQLException e) {
            System.err.println("An error occurred while fetching accounts: " + e.getMessage());
            System.out.println("Failed to retrieve accounts. Please try again later.");
        }

        try (Connection connection = connectionManager.getConnection()){
            CurrencyDAO currencyDAO = new CurrencyDAO(connection);
            if (connection != null){
                //Find all currencies
                List<Currency> allCurrencies = currencyDAO.findAll();
                System.out.println("All Currencies:");
                for (Currency currency : allCurrencies) {
                    System.out.println(currency.toString());
                }
                // Creating a new currency
                Currency newCurrency = new Currency(UUID.randomUUID(),"Yuan","CNY");
                Currency createdCurrency = currencyDAO.save(newCurrency);
                if (createdCurrency != null) {
                    System.out.println("New currency created: " + createdCurrency);
                } else {
                    System.out.println("Failed to create a new currency.");
                }

            }

        } catch (SQLException e) {
            System.err.println("An error occurred while fetching currencies: " + e.getMessage());
            System.out.println("Failed to retrieve currencies. Please try again later.");
        }


        try (Connection connection = connectionManager.getConnection()){
            TransactionDAO transactionDAO = new TransactionDAO(connection);
            if (connection != null){
                //Find all transactions
                List<Transaction> allTransactions = transactionDAO.findAll();
                System.out.println("All Transactions:");
                for (Transaction transaction : allTransactions) {
                    System.out.println(transaction.toString());
                }
                // Creating a new transaction
                Transaction newTransaction = new Transaction(UUID.randomUUID(), new Timestamp(System.currentTimeMillis()),50000.0,UUID.fromString("98ac1b3e-92e3-11ee-b9d1-0242ac120008"),UUID.fromString("98ac0770-92e3-11ee-b9d1-0242ac120002"));
                Transaction createdTransaction = transactionDAO.save(newTransaction);
                if (createdTransaction != null) {
                    System.out.println("New transaction created: " + createdTransaction);
                } else {
                    System.out.println("Failed to create a new transaction.");
                }

            }

        } catch (SQLException e) {
            System.err.println("An error occurred while fetching transactions: " + e.getMessage());
            System.out.println("Failed to retrieve transactions. Please try again later.");
        }



    }
}
