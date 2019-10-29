package pl.sdajava25.travelagency.repository;

import jdk.vm.ci.meta.Constant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentRepository extends JpaRepository<Constant, Long> {
}
