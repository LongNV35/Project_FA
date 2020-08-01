package fa.fams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fa.fams.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
  Role findByCode(String code);
}
