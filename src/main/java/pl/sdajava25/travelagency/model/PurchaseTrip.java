package pl.sdajava25.travelagency.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int price;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTimeFormat dateOfPayment;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime dateOfPurchase;

    private int totalTicket;

    public PurchaseTrip(int price, DateTimeFormat dateOfPayment, LocalDateTime dateOfPurchase, int totalTicket) {
        this.price = price;
        this.dateOfPayment = dateOfPayment;
        this.dateOfPurchase = dateOfPurchase;
        this.totalTicket = totalTicket;
    }
}
