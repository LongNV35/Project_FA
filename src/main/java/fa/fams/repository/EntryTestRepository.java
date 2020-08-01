package fa.fams.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fa.fams.model.EntryTest;

@Repository
public interface EntryTestRepository extends JpaRepository<EntryTest, Integer> {
  List<EntryTest> getAllByCandidateCandidateID(int candidateId);
  
  @SuppressWarnings("unchecked")
  EntryTest save(EntryTest entryTest);
}
