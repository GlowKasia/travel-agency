package pl.sdajava25.travelagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sdajava25.travelagency.model.City;
import pl.sdajava25.travelagency.service.CityService;

@Controller
public class CityController {

    private CityService cityService;

    @Autowired
    public CityController (CityService cityService){
        this.cityService = cityService;
    }

    @GetMapping("/admin/add-city/{countryId}")
    public String addCity(@PathVariable("countryId") Long countryId,
                          Model model){
        model.addAttribute("newCity", new City());
        return "city/add-city";
    }

    @PostMapping("/admin/add-city/{countryId}")
    public String addCityPost(@PathVariable("countryId") Long countryId,
                              @ModelAttribute("newCity") City city){
        cityService.addNewCity(countryId, city);
        return "redirect:/city/list/{countryId}";
    }
}
