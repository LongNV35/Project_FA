package fa.fams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fa.fams.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
  Faculty findByFacultyId(int id);
}
