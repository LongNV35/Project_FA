package fa.fams.service.service;

import java.util.ArrayList;
import java.util.List;
import fa.fams.common.valueobjects.DashboardVo;
import fa.fams.model.Trainee;

public interface TraineeService {
  Trainee saveTrainee(Trainee trainee);

  List<DashboardVo> countTraineeByStatusLocation(String status,
      String location);

  ArrayList<List<DashboardVo>> getListsCountTraineeByStatusLocation(
      String status, String location);

}
