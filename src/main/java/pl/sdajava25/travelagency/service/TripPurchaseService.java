package pl.sdajava25.travelagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.sdajava25.travelagency.model.TripDetails;
import pl.sdajava25.travelagency.model.TripPurchase;
import pl.sdajava25.travelagency.model.User;
import pl.sdajava25.travelagency.model.dto.TripPurchaseDto;
import pl.sdajava25.travelagency.repository.TripPurchaseRepository;


import java.util.List;
import java.util.Optional;

@Service
public class TripPurchaseService {

    private TripPurchaseRepository tripPurchaseRepository;
    private TripDetailsService tripDetailsService;
    private UserService userService;
    private Long id;

    public List<TripPurchase> getAllTripPurchase(){
        return tripPurchaseRepository.findAll();
    }

    public Page<TripPurchase> getAllTripPurchases(Pageable pageable){
        return tripPurchaseRepository.findAll(pageable);
    }

    public TripPurchase createPurchaseFromDto(Long tripId, TripPurchaseDto tripPurchaseDto) {
        Optional<TripDetails> foundTrip = tripDetailsService.getTripById(tripId);
        TripPurchase tripPurchase = new TripPurchase();
        User user = new User();
        user.setName(tripPurchaseDto.getName());
        user.setSurname(tripPurchaseDto.getSurname());


        user = userService.addNewUser(id, user);

        tripPurchase.setUser(user);
        tripPurchase.setNumberOfAdult(Integer.valueOf(tripPurchase.getNumberOfAdult()));
        tripPurchase.setNumberOfChildren(Integer.valueOf(tripPurchase.getNumberOfChildren()));

        foundTrip.ifPresent(tripPurchase::setTripDetails);

        if (foundTrip.isPresent()){
            foundTrip.get()
                    .setNumberOfAdult(foundTrip.get().getNumberOfAdult() - Integer.valueOf(tripPurchaseDto.getNumberOfAdults()));
            foundTrip.get()
                    .setNumberOfChildren(foundTrip.get().getNumberOfChildren() - Integer.valueOf(tripPurchaseDto.getNumberOfChildren()));
        }
        return tripPurchaseRepository.save(tripPurchase);

    }

    public Optional<TripPurchase> getTripPurchaseById(Long id){
        return tripPurchaseRepository.findById(id);
    }

    public Double calculateTripPurchaseFinances(Long tripPurchaseId){
        Optional<TripPurchase> foundTrip = getTripPurchaseById(tripPurchaseId);
        if (foundTrip.isPresent()){
            Integer numberOfChildren = foundTrip.get().getNumberOfChildren();
            double childrenPrice = foundTrip.get().getTripDetails().getPricePerChildren();

            Integer numberOfAdults = foundTrip.get().getNumberOfAdult();
            double adultPrice = foundTrip.get().getTripDetails().getPricePerAdult();

            Double amount = (numberOfChildren * childrenPrice) + (numberOfAdults * adultPrice);
            return amount;
        }
        return null;
    }
}
