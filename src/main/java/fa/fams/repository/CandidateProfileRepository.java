package fa.fams.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fa.fams.model.CandidateProfile;

@Repository
public interface CandidateProfileRepository
    extends JpaRepository<CandidateProfile, Integer> {
  @Query(value = "select  c.skill, COUNT(c.candidate_profile_id) as value"
      + " from [FAMS_G2].candidate_profile c join [FAMS_G2].candidate ca "
      + "on ca.candidate_id= c.candidate_id join [FAMS_G2].location l "
      + "on ca.location_id= l.location_id where Upper(ca.status) "
      + "like Upper(?1) and Upper(l.location_name) like Upper(?2)"
      + " group by c.skill", nativeQuery = true)
  public List<Object[]> countCandidateByStatusLocation(String status,
      String location);

  List<CandidateProfile> findByAccountStartsWith(String account);

  CandidateProfile findByCandidateProfileId(int id);
  
}
