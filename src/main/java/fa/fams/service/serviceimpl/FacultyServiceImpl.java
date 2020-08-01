package fa.fams.service.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.fams.model.Faculty;
import fa.fams.repository.FacultyRepository;
import fa.fams.service.service.FacultyService;

@Service
public class FacultyServiceImpl implements FacultyService {

  @Autowired
  private FacultyRepository facultyRepository;

  @Override
  public List<Faculty> findAll() {
    return facultyRepository.findAll();
  }

  @Override
  public Faculty save(Faculty faculty) {
    return facultyRepository.save(faculty);
  }

  @Override
  public Faculty findByFacultyId(int id) {
    return facultyRepository.findByFacultyId(id);
  }

}