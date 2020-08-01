package fa.fams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fa.fams.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
  Location findByLocationId(int id);
}
