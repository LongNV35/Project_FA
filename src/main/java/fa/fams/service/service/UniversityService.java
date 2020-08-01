package fa.fams.service.service;

import java.util.List;
import fa.fams.model.University;

public interface UniversityService {

  List<University> findAll();

  University save(University university);

  University findByUniversityId(int id);

}
