package Wallet.Entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private UUID id;
    private String label;
    private String type;
    private Timestamp transactionDate;
    private Double amount;
    private UUID account;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", type='" + type + '\'' +
                ", transactionDate=" + transactionDate +
                ", amount=" + amount +
                ", account=" + account +
                '}';
    }
}
