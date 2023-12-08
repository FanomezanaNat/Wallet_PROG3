package Wallet;

import Wallet.DatabaseConfiguration.DatabaseConnection;
import Wallet.Entity.Account;
import Wallet.Entity.Currency;
import Wallet.Entity.Transaction;
import Wallet.Repository.AccountCrudOperations;
import Wallet.Repository.CurrencyCrudOperations;
import Wallet.Repository.TransactionCrudOperations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection connectionManager = new DatabaseConnection();

        try (Connection connection = connectionManager.getConnection()){
            AccountCrudOperations accountCrudOperations = new AccountCrudOperations(connection);
            if (connection != null){
                //Find all accounts
                List<Account> allAccounts = accountCrudOperations.findAll();
                System.out.println("All Accounts:");
                for (Account account : allAccounts) {
                    System.out.println(account.toString());
                }
                // Creating a new account
                Account newAccount = new Account( UUID.randomUUID(), "", 500000.0,new Timestamp(System.currentTimeMillis()),"Bank",UUID.fromString("98abfe06-92e3-11ee-b9d1-0242ac120002"));
                Account createdAccount = accountCrudOperations.save(newAccount);
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
            CurrencyCrudOperations currencyCrudOperations = new CurrencyCrudOperations(connection);
            if (connection != null){
                //Find all currencies
                List<Currency> allCurrencies = currencyCrudOperations.findAll();
                System.out.println("All Currencies:");
                for (Currency currency : allCurrencies) {
                    System.out.println(currency.toString());
                }
                // Creating a new currency
                Currency newCurrency = new Currency(UUID.randomUUID(),"Yuan","CNY");
                Currency createdCurrency = currencyCrudOperations.save(newCurrency);
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
            TransactionCrudOperations transactionCrudOperations = new TransactionCrudOperations(connection);
            if (connection != null){
                //Find all transactions
                List<Transaction> allTransactions = transactionCrudOperations.findAll();
                System.out.println("All Transactions:");
                for (Transaction transaction : allTransactions) {
                    System.out.println(transaction.toString());
                }
                // Creating a new transaction
                Transaction newTransaction = new Transaction(UUID.randomUUID(),"Internet","Debit",new Timestamp(System.currentTimeMillis()),50000.0,UUID.fromString("'98abfe06-92e3-11ee-b9d1-0242ac120002'"));
                Transaction createdTransaction = transactionCrudOperations.save(newTransaction);
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
