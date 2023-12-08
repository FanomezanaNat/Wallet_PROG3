package Wallet.Entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyValue {
    private UUID id;
    private UUID sourceCurrencyId;
    private UUID destinationCurrencyId;
    private double value;
    private Timestamp dateEffect;

    @Override
    public String toString() {
        return "CurrencyValue{" +
                "id=" + id +
                ", sourceCurrencyId=" + sourceCurrencyId +
                ", destinationCurrencyId=" + destinationCurrencyId +
                ", value=" + value +
                ", dateEffect=" + dateEffect +
                '}';
    }
}
