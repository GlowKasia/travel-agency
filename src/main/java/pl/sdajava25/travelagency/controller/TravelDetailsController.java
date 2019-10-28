package pl.sdajava25.travelagency.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sdajava25.travelagency.model.TravelDetails;
import pl.sdajava25.travelagency.service.TravelDetailsService;

@Controller
@AllArgsConstructor
public class TravelDetailsController {
    public final TravelDetailsService travelDetailsService;

    @GetMapping("/add")
    public String addTravel(){
        return "travel-details";
    }

    @PostMapping("/add")
    public String addTravel(TravelDetails travelDetails){
        travelDetailsService.addTravel(travelDetails);
        return "redirect:/add";
    }

}
