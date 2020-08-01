package fa.fams.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import fa.fams.model.ClassBath;

public interface ClassBathRepository extends JpaRepository<ClassBath, Integer> {
  @Query(name = "select c.* from [FAMS_G2].class_bath c "
      + "where Upper(class_name) like Upper('%?1%')", nativeQuery = true)
  List<ClassBath> findByClassName(String className);

  @Query(value = "SELECT c.skill, COUNT(c.class_id) as value "
      + "FROM [FAMS_G2].class_bath c join [FAMS_G2].location l"
      + " on c.location_id=l.location_id\r\n"
      + "WHERE Upper(c.status) like Upper(?1) and Upper(l.location_name)"
      + " like Upper(?2) GROUP BY c.skill", nativeQuery = true)
  public List<Object[]> countClassByStatusLocation(String status,
      String location);
}
