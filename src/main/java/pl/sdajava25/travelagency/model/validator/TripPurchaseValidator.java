package pl.sdajava25.travelagency.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.sdajava25.travelagency.model.dto.TripPurchaseDto;

@Component
public class TripPurchaseValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return TripPurchaseDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TripPurchaseDto trip = (TripPurchaseDto) o;
        Integer numberOfAdults = Integer.valueOf(trip.getNumberOfAdults());
        Integer adultsAvailable = Integer.valueOf(trip.getAdultsAvailable());
        Integer numberOfChildren = Integer.valueOf(trip.getNumberOfChildren());
        Integer childrenAvailable = Integer.valueOf(trip.getChildrenAvailable());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Name", "NotEmpty", "Required field!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Surname", "NotEmpty", "Required field!");

//        if (trip.getChildrenQuantity() == null || childrenQuantity < 0) {
//            errors.rejectValue("childrenQuantity", "value.positive", "Pole nie może być wartością ujemną.");
//        }
        if (!trip.getNumberOfAdults().matches("^\\d+$")) {
            errors.rejectValue("numberOfAdults", "not.int");
        }
        if (!trip.getNumberOfChildren().matches("^\\d+$")) {
            errors.rejectValue("numberOfChildren", "not.int");
        }
        if (numberOfAdults > adultsAvailable) {
            errors.rejectValue("numberOfAdults", "not.enough.available");
        }
        if (numberOfChildren > childrenAvailable) {
            errors.rejectValue("numberOfChildren", "not.enough.available");
        }
    }


}
