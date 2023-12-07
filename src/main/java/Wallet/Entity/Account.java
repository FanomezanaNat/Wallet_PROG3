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
    private double amount;
    private Timestamp updateDate;
    private List<Transaction> Transactions;
    private String type;
    private UUID Currency;

    @Override
    public String toString() {
        return "Account:"+
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", updateDate=" + updateDate +
                ", Transactions=" + Transactions +
                ", type='" + type + '\'' +
                ", Currency=" + Currency ;
    }
}
