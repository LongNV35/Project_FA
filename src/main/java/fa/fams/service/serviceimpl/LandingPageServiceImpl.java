package fa.fams.service.serviceimpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.fams.common.utility.Constant;
import fa.fams.common.valueobjects.DashboardVo;
import fa.fams.service.service.LandingPageService;

@Service
public class LandingPageServiceImpl implements LandingPageService {
  private List<List<DashboardVo>> listDashboardVos;

  private List<String> listNameOfStatus;

  @Autowired
  private CandidateProfileServiceImpl candidateProfileService;

  @Autowired
  private ClassBathServiceImpl classBathService;

  @Autowired
  private TraineeServiceImpl traineeService;

  @Autowired
  private StatusServiceImpl statusService;

  /**
   * the function to get lists of DashboardVo by dashboardFor, status and
   * location.
   * 
   * @param dashboardFor - type of dash board
   * @param status       - status of Status dropdown
   * @param location     - location of Location dropdown
   * @return - return lists DashboardVo
   */
  @Override
  public List<List<DashboardVo>> getDashboardVoByStatusLocation(
      String dashboardFor, String status, String location) {
    if ("Candidate".equalsIgnoreCase(dashboardFor)) {
      listDashboardVos = candidateProfileService
          .getListsCountCandidateByStatusLocation(status, location);
    } else if ("Class".equalsIgnoreCase(dashboardFor)) {
      listDashboardVos = classBathService
          .getListsCountClassByStatusLocation(status, location);
    } else if ("Trainee".equalsIgnoreCase(dashboardFor)) {
      listDashboardVos = traineeService
          .getListsCountTraineeByStatusLocation(status, location);
    }
    return listDashboardVos;
  }

  /**
   * the function to get all status by dashboardFor.
   * 
   * @param dashboardFor - type of dash board
   * @return - list of status's name
   */
  @Override
  public List<String> getAllStatusByDashboard(String dashboardFor) {
    if ("Candidate".equalsIgnoreCase(dashboardFor)) {
      listNameOfStatus = Arrays.asList(Constant.listStatusOfCandidate);
    } else if ("Class".equalsIgnoreCase(dashboardFor)) {
      listNameOfStatus = Arrays.asList(Constant.listStatusOfClass);
    } else if ("Trainee".equalsIgnoreCase(dashboardFor)) {
      listNameOfStatus = statusService.findAllStatus().stream()
          .map(x -> x.getStatusName()).collect(Collectors.toList());
    }
    return listNameOfStatus;
  }
}
