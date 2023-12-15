package Wallet.Entity;

import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Setter
public class MoneyFlow {
    private UUID idAccount;
    private Double totalEntrance;
    private Double totalExit;

    public MoneyFlow(Double totalEntrance, Double totalExit) {
        this.totalEntrance = totalEntrance;
        this.totalExit = totalExit;
    }

    @Override
    public String toString() {
        return "MoneyFlow{" +
                "idAccount=" + idAccount +
                ", totalEntrance=" + totalEntrance +
                ", totalExit=" + totalExit +
                '}';
    }
}
