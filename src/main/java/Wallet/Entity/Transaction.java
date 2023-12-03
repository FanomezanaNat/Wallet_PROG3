package Wallet.Entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private UUID id;
    private String type;
    private String transactionDate;
    private int amount;
    private int idAccount;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", amount=" + amount +
                ", idAccount=" + idAccount +
                '}';
    }
}
