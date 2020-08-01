package fa.fams.service.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.fams.common.valueobjects.DashboardVo;
import fa.fams.model.Trainee;
import fa.fams.repository.TraineeRepository;
import fa.fams.service.service.StatusService;
import fa.fams.service.service.TraineeService;

@Service
public class TraineeServiceImpl implements TraineeService {
  private ArrayList<List<DashboardVo>> listDashboardVos;

  @Autowired
  private TraineeRepository traineeRepository;

  @Autowired
  private StatusService statusService;

  @Override
  public Trainee saveTrainee(Trainee trainee) {
    return traineeRepository.save(trainee);
  }

  /**
   * the function to find DashboardVo by status and location.
   * 
   * @param status   - status of trainee
   * @param location - location of trainee
   * @return - return list DashboardVo
   */
  @Override
  public List<DashboardVo> countTraineeByStatusLocation(String status,
      String location) {
    if ("All".equalsIgnoreCase(location)) {
      location = "%%";
    }
    List<DashboardVo> listDashboardVo = new ArrayList<DashboardVo>();
    DashboardVo dashboardVo = null;
    for (Object[] ele : traineeRepository.countTraineeByStatusLocation(status,
        location)) {
      dashboardVo = new DashboardVo(status, ele[0].toString(),
          Long.valueOf(ele[1].toString()));
      listDashboardVo.add(dashboardVo);
    }
    if (listDashboardVo.size() == 0) {
      listDashboardVo.add(new DashboardVo(status, "Empty", 0));
    }
    return listDashboardVo;
  }

  /**
   * the function to get all lists DashboardVo each status if status= "All",
   * else get list by status of trainee.
   * 
   * @param status   - status of trainee
   * @param location - location of trainee
   * @return - return lists DashboardVo
   */
  @Override
  public ArrayList<List<DashboardVo>> getListsCountTraineeByStatusLocation(
      String status, String location) {
    listDashboardVos = new ArrayList<List<DashboardVo>>();
    if ("All".equalsIgnoreCase(status)) {
      List<String> listNameOfStatus = statusService.findAllStatus().stream()
          .map(x -> x.getStatusName()).collect(Collectors.toList());
      if (!listNameOfStatus.isEmpty()) {
        listNameOfStatus.forEach(x -> listDashboardVos
            .add(countTraineeByStatusLocation(x, location)));
      }
    } else {
      listDashboardVos.add(countTraineeByStatusLocation(status, location));
    }
    return listDashboardVos;
  }

}
