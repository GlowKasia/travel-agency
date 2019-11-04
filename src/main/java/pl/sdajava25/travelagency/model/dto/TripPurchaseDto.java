package pl.sdajava25.travelagency.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TripPurchaseDto {

    private String purchaseId;
    private String name;
    private String surname;
    private String numberOfAdults;
    private String numberOfChildren;
    private String adultsAvailable;
    private String childrenAvailable;
}
