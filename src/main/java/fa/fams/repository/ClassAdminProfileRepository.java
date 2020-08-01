package fa.fams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fa.fams.model.ClassAdminProfile;

@Repository
public interface ClassAdminProfileRepository
    extends JpaRepository<ClassAdminProfile, Integer> {
  ClassAdminProfile findByClassAdminProfileId(int id);
}
