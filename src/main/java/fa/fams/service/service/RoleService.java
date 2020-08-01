package fa.fams.service.service;

import fa.fams.model.Role;

public interface RoleService {

  Role findByCode(String code) throws Exception;

  boolean createRole(Role role) throws Exception;

}
