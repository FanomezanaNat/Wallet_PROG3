package Wallet.Entity;

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

    public Account performTransaction(String label, String type, double amount) {
        Timestamp dateTransaction = new Timestamp(System.currentTimeMillis());
        Transaction transaction = new Transaction(UUID.randomUUID(), label, type, dateTransaction, amount, this.id);

        if (type.equalsIgnoreCase("debit")) {
            balance -= amount;
        } else if (type.equalsIgnoreCase("credit")) {
            balance += amount;
        }
        if (Transactions == null) {
            Transactions = new ArrayList<>();
        }
        Transactions.add(transaction);
        updateDate = dateTransaction;
        return this;
    }

    public static double getBalanceAtDate(Timestamp currentTime, Account account){
        double balanceAtGivenTime =0.0;
        List<Transaction> transactions = account.getTransactions();

        if (transactions != null) {
            for (Transaction transaction:transactions){
                if(!transaction.getTransactionDate().after(currentTime)){
                    if (transaction.getType().equalsIgnoreCase("debit")) {
                        balanceAtGivenTime-= transaction.getAmount();

                    } else if (transaction.getType().equalsIgnoreCase("credit")) {
                        balanceAtGivenTime+= transaction.getAmount();
                    }
                }
            }
            
        }

        return balanceAtGivenTime;
    }


}

