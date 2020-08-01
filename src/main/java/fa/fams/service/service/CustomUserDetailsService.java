package fa.fams.service.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import fa.fams.common.utility.Constant;
import fa.fams.model.Role;
import fa.fams.model.User;
import fa.fams.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    User user = userRepository.findByUserName(username);
    if (user == null) {
      throw new UsernameNotFoundException(Constant.USER_NOT_FOUND);
    }
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (Role role : user.getRoles()) {
      authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
    }
    return new org.springframework.security.core.userdetails.User(
        user.getUserName(), user.getPassword(), authorities);
  }
}
