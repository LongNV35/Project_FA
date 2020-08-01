package fa.fams.service.service;

import java.util.List;
import fa.fams.model.Location;

public interface LocationService {

  List<Location> findAll();

  Location findByLocationId(int id);

}