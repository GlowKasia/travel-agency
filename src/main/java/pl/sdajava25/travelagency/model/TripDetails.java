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
    @ManyToOne
    private City departureCity;
    @ManyToOne
    private Airport departureAirport;
    @ManyToOne
    private City arrivalCity;
    @ManyToOne
    private Airport arrivalAirport;
    @OneToOne
    private Hotel hotel;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer days;
    private String typeOfBoard;
    private double pricePerAdult;
    private double pricePerChildren;
    private String isPromoted;
    private Integer numberOfAdult;
    private Integer numberOfChildren;


}
