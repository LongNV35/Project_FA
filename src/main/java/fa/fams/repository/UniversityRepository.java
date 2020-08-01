package fa.fams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fa.fams.model.University;

@Repository
public interface UniversityRepository
    extends JpaRepository<University, Integer> {
  University findByUniversityId(int id);
}