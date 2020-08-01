package fa.fams.service.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.fams.common.utility.Constant;
import fa.fams.common.valueobjects.DashboardVo;
import fa.fams.model.ClassBath;
import fa.fams.repository.ClassBathRepository;
import fa.fams.service.service.ClassBathService;

@Service
public class ClassBathServiceImpl implements ClassBathService {
  private ArrayList<List<DashboardVo>> listDashboardVos;
  @Autowired
  private ClassBathRepository classBathRepository;

  @Override
  public List<ClassBath> getAll() {
    return classBathRepository.findAll();
  }

  @Override
  public List<ClassBath> findByClassName(String className) {
    return classBathRepository.findByClassName(className);
  }

  /**
   * the function to find DashboardVo by status and location.
   * 
   * @param status   - status of class bath
   * @param location - location of class bath
   * @return - return list DashboardVo
   */
  @Override
  public List<DashboardVo> countClassByStatusLocation(String status,
      String location) {
    if ("All".equalsIgnoreCase(location)) {
      location = "%%";
    }
    List<DashboardVo> listDashboardVo = new ArrayList<DashboardVo>();
    DashboardVo dashboardVo = null;
    for (Object[] ele : classBathRepository
        .countClassByStatusLocation(status, location)) {
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
   * the function to get all lists DashboardVo if status= "All", else get list
   * by status of class bath.
   * 
   * @param status   - status of class bath
   * @param location - location of class bath
   * @return - return list DashboardVo
   */
  @Override
  public ArrayList<List<DashboardVo>> getListsCountClassByStatusLocation(
      String status, String location) {
    listDashboardVos = new ArrayList<List<DashboardVo>>();
    if ("All".equalsIgnoreCase(status)) {
      List<String> listNameOfStatus = Arrays.asList(Constant.listStatusOfClass);
      listNameOfStatus.forEach(x -> listDashboardVos
          .add(countClassByStatusLocation(x, location)));
    } else {
      listDashboardVos.add(countClassByStatusLocation(status, location));
    }
    return listDashboardVos;
  }
}
