package fa.fams.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import fa.fams.common.valueobjects.DashboardVo;
import fa.fams.model.Location;
import fa.fams.service.serviceimpl.LandingPageServiceImpl;
import fa.fams.service.serviceimpl.LocationServiceImpl;

/**
 * this class to control Landing page.
 * 
 * @author MinhLV6
 *
 */
@Controller
public class LandingPageController {

  private List<String> listNameOfStatus;

  private List<Location> listLocation;

  private List<List<DashboardVo>> listDashboardVos;

  @Autowired
  private LocationServiceImpl locationService;

  @Autowired
  private LandingPageServiceImpl landingpageService;

  /**
   * The function to update dropdown bar when change dashboardFor.
   * 
   * @param model        - param take off attribute
   * @param dashboardFor - param when change dashboardFor dropdown
   * @return - return landing page content
   */
  @RequestMapping(value = "/landingpage/{dashboardFor}")
  public String updateDropdown(Model model,
      @PathVariable(value = "dashboardFor") String dashboardFor) {

    listLocation = locationService.findAll();
    listNameOfStatus = landingpageService.getAllStatusByDashboard(dashboardFor);
    listDashboardVos = landingpageService
        .getDashboardVoByStatusLocation(dashboardFor, "All", "All");
    model.addAttribute("listNameOfStatus", listNameOfStatus);
    model.addAttribute("listLocation", listLocation);
    model.addAttribute("listDashboardVos", listDashboardVos);
    return "landing_page";
  }

  /**
   * The function to display landing page as a table and update when changes.
   * location, status
   * 
   * @param model        - param take off attribute
   * @param location     - param when change location dropdown
   * @param status       - param when change status dropdown
   * @param dashboardFor - param when change dashboardFor dropdown
   * 
   * @return - return landing page content
   */
  @RequestMapping(value = "/landingpage/content-table/{dashboardFor}")
  public String displayLandingPageAsTable(Model model,
      @RequestParam(name = "location") String location,
      @RequestParam(name = "status") String status,
      @PathVariable(value = "dashboardFor") String dashboardFor) {
    listDashboardVos = landingpageService
        .getDashboardVoByStatusLocation(dashboardFor, status, location);
    model.addAttribute("listDashboardVos", listDashboardVos);
    return "include/landing-page-table";
  }

  /**
   * The function to display landing page as a chart and update when changes.
   * location, status
   * 
   * @param location     - param when change location dropdown
   * @param status       - param when change status dropdown
   * @param dashboardFor - param when change dashboardFor dropdown
   * 
   * @return - return list data to create chart
   */
  @RequestMapping(value = "/landingpage/content-chart/{dashboardFor}")
  @ResponseBody
  public List<List<DashboardVo>> displayLandingPageAsChart(
      @RequestParam(name = "location") String location,
      @RequestParam(name = "status") String status,
      @PathVariable(value = "dashboardFor") String dashboardFor) {
    listDashboardVos = landingpageService
        .getDashboardVoByStatusLocation(dashboardFor, status, location);
    return listDashboardVos;
  }
}
