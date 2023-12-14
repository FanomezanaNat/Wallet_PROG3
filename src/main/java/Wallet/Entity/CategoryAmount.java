package Wallet.Entity;

import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Setter
public class CategoryAmount {
    private UUID idAccount;
    private String categoryName;
    private Double amount;

    public CategoryAmount(String categoryName, Double amount) {
        this.categoryName = categoryName;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CategoryAmount{" +
                "idAccount=" + idAccount +
                ", categoryName='" + categoryName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
