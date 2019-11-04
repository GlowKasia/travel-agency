package pl.sdajava25.travelagency.controller;

import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sdajava25.travelagency.model.TripDetails;
import pl.sdajava25.travelagency.model.TripPurchase;
import pl.sdajava25.travelagency.model.dto.PageForm;
import pl.sdajava25.travelagency.model.dto.SearchTrip;
import pl.sdajava25.travelagency.model.dto.TripDto;
import pl.sdajava25.travelagency.service.AirportService;
import pl.sdajava25.travelagency.service.CityService;
import pl.sdajava25.travelagency.service.HotelService;
import pl.sdajava25.travelagency.service.TripDetailsService;

import java.awt.print.Pageable;

@Controller
public class TripDetailsController {

    private TripDetailsService tripDetailsService;
    private AirportService airportService;
    private HotelService hotelService;
    private CityService cityService;

    @Autowired
    public TripDetailsController(TripDetailsService tripDetailsService, AirportService airportService, HotelService hotelService, CityService cityService) {
        this.tripDetailsService = tripDetailsService;
        this.airportService = airportService;
        this.hotelService = hotelService;
        this.cityService = cityService;
    }

    @GetMapping("/admin/add-trip")
    public String addNewTrip(Model model) {
        model.addAttribute("newTrip", new TripDto());
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("airports", airportService.getAllAirports());
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "trip/add-trip";
    }

    @PostMapping("/admin/add-trip")
    public String addNewTripPost(@ModelAttribute("newTrip") TripDto tripDto) {
        tripDetailsService.createTripFromDto(tripDto);
        return "redirect:/trip/list";
    }

    @GetMapping("/admin/edit-trip/{tripId}")
    public String editTrip (@PathVariable("triId") Long id, Model model){
        TripDetails tripDetails = tripDetailsService.getTripById(id).get();
        if (tripDetailsService.getTripById(id).isPresent()){
            model.addAttribute("editedTrip", tripDetailsService.createTripDtoFromTrip(tripDetails));
            model.addAttribute("cities", cityService.getAllCities());
            model.addAttribute("airports", airportService.getAllAirports());
            model.addAttribute("hotels", hotelService.getAllHotels());
            return "trip/edit";
        }
        return "redirect:/admin/add-trip";
    }

    @PostMapping("/admin/edit-trip/{tripId}")
    public String editTripPost(@PathVariable("tripId") Long id,
                               @ModelAttribute("editedTrip") TripDto tripDto){
        TripDetails tripDetails = tripDetailsService.createTripFromDto(tripDto);
        tripDetails.setId(id);
        tripDetailsService.addNewTrip(tripDetails);
        return "redirect:/trip/details/{tripId}";
    }

    @GetMapping("/trip/details/{tripId}")
    public String showDetailsOfGivenTrip(@PathVariable("tripId") Long tripId,
                                         Model model){
        model.addAttribute("trip", tripDetailsService.getTripById(tripId).get());
        model.addAttribute("newPurchase", new TripPurchase());
        return "trip/details";
    }

    @GetMapping("/search")
    public String searchATrip(Model model){
        model.addAttribute("searchedTrip", new SearchTrip());
        return "trip/search";
    }

    @PostMapping("/search")
    public String searchATrip(@ModelAttribute("searchedTrip") SearchTrip searchTrip,
                              Model model){
        String param = searchTrip.getParam();
        String value = searchTrip.getValue();
        model.addAttribute("foundTrips", tripDetailsService.findATrip(param, value));

        return "trip/search-result";
    }

    @GetMapping("/trip/list")
    public String getAllTrips(Model model){
        model.addAttribute("pageForm" , new PageForm());
        model.addAttribute("tripsList", tripDetailsService.getAllTrips());
        return "trip/list";
    }

    @PostMapping("/trip/list")
    public String getAllTripsPost(@ModelAttribute ("pageForm") PageForm pageForm,
                                  Model model){
        Pageable pageable = (Pageable) PageRequest.of(pageForm.getPage(),
                pageForm.getSize(),
                pageForm.getSortOrder(),
                pageForm.getSortField()
        );
        List<TripDetails> tripDetailsList = (List<TripDetails>) tripDetailsService.getAllTrips(pageable);
        model.addAttribute("tripsList", tripDetailsList);

        return "trip/list";
    }

    @GetMapping("/trip/list/{countryId}")
    public String getAllTripsToGivenCountry(@PathVariable("countryId") Long countryId,
                                            @ModelAttribute("pageForm") PageForm pageForm,
                                            Model model){
        model.addAttribute("pageForm", new PageForm());
        model.addAttribute("tripsList", tripDetailsService.getAllTripsToGivenCountry(countryId));
        return "trip/list";
    }

    @PostMapping("/trip/list/{countryId}")
    public String getAllTripsToGivenCountryPost(@PathVariable("countryId") Long countryId,
                                                @ModelAttribute("pageForm") PageForm pageForm,
                                                Model model){
        Pageable pageable = (Pageable) PageRequest.of(pageForm.getPage(),
                pageForm.getSize(),
                pageForm.getSortOrder(),
                pageForm.getSortField()
        );
        List<TripDetails> tripsDetails = (List<TripDetails>) tripDetailsService.getAllTripsToGivenCountry(countryId, pageable);
        model.addAttribute("tripsList", tripsDetails);

        return "trip/list";
    }
}
