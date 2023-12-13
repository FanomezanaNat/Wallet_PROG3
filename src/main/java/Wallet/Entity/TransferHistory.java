package Wallet.Entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TransferHistory {
    private UUID id;
    private UUID debitTransactionId;
    private UUID creditTransactionId;
    private Timestamp transferDate;

    public TransferHistory(UUID debitTransactionId, UUID creditTransactionId, Timestamp transferDate) {
        this.debitTransactionId = debitTransactionId;
        this.creditTransactionId = creditTransactionId;
        this.transferDate = transferDate;
    }

    @Override
    public String toString() {
        return "TransferHistory{" +
                "id=" + id +
                ", debitTransactionId=" + debitTransactionId +
                ", creditTransactionId=" + creditTransactionId +
                ", transferDate=" + transferDate +
                '}';
    }
}
