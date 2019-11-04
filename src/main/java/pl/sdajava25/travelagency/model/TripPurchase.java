package pl.sdajava25.travelagency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TripPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TripDetails tripDetails;
    @OneToOne
    private User user;
    private Integer numberOfAdult;
    private Integer numberOfChildren;
    @OneToOne
    private PurchaseFinanceDetails financeDetails;
}
