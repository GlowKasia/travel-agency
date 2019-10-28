package pl.sdajava25.travelagency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private HotelStandard hotelStandard;

    private String description;

    public Hotel(String name, HotelStandard hotelStandard, String description) {
        this.name = name;
        this.hotelStandard = hotelStandard;
        this.description = description;
    }
}
