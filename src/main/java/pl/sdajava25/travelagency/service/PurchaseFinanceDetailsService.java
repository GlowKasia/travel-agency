package pl.sdajava25.travelagency.service;

import org.springframework.stereotype.Service;
import pl.sdajava25.travelagency.model.PurchaseFinanceDetails;
import pl.sdajava25.travelagency.model.TripPurchase;
import pl.sdajava25.travelagency.repository.PurchaseFinanceDetailsRepository;

@Service
public class PurchaseFinanceDetailsService {

    private PurchaseFinanceDetailsRepository financeDetailsRepository;

    public PurchaseFinanceDetails save(TripPurchase tripPurchase) {
        PurchaseFinanceDetails purchaseFinanceDetails = new PurchaseFinanceDetails();

        purchaseFinanceDetails.setAdultsCost(calculateAdultCost (tripPurchase));
        purchaseFinanceDetails.setChildrenCost(calculateChildrenCost (tripPurchase));
        purchaseFinanceDetails.setTotalCost(calculateTotalCost(tripPurchase));
        purchaseFinanceDetails.setTripPurchase(tripPurchase);
        tripPurchase.setFinanceDetails(purchaseFinanceDetails);
        return financeDetailsRepository.save(purchaseFinanceDetails);
    }

    private Double calculateChildrenCost(TripPurchase tripPurchase) {

        Integer numberOfChildren = tripPurchase.getNumberOfChildren();
        double childrenPrice = tripPurchase.getTripDetails().getPricePerChildren();
        return numberOfChildren * childrenPrice;
    }

    private Double calculateAdultCost(TripPurchase tripPurchase) {

        Integer numberOfAdults = tripPurchase.getNumberOfAdult();
        double adultPrice = tripPurchase.getTripDetails().getPricePerAdult();
        return numberOfAdults * adultPrice;
    }

    private Double calculateTotalCost(TripPurchase tripPurchase){
        return calculateAdultCost(tripPurchase) + calculateChildrenCost(tripPurchase);
    }
}
