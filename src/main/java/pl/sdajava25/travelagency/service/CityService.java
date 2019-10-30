package pl.sdajava25.travelagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdajava25.travelagency.model.City;
import pl.sdajava25.travelagency.model.Country;
import pl.sdajava25.travelagency.repository.CityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private CityRepository cityRepository;
    private CountryService countryService;

    @Autowired
    public CityService(CityRepository cityRepository,
                       CountryService countryService){
        this.cityRepository = cityRepository;
        this.countryService = countryService;
    }


    public void addNewCity(Long countryId, City city) {
        Optional<Country> foundCountry = countryService.getCountryById(countryId);
        foundCountry.ifPresent(city::setCountry);
        cityRepository.save(city);
    }

    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    public Optional<City> findCityById(Long id) {
        return cityRepository.findById(id);
    }
}
