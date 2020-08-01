package fa.fams.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fa.fams.model.Trainee;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {
  @Query(value = "SELECT  c.skill, COUNT(c.candidate_profile_id) as value\r\n"
      + "FROM [FAMS_G2].candidate_profile c JOIN [FAMS_G2].trainee t on"
      + " t.candidate_profile_id= c.candidate_profile_id "
      + "JOIN [FAMS_G2].candidate ca on ca.candidate_id= c.candidate_id \r\n"
      + "JOIN [FAMS_G2].location l on ca.location_id= l.location_id JOIN "
      + "[FAMS_G2].status s ON t.status_id= s.status_id\r\n"
      + "WHERE Upper(s.status_name) like Upper(?1) and Upper(l.location_name)"
      + " like Upper(?2) GROUP BY c.skill", nativeQuery = true)
  public List<Object[]> countTraineeByStatusLocation(String status,
      String location);

  Trainee findTraineeByCandidateProfileCandidateProfileId(
      int candidateProfileId);
}
