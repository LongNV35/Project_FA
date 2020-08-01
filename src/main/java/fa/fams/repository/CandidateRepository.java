package fa.fams.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import fa.fams.model.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

  @Transactional
  @Modifying
  @Query(value = "UPDATE FAMS_G2.candidate SET status = 'inactive'"
      + " WHERE candidate_id=:id and status != 'inactive'", nativeQuery = true)
  int updateStatusInActive(@Param("id") int id);

  Candidate findByCandidateID(int id);

}
