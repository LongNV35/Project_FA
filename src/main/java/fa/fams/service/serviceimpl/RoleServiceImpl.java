package fa.fams.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.fams.model.Role;
import fa.fams.repository.RoleRepository;
import fa.fams.service.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public boolean createRole(Role role) throws Exception {
    return roleRepository.save(role) != null;
  }

  @Override
  public Role findByCode(String code) throws Exception {
    return roleRepository.findByCode(code);
  }

}
