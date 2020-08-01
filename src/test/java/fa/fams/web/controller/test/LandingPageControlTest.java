package fa.fams.web.controller.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fa.fams.common.valueobjects.DashboardVo;
import fa.fams.service.serviceimpl.LandingPageServiceImpl;
import fa.fams.web.controller.LandingPageController;

@SpringBootTest
@AutoConfigureMockMvc
class LandingPageControlTest {

  @Mock
  LandingPageServiceImpl landingpageService;

  private MockMvc mockMvc;

  @InjectMocks
  private LandingPageController landingPageController;

  @BeforeEach
  void setUp() throws Exception {
    // Process mock annotation
    MockitoAnnotations.initMocks(this);
    // Setup Spring test in standalone mode
    this.mockMvc = MockMvcBuilders.standaloneSetup(landingPageController)
        .build();
  }

  protected String mapToJson(Object object) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(object);
  }

  protected <T> T mapFromJson(String json, Class<T> clazz)
      throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(json, clazz);
  }

  /**
   * test when database is empty, status="All", location="All",
   * dashboardFor="Candidate".
   */
  @Test
  void testDisplayLandingPageAsTableUC1() throws Exception {
    List<List<DashboardVo>> listsVo = new ArrayList<List<DashboardVo>>();
    List<DashboardVo> listWithNew = new ArrayList<DashboardVo>();
    listWithNew.add(new DashboardVo("New", "Empty", 0));
    List<DashboardVo> listWithTestPass = new ArrayList<DashboardVo>();
    listWithTestPass.add(new DashboardVo("Test- Pass", "Empty", 0));
    List<DashboardVo> listWithTestFail = new ArrayList<DashboardVo>();
    listWithTestFail.add(new DashboardVo("Test- Fail", "Empty", 0));
    List<DashboardVo> listWithInterviewPass = new ArrayList<DashboardVo>();
    listWithInterviewPass.add(new DashboardVo("Interview- Pass", "Empty", 0));
    List<DashboardVo> listWithInterviewFail = new ArrayList<DashboardVo>();
    listWithInterviewFail.add(new DashboardVo("Interview- Fail", "Empty", 0));
    listsVo.add(listWithNew);
    listsVo.add(listWithTestPass);
    listsVo.add(listWithTestFail);
    listsVo.add(listWithInterviewPass);
    listsVo.add(listWithInterviewFail);
    when(landingpageService.getDashboardVoByStatusLocation("Candidate", "All",
        "All")).thenReturn(listsVo);
    this.mockMvc
        .perform(
            get("/landingpage/content-table/Candidate").param("location", "All")
                .param("status", "All").param("dashboardFor", "Candidate"))
        .andExpect(status().isOk())
        .andExpect(model().attribute("listDashboardVos", listsVo))
        .andExpect(view().name("include/landing-page-table"));
  }

  /**
   * test when database has 5 records, status="All", location="All",
   * dashboardFor="Candidate".
   */
  @Test
  void testDisplayLandingPageAsTableUC2() throws Exception {
    List<List<DashboardVo>> listsVo = getListsVotUC2();
    when(landingpageService.getDashboardVoByStatusLocation("Candidate", "All",
        "All")).thenReturn(listsVo);
    this.mockMvc
        .perform(
            get("/landingpage/content-table/Candidate").param("location", "All")
                .param("status", "All").param("dashboardFor", "Candidate"))
        .andExpect(status().isOk())
        .andExpect(model().attribute("listDashboardVos", listsVo))
        .andExpect(view().name("include/landing-page-table"));
  }

  /**
   * this function to get lists DashboardVo will return by service in
   * testDisplayLandingPageAsTableUC2 when status= "All", location="All",
   * dashboardFor="Candidate".
   * 
   * @return - listsVo
   */
  private ArrayList<List<DashboardVo>> getListsVotUC2() {
    ArrayList<List<DashboardVo>> listExpected;
    listExpected = new ArrayList<List<DashboardVo>>();
    List<DashboardVo> listWithNew = new ArrayList<DashboardVo>();
    listWithNew.add(new DashboardVo("New", "Java", 4));
    List<DashboardVo> listWithTestPass = new ArrayList<DashboardVo>();
    listWithTestPass.add(new DashboardVo("Test- Pass", "Empty", 0));
    List<DashboardVo> listWithTestFail = new ArrayList<DashboardVo>();
    listWithTestFail.add(new DashboardVo("Test- Fail", "Empty", 0));
    List<DashboardVo> listWithInterviewPass = new ArrayList<DashboardVo>();
    listWithInterviewPass.add(new DashboardVo("Interview- Pass", "Java", 1));
    List<DashboardVo> listWithInterviewFail = new ArrayList<DashboardVo>();
    listWithInterviewFail.add(new DashboardVo("Interview- Fail", "Empty", 0));
    listExpected.add(listWithNew);
    listExpected.add(listWithTestPass);
    listExpected.add(listWithTestFail);
    listExpected.add(listWithInterviewPass);
    listExpected.add(listWithInterviewFail);
    return listExpected;
  }

  /**
   * test when database is empty, status="", location="All",
   * dashboardFor="Candidate".
   */
  @Test
  void testDisplayLandingPageAsTableUC3() throws Exception {
    List<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
    List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
    listVoInExpected.add(new DashboardVo("", "Empty", 0));
    listExpected.add(listVoInExpected);
    when(landingpageService.getDashboardVoByStatusLocation("Candidate", "",
        "All")).thenReturn(listExpected);
    this.mockMvc
        .perform(
            get("/landingpage/content-table/Candidate").param("location", "All")
                .param("status", "").param("dashboardFor", "Candidate"))
        .andExpect(status().isOk())
        .andExpect(model().attribute("listDashboardVos", listExpected))
        .andExpect(view().name("include/landing-page-table"));
  }

  /**
   * test when database is empty, status="All", location="",
   * dashboardFor="Candidate".
   */
  @Test
  void testDisplayLandingPageAsTableUC4() throws Exception {
    List<List<DashboardVo>> listsVo = new ArrayList<List<DashboardVo>>();
    List<DashboardVo> listWithNew = new ArrayList<DashboardVo>();
    listWithNew.add(new DashboardVo("New", "Empty", 0));
    List<DashboardVo> listWithTestPass = new ArrayList<DashboardVo>();
    listWithTestPass.add(new DashboardVo("Test- Pass", "Empty", 0));
    List<DashboardVo> listWithTestFail = new ArrayList<DashboardVo>();
    listWithTestFail.add(new DashboardVo("Test- Fail", "Empty", 0));
    List<DashboardVo> listWithInterviewPass = new ArrayList<DashboardVo>();
    listWithInterviewPass.add(new DashboardVo("Interview- Pass", "Empty", 0));
    List<DashboardVo> listWithInterviewFail = new ArrayList<DashboardVo>();
    listWithInterviewFail.add(new DashboardVo("Interview- Fail", "Empty", 0));
    listsVo.add(listWithNew);
    listsVo.add(listWithTestPass);
    listsVo.add(listWithTestFail);
    listsVo.add(listWithInterviewPass);
    listsVo.add(listWithInterviewFail);
    when(landingpageService.getDashboardVoByStatusLocation("Candidate", "All",
        "")).thenReturn(listsVo);
    this.mockMvc
        .perform(
            get("/landingpage/content-table/Candidate").param("location", "")
                .param("status", "All").param("dashboardFor", "Candidate"))
        .andExpect(status().isOk())
        .andExpect(model().attribute("listDashboardVos", listsVo))
        .andExpect(view().name("include/landing-page-table"));
  }

  /**
   * test when database is empty, status="All", location="All",
   * dashboardFor="Candidate".
   */
  @Test
  void testDisplayLandingPageAsTableUC5() throws Exception {
    List<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
    when(landingpageService.getDashboardVoByStatusLocation("", "All", "All"))
        .thenReturn(listExpected);
    this.mockMvc
        .perform(
            get("/landingpage/content-table/Candidate").param("location", "All")
                .param("status", "All").param("dashboardFor", ""))
        .andExpect(status().isOk())
        .andExpect(model().attribute("listDashboardVos", listExpected))
        .andExpect(view().name("include/landing-page-table"));
  }
}
