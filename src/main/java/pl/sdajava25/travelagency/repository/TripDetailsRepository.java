package pl.sdajava25.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sdajava25.travelagency.model.TripDetails;
@Repository
public interface TripDetailsRepository extends JpaRepository<TripDetails, Long> {
}
