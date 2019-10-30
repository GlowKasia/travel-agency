package pl.sdajava25.travelagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sdajava25.travelagency.model.Airport;
import pl.sdajava25.travelagency.service.AirportService;

@Controller
public class AirportController {

    private AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService){
        this.airportService = airportService;
    }

    @GetMapping("admin/add-airport/{cityId}")
    public String addAirport (@PathVariable ("cityId") Long cityId,
                              Model model){
        model.addAttribute("newAirport", new Airport());
        return "airport/add-airport";
    }

    @PostMapping("admin/add-airport/{cityId}")
    public String addAirportPost(@PathVariable("cityId") Long cityId,
                                 @ModelAttribute("newAirport") Airport airport){
        airportService.addNewAirport(cityId, airport);
        return "redirect:/airport/list/{cityId}";
    }

}
