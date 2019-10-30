package pl.sdajava25.travelagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdajava25.travelagency.model.Country;
import pl.sdajava25.travelagency.repository.CountryRepository;

import java.util.Optional;

@Service
public class CountryService {

    private CountryRepository countryRepository;
    private ContinentService continentService;

    @Autowired
    public CountryService(CountryRepository countryRepository, ContinentService continentService){
        this.countryRepository = countryRepository;
        this.continentService = continentService;
    }

    public Optional<Country> getCountryById(Long id) {
        return countryRepository.findById(id);
    }
}
