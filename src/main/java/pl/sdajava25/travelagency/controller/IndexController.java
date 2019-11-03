package pl.sdajava25.travelagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sdajava25.travelagency.service.ContinentService;
import pl.sdajava25.travelagency.service.TripDetailsService;

@Controller
public class IndexController {

    private TripDetailsService tripDetailsService;
    private ContinentService continentService;

    @Autowired
    public IndexController(TripDetailsService tripDetailsService, ContinentService continentService) {
        this.tripDetailsService = tripDetailsService;
        this.continentService = continentService;
    }

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("allTrips", tripDetailsService.getAllTrips());
        model.addAttribute("promotedTrips", tripDetailsService.getPromotedTrips());
        model.addAttribute("allContinents", continentService.getAllContinentsSortedByName());


        return "index";
    }
}
