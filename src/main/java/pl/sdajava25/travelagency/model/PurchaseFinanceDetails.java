package pl.sdajava25.travelagency.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PurchaseFinanceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double adultsCost;
    private Double childrenCost;
    private Double totalCost;

    @OneToOne
    private TripPurchase tripPurchase;


}
