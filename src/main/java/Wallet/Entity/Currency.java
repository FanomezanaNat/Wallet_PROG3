package Wallet.Entity;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    private UUID id;
    private String type;

    @Override
    public String toString() {
        return "Currency: " +
                "id: " + id +
                ", type: '" + type + '\'' +
                '}';
    }
}
