package Wallet.Entity;

import lombok.*;

import java.sql.Timestamp;
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
        return "Account:"+
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", updateDate=" + updateDate +
                ", Transactions=" + Transactions +
                ", type='" + type + '\'' +
                ", Currency=" + Currency ;
    }
}
