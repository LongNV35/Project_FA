package fa.fams.web.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;
import fa.fams.common.utility.DateUtils;
import fa.fams.model.Candidate;
import fa.fams.model.CandidateProfile;
import fa.fams.model.Channel;
import fa.fams.model.Location;
import fa.fams.model.Status;
import fa.fams.model.Trainee;
import fa.fams.repository.CandidateRepository;
import fa.fams.repository.StatusRepository;
import fa.fams.repository.TraineeRepository;
import fa.fams.service.serviceimpl.CandidateServiceImpl;
import fa.fams.web.controller.CandidateController;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CandidateControllerTest {

  @Mock
  private CandidateServiceImpl candidateServiceImpl;

  private MockMvc mockMvc;

  @InjectMocks
  private CandidateController candidateController;

  @Mock
  CandidateRepository candidateRepository;

  @Mock
  public StatusRepository statusRepository;

  @Mock
  public TraineeRepository traineeRepository;

  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    mockMvc = MockMvcBuilders.standaloneSetup(candidateController).build();
  }

  @Test
  void testGetUserNameCount() {
    fail("Not yet implemented");
  }

  /**
   * Author : LongNV35
   * 
   * Test normal case
   * 
   * @throws Exception
   */
  @Test
  public void testViewCandidateListing() throws Exception {

    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDate localDate1 = LocalDate.parse("2020-05-06", pattern);
    LocalDate localDate2 = LocalDate.parse("2020-04-02", pattern);
    LocalDate localDate3 = LocalDate.parse("2020-06-07", pattern);

    Channel channel = new Channel();
    channel.setChannelId(2);
    Channel channel2 = new Channel();
    channel.setChannelId(1);
    Channel channel3 = new Channel();
    channel.setChannelId(2);

    Location location = new Location();
    location.setLocationId(1);
    Location location2 = new Location();
    location.setLocationId(2);
    Location location3 = new Location();
    location.setLocationId(2);

    Candidate candidate = new Candidate(4, localDate1, "Interview - Fail",
        "abc", "abc", channel, location);
    Candidate candidate2 = new Candidate(5, localDate2, "Test - Pass", "abc",
        "abc", channel2, location2);
    Candidate candidate3 = new Candidate(6, localDate3, "Test - Fail", "abc",
        "abc", channel3, location3);

    List<Candidate> candidates = new ArrayList<>();
    candidates.add(candidate);
    candidates.add(candidate2);
    candidates.add(candidate3);

    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);

    this.mockMvc.perform(MockMvcRequestBuilders.get("/listcandidate"))
        .andExpect(MockMvcResultMatchers.status().isOk())
//      .andExpect(MockMvcResultMatchers.model().attribute("candidates", HasProperty.hasProperty(""))
        .andExpect(MockMvcResultMatchers.model().attributeExists("candidates"))
        .andExpect(MockMvcResultMatchers.view().name("candidate_listing"));

  }

  /**
   * test case nonal: database have 20 records, candidateId = 1.
   * 
   * @throws Exception
   */
  @Test
  void testViewCandidateProfile1() throws Exception {
    LocalDate current = LocalDate.now();
    Candidate candidatetest = new Candidate();
    List<Candidate> candidates = new ArrayList<>();
    candidatetest.setCandidateID(1);
    candidatetest.setApplicationDate(current);
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    candidateProfile.setDateOfBirth(current);
    candidateProfile.setGraduationYear(current);
    candidatetest.setCandidateProfile(candidateProfile);
    candidates.add(candidatetest);
    when(candidateServiceImpl.findByCandidateId(1)).thenReturn(candidatetest);
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/viewcandidate")
            .param("candidateId", 1 + ""))
        .andExpect(model().attribute("candidate", candidatetest))
        .andExpect(model().attribute("applicationDate",
            DateUtils.formatDate2(current)))
        .andExpect(
            model().attribute("dateOfBirth", DateUtils.formatDate2(current)))
        .andExpect(
            model().attribute("graduationYear", DateUtils.formatDate1(current)))
        .andExpect(MockMvcResultMatchers.view().name("view_candidate_profile"));
  }

  /**
   * test case abnomal: database have 20 records, candidateId = 0.
   */
  @Test
  void testViewCandidateProfile2() {
    LocalDate current = LocalDate.now();
    Candidate candidatetest = new Candidate();
    List<Candidate> candidates = new ArrayList<>();
    candidatetest.setCandidateID(1);
    candidatetest.setApplicationDate(current);
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    candidateProfile.setDateOfBirth(current);
    candidateProfile.setGraduationYear(current);
    candidatetest.setCandidateProfile(candidateProfile);
    candidates.add(candidatetest);
    when(candidateServiceImpl.findByCandidateId(0)).thenReturn(null);
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    try {
      this.mockMvc
          .perform(MockMvcRequestBuilders.get("/viewcandidate")
              .param("candidateId", 0 + ""))
          .andExpect(model().attribute("candidates", candidates))
          .andExpect(
              request().sessionAttribute("view_candidate_message", false))
          .andExpect(MockMvcResultMatchers.view().name("candidate_listing"));
    } catch (Exception e) {
      assertEquals(NestedServletException.class, e.getClass());
    }
  }

  /**
   * test case abnomal: database have 20 records, candidateId = 20.
   */
  @Test
  void testViewCandidateProfile3() throws Exception {
    LocalDate current = LocalDate.now();
    Candidate candidatetest = new Candidate();
    List<Candidate> candidates = new ArrayList<>();
    candidatetest.setCandidateID(20);
    candidatetest.setApplicationDate(current);
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(20);
    candidateProfile.setDateOfBirth(current);
    candidateProfile.setGraduationYear(current);
    candidatetest.setCandidateProfile(candidateProfile);
    candidates.add(candidatetest);
    when(candidateServiceImpl.findByCandidateId(20)).thenReturn(candidatetest);
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/viewcandidate")
            .param("candidateId", 20 + ""))
        .andExpect(model().attribute("candidate", candidatetest))
        .andExpect(model().attribute("applicationDate",
            DateUtils.formatDate2(current)))
        .andExpect(
            model().attribute("dateOfBirth", DateUtils.formatDate2(current)))
        .andExpect(
            model().attribute("graduationYear", DateUtils.formatDate1(current)))
        .andExpect(MockMvcResultMatchers.view().name("view_candidate_profile"));
  }

  /**
   * test case abnomal: database have 20 records, candidateId = 100.
   */
  @Test
  void testViewCandidateProfile4() {
    LocalDate current = LocalDate.now();
    Candidate candidatetest = new Candidate();
    List<Candidate> candidates = new ArrayList<>();
    candidatetest.setCandidateID(1);
    candidatetest.setApplicationDate(current);
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    candidateProfile.setDateOfBirth(current);
    candidateProfile.setGraduationYear(current);
    candidatetest.setCandidateProfile(candidateProfile);
    candidates.add(candidatetest);
    when(candidateServiceImpl.findByCandidateId(100)).thenReturn(null);
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    try {
      this.mockMvc
          .perform(MockMvcRequestBuilders.get("/viewcandidate")
              .param("candidateId", 100 + ""))
          .andExpect(model().attribute("candidates", candidates))
          .andExpect(
              request().sessionAttribute("view_candidate_message", false))
          .andExpect(MockMvcResultMatchers.view().name("candidate_listing"));
    } catch (Exception e) {
      assertEquals(NestedServletException.class, e.getClass());
    }
  }

  /**
   * test case abnomal: database have 20 records, candidateId = -100.
   */
  @Test
  void testViewCandidateProfile5() {
    LocalDate current = LocalDate.now();
    Candidate candidatetest = new Candidate();
    List<Candidate> candidates = new ArrayList<>();
    candidatetest.setCandidateID(2);
    candidatetest.setApplicationDate(current);
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(2);
    candidateProfile.setDateOfBirth(current);
    candidateProfile.setGraduationYear(current);
    candidatetest.setCandidateProfile(candidateProfile);
    candidates.add(candidatetest);
    when(candidateServiceImpl.findByCandidateId(-100)).thenReturn(null);
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    try {
      this.mockMvc
          .perform(MockMvcRequestBuilders.get("/viewcandidate")
              .param("candidateId", -100 + ""))
          .andExpect(model().attribute("candidates", candidates))
          .andExpect(
              request().sessionAttribute("view_candidate_message", false))
          .andExpect(MockMvcResultMatchers.view().name("candidate_listing"));
    } catch (Exception e) {
      assertEquals(NestedServletException.class, e.getClass());
    }
  }

  /**
   * test case abnomal: database not have any record, candidateId = 1.
   */
  @Test
  void testViewCandidateProfile6() {
    LocalDate current = LocalDate.now();
    Candidate candidatetest = new Candidate();
    List<Candidate> candidates = new ArrayList<>();
    candidatetest.setCandidateID(2);
    candidatetest.setApplicationDate(current);
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(2);
    candidateProfile.setDateOfBirth(current);
    candidateProfile.setGraduationYear(current);
    candidatetest.setCandidateProfile(candidateProfile);
    candidates.add(candidatetest);
    when(candidateServiceImpl.findByCandidateId(1)).thenReturn(null);
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    try {
      this.mockMvc
          .perform(MockMvcRequestBuilders.get("/viewcandidate")
              .param("candidateId", 1 + ""))
          .andExpect(model().attribute("candidates", candidates))
          .andExpect(
              request().sessionAttribute("view_candidate_message", false))
          .andExpect(MockMvcResultMatchers.view().name("candidate_listing"));
    } catch (Exception e) {
      assertEquals(NestedServletException.class, e.getClass());
    }
  }

  /**
   * test case abnomal: database not have any record, candidateId = 0.
   */
  @Test
  void testViewCandidateProfile7() {
    LocalDate current = LocalDate.now();
    Candidate candidatetest = new Candidate();
    List<Candidate> candidates = new ArrayList<>();
    candidatetest.setCandidateID(2);
    candidatetest.setApplicationDate(current);
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(2);
    candidateProfile.setDateOfBirth(current);
    candidateProfile.setGraduationYear(current);
    candidatetest.setCandidateProfile(candidateProfile);
    candidates.add(candidatetest);
    when(candidateServiceImpl.findByCandidateId(0)).thenReturn(null);
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    try {
      this.mockMvc
          .perform(MockMvcRequestBuilders.get("/viewcandidate")
              .param("candidateId", 0 + ""))
          .andExpect(model().attribute("candidates", candidates))
          .andExpect(
              request().sessionAttribute("view_candidate_message", false))
          .andExpect(MockMvcResultMatchers.view().name("candidate_listing"));
    } catch (Exception e) {
      assertEquals(NestedServletException.class, e.getClass());
    }
  }

  /**
   * test case abnomal: database not have any record, candidateId = 20.
   */
  @Test
  void testViewCandidateProfile8() {
    LocalDate current = LocalDate.now();
    Candidate candidatetest = new Candidate();
    List<Candidate> candidates = new ArrayList<>();
    candidatetest.setCandidateID(2);
    candidatetest.setApplicationDate(current);
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(2);
    candidateProfile.setDateOfBirth(current);
    candidateProfile.setGraduationYear(current);
    candidatetest.setCandidateProfile(candidateProfile);
    candidates.add(candidatetest);
    when(candidateServiceImpl.findByCandidateId(20)).thenReturn(null);
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    try {
      this.mockMvc
          .perform(MockMvcRequestBuilders.get("/viewcandidate")
              .param("candidateId", 20 + ""))
          .andExpect(model().attribute("candidates", candidates))
          .andExpect(
              request().sessionAttribute("view_candidate_message", false))
          .andExpect(MockMvcResultMatchers.view().name("candidate_listing"));
    } catch (Exception e) {
      assertEquals(NestedServletException.class, e.getClass());
    }
  }

  /**
   * test case abnomal: database not have any record, candidateId = 100.
   */
  @Test
  void testViewCandidateProfile9() {
    LocalDate current = LocalDate.now();
    Candidate candidatetest = new Candidate();
    List<Candidate> candidates = new ArrayList<>();
    candidatetest.setCandidateID(2);
    candidatetest.setApplicationDate(current);
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(2);
    candidateProfile.setDateOfBirth(current);
    candidateProfile.setGraduationYear(current);
    candidatetest.setCandidateProfile(candidateProfile);
    candidates.add(candidatetest);
    when(candidateServiceImpl.findByCandidateId(100)).thenReturn(null);
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    try {
      this.mockMvc
          .perform(MockMvcRequestBuilders.get("/viewcandidate")
              .param("candidateId", 100 + ""))
          .andExpect(model().attribute("candidates", candidates))
          .andExpect(
              request().sessionAttribute("view_candidate_message", false))
          .andExpect(MockMvcResultMatchers.view().name("candidate_listing"));
    } catch (Exception e) {
      assertEquals(NestedServletException.class, e.getClass());
    }
  }

  /**
   * test case abnomal: database not have any record, candidateId = -100.
   */
  @Test
  void testViewCandidateProfile10() {
    LocalDate current = LocalDate.now();
    Candidate candidatetest = new Candidate();
    List<Candidate> candidates = new ArrayList<>();
    candidatetest.setCandidateID(2);
    candidatetest.setApplicationDate(current);
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(2);
    candidateProfile.setDateOfBirth(current);
    candidateProfile.setGraduationYear(current);
    candidatetest.setCandidateProfile(candidateProfile);
    candidates.add(candidatetest);
    when(candidateServiceImpl.findByCandidateId(-100)).thenReturn(null);
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    try {
      this.mockMvc
          .perform(MockMvcRequestBuilders.get("/viewcandidate")
              .param("candidateId", -100 + ""))
          .andExpect(model().attribute("candidates", candidates))
          .andExpect(
              request().sessionAttribute("view_candidate_message", false))
          .andExpect(MockMvcResultMatchers.view().name("candidate_listing"));
    } catch (Exception e) {
      assertEquals(NestedServletException.class, e.getClass());
    }
  }

  /**
   * test case nomal: candidate is not tranferred, have role, candidateId = 1,
   * level not null.
   * 
   * @throws Exception
   */
  @Test
  void testTranferCandidate1() throws Exception {
    LocalDate current = LocalDate.now();
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_ADMIN");
    Trainee trainee = null;
    Status status = new Status();
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    candidateProfile.setGraduationYear(current);
    candidateProfile.setDateOfBirth(current);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(1);
    candidate.setApplicationDate(current);
    candidate.setStatus("Interview - Pass");
    candidate.setCandidateProfile(candidateProfile);
    List<Candidate> candidates = new ArrayList<>();
    candidates.add(candidate);
    String level = "FA";
    when(candidateServiceImpl.findByCandidateId(1)).thenReturn(candidate);
    when(candidateServiceImpl.tranferCandidate(1, roles, level))
        .thenReturn("success");
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post("/tranfer").param("candidateId", 1 + "")
                .param("level", "FA").sessionAttr("roles", roles))
        .andExpect(model().attribute("candidates", candidates))
        .andExpect(request().sessionAttribute("view_candidate_message", true))
        .andExpect(MockMvcResultMatchers.view().name("candidate_listing"));
  }

  /**
   * test case abnomal: candidate is not tranferred, have role, candidateId = 0,
   * level not null.
   */
  @Test
  void testTranferCandidate2() {
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_ADMIN");
    Trainee trainee = null;
    Status status = new Status();
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(1);
    candidate.setStatus("Interview - Pass");
    candidate.setCandidateProfile(candidateProfile);
    List<Candidate> candidates = new ArrayList<>();
    candidates.add(candidate);
    String level = "FA";
    LocalDate current = LocalDate.now();
    when(candidateServiceImpl.findByCandidateId(1)).thenReturn(candidate);
    when(candidateServiceImpl.tranferCandidate(1, roles, level))
        .thenReturn("success");
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    try {
      this.mockMvc
          .perform(MockMvcRequestBuilders.post("/tranfer")
              .param("candidateId", 0 + "").param("level", "FA")
              .sessionAttr("roles", roles))
          .andExpect(model().attribute("candidate", candidate))
          .andExpect(model().attribute("applicationDate",
              DateUtils.formatDate2(current)))
          .andExpect(
              model().attribute("dateOfBirth", DateUtils.formatDate2(current)))
          .andExpect(model().attribute("graduationYear",
              DateUtils.formatDate1(current)))
          .andExpect(request().sessionAttribute("tranfer_message", true))
          .andExpect(
              MockMvcResultMatchers.view().name("view_candidate_profile"));
    } catch (Exception e) {
      assertEquals(NestedServletException.class, e.getClass());
    }
  }

  /**
   * test case abnomal: candidate is not tranferred, have role, candidateId =
   * 100, level not null.
   */
  @Test
  void testTranferCandidate3() {
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_ADMIN");
    Trainee trainee = null;
    Status status = new Status();
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(1);
    candidate.setStatus("Interview - Pass");
    candidate.setCandidateProfile(candidateProfile);
    List<Candidate> candidates = new ArrayList<>();
    candidates.add(candidate);
    String level = "FA";
    LocalDate current = LocalDate.now();
    when(candidateServiceImpl.findByCandidateId(1)).thenReturn(candidate);
    when(candidateServiceImpl.tranferCandidate(1, roles, level))
        .thenReturn("success");
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    try {
      this.mockMvc
          .perform(MockMvcRequestBuilders.post("/tranfer")
              .param("candidateId", 100 + "").param("level", "FA")
              .sessionAttr("roles", roles))
          .andExpect(model().attribute("candidate", candidate))
          .andExpect(model().attribute("applicationDate",
              DateUtils.formatDate2(current)))
          .andExpect(
              model().attribute("dateOfBirth", DateUtils.formatDate2(current)))
          .andExpect(model().attribute("graduationYear",
              DateUtils.formatDate1(current)))
          .andExpect(request().sessionAttribute("tranfer_message", true))
          .andExpect(
              MockMvcResultMatchers.view().name("view_candidate_profile"));
    } catch (Exception e) {
      assertEquals(NestedServletException.class, e.getClass());
    }
  }

  /**
   * test case abnomal: candidate is not tranferred, have role, candidateId =
   * -100, level not null.
   */
  @Test
  void testTranferCandidate4() {
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_ADMIN");
    Trainee trainee = null;
    Status status = new Status();
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(1);
    candidate.setStatus("Interview - Pass");
    candidate.setCandidateProfile(candidateProfile);
    List<Candidate> candidates = new ArrayList<>();
    candidates.add(candidate);
    String level = "FA";
    LocalDate current = LocalDate.now();
    when(candidateServiceImpl.findByCandidateId(1)).thenReturn(candidate);
    when(candidateServiceImpl.tranferCandidate(1, roles, level))
        .thenReturn("success");
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    try {
      this.mockMvc
          .perform(MockMvcRequestBuilders.post("/tranfer")
              .param("candidateId", -100 + "").param("level", "FA")
              .sessionAttr("roles", roles))
          .andExpect(model().attribute("candidate", candidate))
          .andExpect(model().attribute("applicationDate",
              DateUtils.formatDate2(current)))
          .andExpect(
              model().attribute("dateOfBirth", DateUtils.formatDate2(current)))
          .andExpect(model().attribute("graduationYear",
              DateUtils.formatDate1(current)))
          .andExpect(request().sessionAttribute("tranfer_message", true))
          .andExpect(
              MockMvcResultMatchers.view().name("view_candidate_profile"));
    } catch (Exception e) {
      assertEquals(NestedServletException.class, e.getClass());
    }
  }

  /**
   * test case abnomal: candidate is not tranferred, not have role, candidateId
   * = 1, level null.
   */
  @Test
  void testTranferCandidate9() {
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_USER");
    Trainee trainee = null;
    Status status = new Status();
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(2);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(2);
    candidate.setStatus("Interview - Pass");
    candidate.setCandidateProfile(candidateProfile);
    List<Candidate> candidates = new ArrayList<>();
    candidates.add(candidate);
    String level = "FA";
    when(candidateServiceImpl.findByCandidateId(1)).thenReturn(candidate);
    when(candidateServiceImpl.tranferCandidate(1, roles, level))
        .thenReturn("success");
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    LocalDate current = LocalDate.now();
    try {
      this.mockMvc
          .perform(MockMvcRequestBuilders.post("/tranfer")
              .param("candidateId", 1 + "").param("level", "null")
              .sessionAttr("roles", roles))
          .andExpect(model().attribute("candidate", candidate))
          .andExpect(model().attribute("applicationDate",
              DateUtils.formatDate2(current)))
          .andExpect(
              model().attribute("dateOfBirth", DateUtils.formatDate2(current)))
          .andExpect(model().attribute("graduationYear",
              DateUtils.formatDate1(current)))
          .andExpect(request().sessionAttribute("tranfer_message", true))
          .andExpect(
              MockMvcResultMatchers.view().name("view_candidate_profile"));
    } catch (Exception e) {
      assertEquals(NestedServletException.class, e.getClass());
    }
  }

  /**
   * test case abnomal: candidate is not tranferred, not have role, candidateId
   * = 0, level null.
   */
  @Test
  void testTranferCandidate10() {
    LocalDate current = LocalDate.now();
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_USER");
    Trainee trainee = null;
    Status status = new Status();
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(2);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(2);
    candidate.setStatus("Interview - Pass");
    candidate.setCandidateProfile(candidateProfile);
    List<Candidate> candidates = new ArrayList<>();
    candidates.add(candidate);
    String level = "FA";
    when(candidateServiceImpl.findByCandidateId(0)).thenReturn(candidate);
    when(candidateServiceImpl.tranferCandidate(0, roles, level))
        .thenReturn("success");
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    try {
      this.mockMvc
          .perform(MockMvcRequestBuilders.post("/tranfer")
              .param("candidateId", 0 + "").param("level", "null")
              .sessionAttr("roles", roles))
          .andExpect(model().attribute("candidate", candidate))
          .andExpect(model().attribute("applicationDate",
              DateUtils.formatDate2(current)))
          .andExpect(
              model().attribute("dateOfBirth", DateUtils.formatDate2(current)))
          .andExpect(model().attribute("graduationYear",
              DateUtils.formatDate1(current)))
          .andExpect(request().sessionAttribute("tranfer_message", true))
          .andExpect(
              MockMvcResultMatchers.view().name("view_candidate_profile"));
    } catch (Exception e) {
      assertEquals(NestedServletException.class, e.getClass());
    }
  }

  /**
   * test case abnomal: candidate is not tranferred, not have role, candidateId
   * = 100, level null.
   */
  @Test
  void testTranferCandidate11() {
    LocalDate current = LocalDate.now();
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_USER");
    Trainee trainee = null;
    Status status = new Status();
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(2);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(2);
    candidate.setStatus("Interview - Pass");
    candidate.setCandidateProfile(candidateProfile);
    List<Candidate> candidates = new ArrayList<>();
    candidates.add(candidate);
    String level = "FA";
    when(candidateServiceImpl.findByCandidateId(100)).thenReturn(candidate);
    when(candidateServiceImpl.tranferCandidate(100, roles, level))
        .thenReturn("success");
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    try {
      this.mockMvc
          .perform(MockMvcRequestBuilders.post("/tranfer")
              .param("candidateId", 100 + "").param("level", "null")
              .sessionAttr("roles", roles))
          .andExpect(model().attribute("candidate", candidate))
          .andExpect(model().attribute("applicationDate",
              DateUtils.formatDate2(current)))
          .andExpect(
              model().attribute("dateOfBirth", DateUtils.formatDate2(current)))
          .andExpect(model().attribute("graduationYear",
              DateUtils.formatDate1(current)))
          .andExpect(request().sessionAttribute("tranfer_message", true))
          .andExpect(
              MockMvcResultMatchers.view().name("view_candidate_profile"));
    } catch (Exception e) {
      assertEquals(NestedServletException.class, e.getClass());
    }
  }

  /**
   * test case abnomal: candidate is not tranferred, not have role, candidateId
   * = -100, level null.
   */
  @Test
  void testTranferCandidate12() {
    LocalDate current = LocalDate.now();
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_USER");
    Trainee trainee = null;
    Status status = new Status();
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(2);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(2);
    candidate.setStatus("Interview - Pass");
    candidate.setCandidateProfile(candidateProfile);
    List<Candidate> candidates = new ArrayList<>();
    candidates.add(candidate);
    String level = "FA";
    when(candidateServiceImpl.findByCandidateId(-100)).thenReturn(candidate);
    when(candidateServiceImpl.tranferCandidate(-100, roles, level))
        .thenReturn("success");
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    try {
      this.mockMvc
          .perform(MockMvcRequestBuilders.post("/tranfer")
              .param("candidateId", -100 + "").param("level", "null")
              .sessionAttr("roles", roles))
          .andExpect(model().attribute("candidate", candidate))
          .andExpect(model().attribute("applicationDate",
              DateUtils.formatDate2(current)))
          .andExpect(
              model().attribute("dateOfBirth", DateUtils.formatDate2(current)))
          .andExpect(model().attribute("graduationYear",
              DateUtils.formatDate1(current)))
          .andExpect(request().sessionAttribute("tranfer_message", true))
          .andExpect(
              MockMvcResultMatchers.view().name("view_candidate_profile"));
    } catch (Exception e) {
      assertEquals(NestedServletException.class, e.getClass());
    }
  }

  /**
   * test case abnomal: trainee is transferred, not have role, candidateId = 1,
   * level null.
   */
  @Test
  void testTranferCandidate5() throws Exception {
    LocalDate current = LocalDate.now();
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_USER");
    Trainee trainee = new Trainee();
    Status status = new Status();
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    candidateProfile.setDateOfBirth(current);
    candidateProfile.setGraduationYear(current);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(1);
    candidate.setApplicationDate(current);
    candidate.setStatus("Interview - Pass");
    candidate.setCandidateProfile(candidateProfile);
    List<Candidate> candidates = new ArrayList<>();
    candidates.add(candidate);
    trainee.setCandidateProfile(candidateProfile);
    trainee.setStatus(status);
    String level = "FA";
    when(candidateServiceImpl.findByCandidateId(1)).thenReturn(candidate);
    when(candidateServiceImpl.tranferCandidate(1, roles, level))
        .thenReturn("success");
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    when(traineeRepository.findTraineeByCandidateProfileCandidateProfileId(1))
        .thenReturn(trainee);

    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post("/tranfer").param("candidateId", 1 + "")
                .param("level", "null").sessionAttr("roles", roles))
        .andExpect(model().attribute("candidate", candidate))
        .andExpect(model().attribute("applicationDate",
            DateUtils.formatDate2(current)))
        .andExpect(
            model().attribute("dateOfBirth", DateUtils.formatDate2(current)))
        .andExpect(
            model().attribute("graduationYear", DateUtils.formatDate1(current)))
        .andExpect(request().sessionAttribute("tranfer_message", true))
        .andExpect(MockMvcResultMatchers.view().name("view_candidate_profile"));
  }

  /**
   * test case abnomal: trainee is transferred, not have role, candidateId = 0,
   * level null.
   */
  @Test
  void testTranferCandidate6() throws Exception {
    LocalDate current = LocalDate.now();
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_USER");
    Trainee trainee = new Trainee();
    Status status = new Status();
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    candidateProfile.setDateOfBirth(current);
    candidateProfile.setGraduationYear(current);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(1);
    candidate.setApplicationDate(current);
    candidate.setStatus("Interview - Pass");
    candidate.setCandidateProfile(candidateProfile);
    List<Candidate> candidates = new ArrayList<>();
    candidates.add(candidate);
    trainee.setCandidateProfile(candidateProfile);
    trainee.setStatus(status);
    String level = "FA";
    when(candidateServiceImpl.findByCandidateId(0)).thenReturn(candidate);
    when(candidateServiceImpl.tranferCandidate(0, roles, level))
        .thenReturn("success");
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    when(traineeRepository.findTraineeByCandidateProfileCandidateProfileId(0))
        .thenReturn(trainee);
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post("/tranfer").param("candidateId", 0 + "")
                .param("level", "null").sessionAttr("roles", roles))
        .andExpect(model().attribute("candidate", candidate))
        .andExpect(model().attribute("applicationDate",
            DateUtils.formatDate2(current)))
        .andExpect(
            model().attribute("dateOfBirth", DateUtils.formatDate2(current)))
        .andExpect(
            model().attribute("graduationYear", DateUtils.formatDate1(current)))
        .andExpect(request().sessionAttribute("tranfer_message", true))
        .andExpect(MockMvcResultMatchers.view().name("view_candidate_profile"));
  }

  /**
   * test case abnomal: trainee is transferred, not have role, candidateId =
   * 100, level null.
   */
  @Test
  void testTranferCandidate7() throws Exception {
    LocalDate current = LocalDate.now();
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_USER");
    Trainee trainee = new Trainee();
    Status status = new Status();
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    candidateProfile.setDateOfBirth(current);
    candidateProfile.setGraduationYear(current);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(1);
    candidate.setApplicationDate(current);
    candidate.setStatus("Interview - Pass");
    candidate.setCandidateProfile(candidateProfile);
    List<Candidate> candidates = new ArrayList<>();
    candidates.add(candidate);
    trainee.setCandidateProfile(candidateProfile);
    trainee.setStatus(status);
    String level = "FA";
    when(candidateServiceImpl.findByCandidateId(100)).thenReturn(candidate);
    when(candidateServiceImpl.tranferCandidate(100, roles, level))
        .thenReturn("success");
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    when(traineeRepository.findTraineeByCandidateProfileCandidateProfileId(100))
        .thenReturn(trainee);

    this.mockMvc
        .perform(MockMvcRequestBuilders.post("/tranfer")
            .param("candidateId", 100 + "").param("level", "null")
            .sessionAttr("roles", roles))
        .andExpect(model().attribute("candidate", candidate))
        .andExpect(model().attribute("applicationDate",
            DateUtils.formatDate2(current)))
        .andExpect(
            model().attribute("dateOfBirth", DateUtils.formatDate2(current)))
        .andExpect(
            model().attribute("graduationYear", DateUtils.formatDate1(current)))
        .andExpect(request().sessionAttribute("tranfer_message", true))
        .andExpect(MockMvcResultMatchers.view().name("view_candidate_profile"));
  }

  /**
   * test case abnomal: trainee is transferred, not have role, candidateId =
   * -100, level null.
   */
  @Test
  void testTranferCandidate8() throws Exception {
    LocalDate current = LocalDate.now();
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_USER");
    Trainee trainee = new Trainee();
    Status status = new Status();
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    candidateProfile.setDateOfBirth(current);
    candidateProfile.setGraduationYear(current);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(1);
    candidate.setApplicationDate(current);
    candidate.setStatus("Interview - Pass");
    candidate.setCandidateProfile(candidateProfile);
    List<Candidate> candidates = new ArrayList<>();
    candidates.add(candidate);
    trainee.setCandidateProfile(candidateProfile);
    trainee.setStatus(status);
    String level = "FA";
    when(candidateServiceImpl.findByCandidateId(-100)).thenReturn(candidate);
    when(candidateServiceImpl.tranferCandidate(-100, roles, level))
        .thenReturn("success");
    when(candidateServiceImpl.getAllCandidate()).thenReturn(candidates);
    when(
        traineeRepository.findTraineeByCandidateProfileCandidateProfileId(-100))
            .thenReturn(trainee);

    this.mockMvc
        .perform(MockMvcRequestBuilders.post("/tranfer")
            .param("candidateId", -100 + "").param("level", "null")
            .sessionAttr("roles", roles))
        .andExpect(model().attribute("candidate", candidate))
        .andExpect(model().attribute("applicationDate",
            DateUtils.formatDate2(current)))
        .andExpect(
            model().attribute("dateOfBirth", DateUtils.formatDate2(current)))
        .andExpect(
            model().attribute("graduationYear", DateUtils.formatDate1(current)))
        .andExpect(request().sessionAttribute("tranfer_message", true))
        .andExpect(MockMvcResultMatchers.view().name("view_candidate_profile"));
  }

  @Test
  void testHandleUserNotFoundException() {
    fail("Not yet implemented");
  }

  @Test
  void testDeleteCandidate() {
    fail("Not yet implemented");
  }

  @Test
  void testViewCreateCandidatePage() {
    fail("Not yet implemented");
  }

  @Test
  void testProcessCreateCandidate() {
    fail("Not yet implemented");
  }

  @Test
  void testProcessUpdateCandidate() {
    fail("Not yet implemented");
  }

  @Test
  void testViewUpdateCandidatePage() {
    fail("Not yet implemented");
  }

}
