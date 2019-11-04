package pl.sdajava25.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sdajava25.travelagency.model.TripDetails;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripDetailsRepository extends JpaRepository<TripDetails, Long> {


    List<TripDetails> findAllByArrivalCity_Country_Id(Long countryId);
    List<TripDetails> findByIsPromotedLike(String isPromoted);
    List<TripDetails> findAllByArrivalCity_Country_Continent_Id(Long continentId);

    List<TripDetails> findAllByDepartureCity_NameContaining(String value);

    List<TripDetails> findAllByDepartureAirport_NameContaining(String value);

    List<TripDetails> findAllByArrivalCity_NameContaining(String value);

    List<TripDetails> findAllByArrivalAirport_NameContaining(String value);

    List<TripDetails> findAllByHotel_NameContaining(String value);

    List<TripDetails> findAllByStartDateContaining(LocalDate parse);

    List<TripDetails> findAllByEndDateContaining(LocalDate parse);

    List<TripDetails> findAllByDaysEquals(Integer valueOf);

    List<TripDetails> findAllByTypeOfBoardEquals(String value);

    List<TripDetails> findAllByPricePerAdultIsLessThanEqual(Double valueOf);

    List<TripDetails> findAllByPricePerChildrenIsLessThanEqual(Double valueOf);

    List<TripDetails> findAllByIsPromotedContaining(String value);

    List<TripDetails> findAllByNumberOfAdultsGreaterThanEqual(Integer valueOf);

    List<TripDetails> findAllByNumberOfChildrenGreaterThanEqual(Integer valueOf);
}
