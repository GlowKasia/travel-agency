package pl.sdajava25.travelagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdajava25.travelagency.model.Continent;
import pl.sdajava25.travelagency.model.Country;
import pl.sdajava25.travelagency.repository.CountryRepository;

import java.util.List;
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

    public void addNewCountry(Long continentId, Country country) {
        Optional<Continent> foundContinent = continentService.getContinentById(continentId);
        foundContinent.ifPresent(country::setContinent);
        countryRepository.save(country);
    }

    public List<Country> getAllCountriesOfGivenContinent(Long id) {
        return countryRepository.findAllByContinent_Id(id);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
