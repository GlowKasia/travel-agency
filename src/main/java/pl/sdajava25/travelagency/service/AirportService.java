package pl.sdajava25.travelagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdajava25.travelagency.model.Airport;
import pl.sdajava25.travelagency.model.City;
import pl.sdajava25.travelagency.repository.AirportRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private AirportRepository airportRepository;
    private CityService cityService;

    @Autowired
    public AirportService (AirportRepository airportRepository,
                           CityService cityService){
        this.airportRepository = airportRepository;
        this.cityService = cityService;
    }


    public void addNewAirport(Long cityId, Airport airport) {
        Optional<City> foundCity = cityService.findCityById(cityId);
        foundCity.ifPresent(airport::setCity);
        airportRepository.save(airport);

    }

    public List<Airport> getAllAirports(){
        return airportRepository.findAll();
    }
    public List<Airport> getAllAirportsOfGivenCity(Long cityId) {
        return airportRepository.findAllByCity_Id(cityId);
    }

    public Airport findByName(String name) {
        return airportRepository.findByNameContaining(name);
    }
}
