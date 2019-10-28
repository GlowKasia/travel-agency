package pl.sdajava25.travelagency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.DateFormat;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TripDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String start;
    private String destination;
    private LocalDate dateOfDeparture;
    private LocalDate dateOfReturn;
    private Integer days;

    @Enumerated(EnumType.STRING)
    private TypeOfBoard typeOfBoard;

    private Integer pricePerAdult;
    private Integer pricePerChildren;

    private Integer numberOfAdult;
    private Integer numberOfChildren;


}
