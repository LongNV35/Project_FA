package fa.fams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fa.fams.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  User findByUserName(String userName);

  User findById(int id);
}
