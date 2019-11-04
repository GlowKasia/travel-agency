package pl.sdajava25.travelagency.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sdajava25.travelagency.model.TripDetails;
import pl.sdajava25.travelagency.model.dto.TripDto;
import pl.sdajava25.travelagency.repository.TripDetailsRepository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripDetailsService {

    private TripDetailsRepository tripDetailsRepository;
    private CityService cityService;
    private HotelService hotelService;
    private AirportService airportService;

    public Optional<TripDetails> getTripById(Long id) {
        return tripDetailsRepository.findById(id);
    }

    public TripDetails addNewTrip(TripDetails tripDetails) {
        return tripDetailsRepository.save(tripDetails);
    }

    private Optional<TripDetails> getTripDetailsById(Long id) {
        return tripDetailsRepository.findById(id);
    }

    public List<TripDetails> getAllTrips() {
        return tripDetailsRepository.findAll();
    }

    public List<TripDetails> getAllTrips(Pageable pageable) {
        return tripDetailsRepository.findAll();
    }

    public TripDetails createTripFromDto(TripDto tripDto) {
        TripDetails tripDetails;
        if (tripDto.getId() == null) {
            tripDetails = new TripDetails();
        } else {
            tripDetails = getTripDetailsById(tripDto.getId()).get();
        }
        tripDetails.setDepartureCity(cityService.findCityByName(tripDto.getDepartureCity()));
        tripDetails.setDepartureAirport(airportService.findByName(tripDto.getDepartureAirport()));
        tripDetails.setArrivalCity(cityService.findCityByName(tripDto.getArrivalCity()));
        tripDetails.setArrivalAirport(airportService.findByName(tripDto.getArrivalAirport()));
        tripDetails.setHotel(hotelService.findByName(tripDto.getHotel()));
        tripDetails.setStartDate(LocalDate.parse(tripDto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        tripDetails.setEndDate(LocalDate.parse(tripDto.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        tripDetails.setDays(tripDto.getDays());
        tripDetails.setTypeOfBoard(tripDto.getTypeOfBoard());
        tripDetails.setPricePerAdult(tripDto.getPricePerAdult());
        tripDetails.setPricePerChildren(tripDto.getPricePerChildren());
        tripDetails.setNumberOfAdult(tripDto.getNumberOfAdult());
        tripDetails.setNumberOfChildren(tripDetails.getNumberOfChildren());
        tripDetails.setIsPromoted(tripDto.getIsPromoted());

        return tripDetailsRepository.save(tripDetails);
    }

    public TripDto createTripDtoFromTrip(TripDetails tripDetails) {
        TripDto tripDto = new TripDto();
        tripDto.setId(tripDetails.getId());
        tripDto.setDepartureCity(tripDetails.getDepartureCity().getName());
        tripDto.setDepartureAirport(tripDetails.getDepartureAirport().getName());
        tripDto.setArrivalCity(tripDetails.getArrivalCity().getName());
        tripDto.setArrivalAirport(tripDetails.getArrivalAirport().getName());
        tripDto.setHotel(tripDetails.getHotel().getName());
        tripDto.setStartDate(tripDetails.getStartDate().toString());
        tripDto.setEndDate(tripDetails.getEndDate().toString());
        tripDto.setDays(tripDetails.getDays());
        tripDto.setTypeOfBoard(tripDetails.getTypeOfBoard());
        tripDto.setPricePerAdult(tripDetails.getPricePerAdult());
        tripDto.setPricePerChildren(tripDetails.getPricePerChildren());
        tripDto.setIsPromoted(tripDetails.getIsPromoted());
        tripDto.setNumberOfAdult(tripDetails.getNumberOfAdult());
        tripDto.setNumberOfChildren(tripDetails.getNumberOfChildren());
        return tripDto;
    }

    public List<TripDetails> findATrip(String parameter, String value) {
        if (parameter.equals("departureCity")) {
            return tripDetailsRepository.findAllByDepartureCity_NameContaining(value);
        } else if (parameter.equals("departureAirport")) {
            return tripDetailsRepository.findAllByDepartureAirport_NameContaining(value);
        } else if (parameter.equals("arrivalCity")) {
            return tripDetailsRepository.findAllByArrivalCity_NameContaining(value);
        } else if (parameter.equals("arrivalAirport")) {
            return tripDetailsRepository.findAllByArrivalAirport_NameContaining(value);
        } else if (parameter.equals("hotel")) {
            return tripDetailsRepository.findAllByHotel_NameContaining(value);
        } else if (parameter.equals("startDate")) {
            return tripDetailsRepository.findAllByStartDateContaining(LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-d")));
        } else if (parameter.equals("endDate")) {
            return tripDetailsRepository.findAllByEndDateContaining(LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-d")));
        } else if (parameter.equals("days")) {
            return tripDetailsRepository.findAllByDaysEquals(Integer.valueOf(value));
        } else if (parameter.equals("typeOfBoard")) {
            return tripDetailsRepository.findAllByTypeOfBoardEquals(value);
        } else if (parameter.equals("pricePerAdult")) {
            return tripDetailsRepository.findAllByPricePerAdultIsLessThanEqual(Double.valueOf(value));
        } else if (parameter.equals("pricePerChildren")) {
            return tripDetailsRepository.findAllByPricePerChildrenIsLessThanEqual(Double.valueOf(value));
        } else if (parameter.equals("isPromoted")) {
            return tripDetailsRepository.findAllByIsPromotedContaining(value);
        } else if (parameter.equals("numberOfAdults")) {
            return tripDetailsRepository.findAllByNumberOfAdultsGreaterThanEqual(Integer.valueOf(value));
        } else if (parameter.equals("numberOfChildren")) {
            return tripDetailsRepository.findAllByNumberOfChildrenGreaterThanEqual(Integer.valueOf(value));
        } else
            return null;
    }

    public List<TripDetails> getTripsOrderedByStartDate() {
        List<TripDetails> tripDetails = tripDetailsRepository.findAll();
        tripDetails.sort(Comparator.comparing(TripDetails::getStartDate));
        return tripDetails;
    }


    public List<TripDetails> getAllTripsToGivenCountry(Long countryId) {
        return tripDetailsRepository.findAllByArrivalCity_Country_Id(countryId);
    }

    public List<TripDetails> getAllTripsToGivenCountry(Long countryId, Pageable pageable) {
        return getAllTripsToGivenCountry(countryId, pageable);
    }
}
