package pl.sdajava25.travelagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdajava25.travelagency.model.TravelDetails;

@Service
public class TravelDetailsService {
    @Autowired
    private TravelDetailsService travelDetailsService;

    public void addTravel(TravelDetails travelDetails) {
        travelDetailsService.addTravel(travelDetails);
    }
}
