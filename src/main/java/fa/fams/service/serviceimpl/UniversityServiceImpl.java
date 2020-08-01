package fa.fams.service.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.fams.model.University;
import fa.fams.repository.UniversityRepository;
import fa.fams.service.service.UniversityService;

@Service
public class UniversityServiceImpl implements UniversityService {

  @Autowired
  private UniversityRepository universityRepository;

  @Override
  public List<University> findAll() {
    return universityRepository.findAll();
  }

  @Override
  public University save(University university) {
    return universityRepository.save(university);
  }

  @Override
  public University findByUniversityId(int id) {
    return universityRepository.findByUniversityId(id);
  }

}