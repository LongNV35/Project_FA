package fa.fams.service.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.fams.model.Role;
import fa.fams.model.User;
import fa.fams.repository.UserRepository;
import fa.fams.service.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public boolean createUser(User user) {
    if (user.getRoles() == null || user.getRoles().isEmpty()) {
      List<Role> roles = new ArrayList<>();
      roles.add(new Role(1, "USER", "USER"));
      user.setRoles(roles);
    }
    return userRepository.save(user) != null;
  }

  @Override
  public User findByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }

  @Override
  public User findById(int id) {
    return userRepository.findById(id);
  }
}