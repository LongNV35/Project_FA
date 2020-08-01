package fa.fams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fa.fams.model.ClassAdmin;

@Repository
public interface ClassAdminRepository
    extends JpaRepository<ClassAdmin, Integer> {
  ClassAdmin findByClassAdminId(int id);
}
