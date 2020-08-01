package fa.fams.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fa.fams.model.InterView;

@Repository
public interface InterViewRepository extends JpaRepository<InterView, Integer> {
  List<InterView> getAllByCandidateCandidateID(int candidateId);
}
