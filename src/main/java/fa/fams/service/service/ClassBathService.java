package fa.fams.service.service;

import java.util.ArrayList;
import java.util.List;
import fa.fams.common.valueobjects.DashboardVo;
import fa.fams.model.ClassBath;

public interface ClassBathService {
  List<ClassBath> getAll();

  List<ClassBath> findByClassName(String className);

  List<DashboardVo> countClassByStatusLocation(String status,
      String location);

  ArrayList<List<DashboardVo>> getListsCountClassByStatusLocation(
      String status, String location);
}
