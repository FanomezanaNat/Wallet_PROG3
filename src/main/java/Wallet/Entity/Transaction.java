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
    private Timestamp transactionDate;
    private Double amount;
    private UUID category;
    private UUID account;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionDate=" + transactionDate +
                ", amount=" + amount +
                ", category=" + category +
                ", account=" + account +
                '}';
    }
}
