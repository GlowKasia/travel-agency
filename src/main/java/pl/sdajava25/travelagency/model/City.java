package pl.sdajava25.travelagency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int hotelId;
    private int countryId;
    private int airportId;

    public City(String name, int hotelId, int countryId, int airportId) {
        this.name = name;
        this.hotelId = hotelId;
        this.countryId = countryId;
        this.airportId = airportId;
    }
}
