package fa.fams.service.service;

import java.util.List;
import fa.fams.model.Faculty;

public interface FacultyService {

  List<Faculty> findAll();

  Faculty save(Faculty faculty);

  Faculty findByFacultyId(int id);
}
