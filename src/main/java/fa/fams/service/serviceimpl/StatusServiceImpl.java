package fa.fams.service.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.fams.model.Status;
import fa.fams.repository.StatusRepository;
import fa.fams.service.service.StatusService;

/**
 * this class is service of status.
 * 
 * @author MinhLV6
 *
 */
@Service
public class StatusServiceImpl implements StatusService {
  
  @Autowired
  private StatusRepository statusRepository;

  /**
   * the function to find all status.
   */
  @Override
  public List<Status> findAllStatus() {
    return statusRepository.findAll();
  }
}
