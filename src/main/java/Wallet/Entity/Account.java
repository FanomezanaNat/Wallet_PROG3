package Wallet.Entity;

import Wallet.DAO.TransactionDAO;
import Wallet.DAO.TransferHistoryDAO;
import Wallet.DatabaseConfiguration.DatabaseConnection;
import Wallet.DAO.CurrencyValueDAO;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private UUID id;
    private String name;
    private double balance;
    private Timestamp updateDate;
    private List<Transaction> Transactions;
    private String type;
    private UUID Currency;


    public Account(UUID id, String name, double balance, Timestamp updateDate, String type, UUID currency) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.updateDate = updateDate;
        this.type = type;
        Currency = currency;
    }

    public static double getBalanceAtDate(Timestamp currentTime, Account account) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        double balanceAtGivenTime = 0.0;
        List<Transaction> transactions = account.getTransactions();
        TransactionDAO transactionDAO = new TransactionDAO(databaseConnection.getConnection());

        if (transactions != null) {
            for (Transaction transaction : transactions) {
                if (!transaction.getTransactionDate().after(currentTime)) {
                    if (transactionDAO.findTypeById(transaction.getId()).equalsIgnoreCase("debit")) {
                        balanceAtGivenTime -= transaction.getAmount();

                    } else if (transactionDAO.findTypeById(transaction.getId()).equalsIgnoreCase("credit")) {
                        balanceAtGivenTime += transaction.getAmount();
                    }
                }
            }

        }

        return balanceAtGivenTime;
    }

    public static double getBalanceAtCurrentTime(Account account) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        return getBalanceAtDate(currentTime, account);
    }

    public static List<Double> getBalanceHistory(Account account, Timestamp startDate, Timestamp endDate) {
        List<Double> balanceHistory = new ArrayList<>();
        List<Transaction> transactions = account.getTransactions();
        if (transactions != null) {
            for (Transaction transaction : transactions) {
                Timestamp transactionDate = transaction.getTransactionDate();
                if (transactionDate.after(startDate) && transactionDate.before(endDate)) {
                    double balanceAtTransaction = getBalanceAtDate(transactionDate, account);
                    balanceHistory.add(balanceAtTransaction);
                }
            }

        }

        return balanceHistory;
    }

    @Override
    public String toString() {
        return "Account:" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", updateDate=" + updateDate +
                ", Transactions=" + Transactions +
                ", type='" + type + '\'' +
                ", Currency=" + Currency;
    }

    public Account performTransaction(UUID category, double amount) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        TransactionDAO transactionDAO = new TransactionDAO(databaseConnection.getConnection());

        Timestamp dateTransaction = new Timestamp(System.currentTimeMillis());
        Transaction transaction = new Transaction(UUID.randomUUID(), dateTransaction, amount, category, this.id);

        transactionDAO.save(transaction);

        if (transactionDAO.findTypeById(transaction.getId()).equalsIgnoreCase("debit")) {
            balance -= amount;
        } else if (transactionDAO.findTypeById(transaction.getId()).equalsIgnoreCase("credit")) {
            balance += amount;
        }
        if (Transactions == null) {
            Transactions = new ArrayList<>();
        }
        Transactions.add(transaction);
        updateDate = dateTransaction;
        return this;
    }

    public void transferMoney(Account destinationAccount, double amount, UUID category) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        TransferHistoryDAO transferHistoryDAO = new TransferHistoryDAO(databaseConnection.getConnection());
        CurrencyValueDAO currencyValueDAO = new CurrencyValueDAO(databaseConnection.getConnection());

        if (!getId().equals(destinationAccount.getId()))
            if (getCurrency().equals(destinationAccount.getCurrency())) {
                performTransaction(category, amount);
                destinationAccount.performTransaction(category,amount);


                TransferHistory transferHistory = new TransferHistory(UUID.randomUUID(),
                        getTransactions().get(getTransactions().size() - 1).getId(),
                        destinationAccount.getTransactions().get(destinationAccount.getTransactions().size() - 1).getId(),
                        new Timestamp(System.currentTimeMillis()));
                transferHistoryDAO.save(transferHistory);


            } else {
                CurrencyValue currencyValue = currencyValueDAO.findByDate(new Timestamp(System.currentTimeMillis()));
                double convertedAmount = amount * currencyValue.getAmount();

                performTransaction(category, amount);
                destinationAccount.performTransaction(category, convertedAmount);


                TransferHistory transferHistory = new TransferHistory(UUID.randomUUID(),
                        getTransactions().get(getTransactions().size() - 1).getId(),
                        destinationAccount.getTransactions().get(destinationAccount.getTransactions().size() - 1).getId(),
                        new Timestamp(System.currentTimeMillis()));
                transferHistoryDAO.save(transferHistory);


            }
    }

    public double getBalanceAtDateWithExchange(Timestamp currentTime, Account account) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        CurrencyValueDAO currencyValueDAO = new CurrencyValueDAO(databaseConnection.getConnection());
        TransactionDAO transactionDAO = new TransactionDAO(databaseConnection.getConnection());

        double balanceAtGivenTime = 0.0;
        List<Transaction> transactions = account.getTransactions();
        if (transactions != null) {
            for (Transaction transaction : transactions) {
                if (!transaction.getTransactionDate().after(currentTime)) {
                    double amount = transaction.getAmount();
                    if (transactionDAO.findTypeById(transaction.getId()).equalsIgnoreCase("debit")) {
                        CurrencyValue currencyValue = currencyValueDAO.findByDate(currentTime);
                        amount *= currencyValue.getAmount();

                        balanceAtGivenTime -= amount;
                    } else if (transactionDAO.findTypeById(transaction.getId()).equalsIgnoreCase("credit")) {
                        CurrencyValue currencyValue = currencyValueDAO.findByDate(currentTime);
                        amount *= currencyValue.getAmount();

                        balanceAtGivenTime += amount;
                    }
                }
            }
        }

        return balanceAtGivenTime;
    }


}

