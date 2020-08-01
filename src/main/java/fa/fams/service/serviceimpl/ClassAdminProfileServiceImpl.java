package fa.fams.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.fams.model.ClassAdminProfile;
import fa.fams.repository.ClassAdminProfileRepository;
import fa.fams.service.service.ClassAdminProfileService;

@Service
public class ClassAdminProfileServiceImpl implements ClassAdminProfileService {

  @Autowired
  private ClassAdminProfileRepository classAdminProfileRepository;

  @Override
  public ClassAdminProfile findByClassAdminProfileId(int id) {
    return classAdminProfileRepository.findByClassAdminProfileId(id);
  }

}
