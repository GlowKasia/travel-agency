package pl.sdajava25.travelagency.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.sdajava25.travelagency.model.TravelDetails;

public interface TravelDetailsApi {

    @PostMapping()
    public TravelDetails addTravel(@RequestBody TravelDetails travelDetails);
}
