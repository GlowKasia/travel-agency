package pl.sdajava25.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sdajava25.travelagency.model.Airport;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    List<Airport> findAllByCity_Id(Long cityId);

    Airport findByNameContaining(String name);
}
