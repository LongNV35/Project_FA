package fa.fams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fa.fams.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

  Status findStatusByStatusName(String statusName);

}
