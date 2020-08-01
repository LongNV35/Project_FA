package fa.fams.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.fams.model.ClassAdmin;
import fa.fams.repository.ClassAdminRepository;
import fa.fams.service.service.ClassAdminService;

@Service
public class ClassAdminServiceImpl implements ClassAdminService {

  @Autowired
  private ClassAdminRepository classAdminRepository;

  @Override
  public ClassAdmin findByClassAdminId(int id) {
    return classAdminRepository.findByClassAdminId(id);
  }

}
