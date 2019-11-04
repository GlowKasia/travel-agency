package pl.sdajava25.travelagency.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sdajava25.travelagency.model.TripDetails;
import pl.sdajava25.travelagency.model.TripPurchase;
import pl.sdajava25.travelagency.model.TripPurchaseValidator;
import pl.sdajava25.travelagency.model.dto.PageForm;
import pl.sdajava25.travelagency.model.dto.TripPurchaseDto;
import pl.sdajava25.travelagency.service.PurchaseFinanceDetailsService;
import pl.sdajava25.travelagency.service.TripDetailsService;
import pl.sdajava25.travelagency.service.TripPurchaseService;

@Controller
public class TripPurchaseController {

    private TripPurchaseService tripPurchaseService;
    private TripDetailsService tripDetailsService;
    private PurchaseFinanceDetailsService purchaseFinanceDetailsService;
    private TripPurchaseValidator tripPurchaseValidator;


    @GetMapping("/but-a-trip/{tripId}")
    public String buyATrip(@PathVariable ("tripId") Long tripId,
                           Model model){
        TripDetails trip = tripDetailsService.getTripById(tripId).get();
        TripPurchaseDto dto = new TripPurchaseDto();

        model.addAttribute("trip", trip);
        model.addAttribute("newTripPurchase", dto);
        dto.setAdultsAvailable(String.valueOf(trip.getNumberOfAdult()));
        dto.setChildrenAvailable(String.valueOf(trip.getNumberOfChildren()));
        dto.setNumberOfAdults("2");
        dto.setNumberOfChildren("0");
        return "trip-purchase/buy";
    }

    @PostMapping("/buy-a-trip/{tripId}")
    public String buyATripPost(@PathVariable ("tripId") Long tripId,
                               @ModelAttribute("newTripPurchase") TripPurchaseDto tripPurchaseDto,
                               Model model, BindingResult bindingResult){
        TripDetails trip = tripDetailsService.getTripById(tripId).get();
        tripPurchaseValidator.validate(tripPurchaseDto, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("error", bindingResult);
            return "trip_purchase/buy";
        }
        TripPurchase purchase = tripPurchaseService.createPurchaseFromDto(tripId, tripPurchaseDto);
        model.addAttribute("purchaseId", purchase.getId());
        return "redirect:/purchase/purchase-summary/" + purchase.getId();

    }

    @GetMapping("/purchase/purchase-summary/{purchaseId}")
    public String showYourPurchase(@PathVariable("purchaseId") Long purchaseId,
                                   Model model){
        model.addAttribute("newTripPurchase", tripPurchaseService.getTripPurchaseById(purchaseId).get());
        model.addAttribute("amountOfMonay", tripPurchaseService.calculateTripPurchaseFinances(purchaseId));
        model.addAttribute("financeDetails", purchaseFinanceDetailsService.save(tripPurchaseService.getTripPurchaseById(purchaseId).get()));
        return "trip-purchase/purchase-summary";
    }

    @PostMapping("/purchase/purchase-summary")
    public String showYourPurchasePost(){
        return "trip-purchase/purchase-summary";
    }

    @GetMapping("/admin/purchase-list")
    public String getAllPurchases(Model model){
        model.addAttribute("pageForm", new PageForm());
        model.addAttribute("purchaseList", tripPurchaseService.getAllTripPurchase());
        return "trip-purchase/list";
    }

    @PostMapping("/admin/purchase-list")
    public String getAllPurchasesPost(@ModelAttribute("pageForm") PageForm pageForm, Model model){
        Pageable pageable = PageRequest.of(pageForm.getPage(),
                pageForm.getSize(),
                pageForm.getSortOrder(),
                pageForm.getSortField());
        Page<TripPurchase> allTripPurchase = tripPurchaseService.getAllTripPurchases(pageable);
        model.addAttribute("purchaseList", allTripPurchase);
        return "/admin/purchase-list";
    }
}
