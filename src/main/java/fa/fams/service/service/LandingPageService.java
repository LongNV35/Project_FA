package fa.fams.service.service;

import java.util.List;
import fa.fams.common.valueobjects.DashboardVo;

public interface LandingPageService {
  List<List<DashboardVo>> getDashboardVoByStatusLocation(String dashboardFor,
      String status, String location);

  List<String> getAllStatusByDashboard(String dashboardFor);
}
