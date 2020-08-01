package fa.fams.service.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.fams.model.Location;
import fa.fams.repository.LocationRepository;
import fa.fams.service.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

  @Autowired
  private LocationRepository locationEpository;

  @Override
  public List<Location> findAll() {
    return locationEpository.findAll();
  }

  @Override
  public Location findByLocationId(int id) {
    return locationEpository.findByLocationId(id);
  }

}