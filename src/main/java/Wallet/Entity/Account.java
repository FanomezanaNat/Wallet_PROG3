package Wallet.Entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private int id;
    private UUID idCurrency;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", idCurrency=" + idCurrency +
                '}';
    }
}
