package pl.sdajava25.travelagency.model.dto;

import lombok.Data;

@Data
public class TripDto {

    private Long id;
    private String departureCity;
    private String departureAirport;
    private String arrivalCity;
    private String arrivalAirport;
    private String hotel;
    private String startDate;
    private String endDate;
    private Integer days;
    private String typeOfBoard;
    private Double pricePerAdult;
    private Double pricePerChildren;
    private String isPromoted;
    private Integer numberOfAdult;
    private Integer numberOfChildren;

}
