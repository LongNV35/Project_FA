package fa.fams.service.service;

import fa.fams.model.User;

public interface UserService {

  boolean createUser(User user) throws Exception;

  User findByUserName(String userName) throws Exception;

  User findById(int id);
}
