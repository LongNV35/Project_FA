package fa.fams.service.serviceimpl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import fa.fams.common.log.Log4J;
import fa.fams.common.utility.CandidateUtils;
import fa.fams.common.utility.DateUtils;
import fa.fams.model.Candidate;
import fa.fams.model.CandidateProfile;
import fa.fams.model.Channel;
import fa.fams.model.Location;
import fa.fams.model.Status;
import fa.fams.model.Trainee;
import fa.fams.model.User;
import fa.fams.repository.CandidateRepository;
import fa.fams.repository.StatusRepository;
import fa.fams.repository.TraineeRepository;
import fa.fams.repository.UserRepository;
import fa.fams.service.serviceimpl.CandidateServiceImpl;
import fa.fams.service.serviceimpl.ChannelServiceImpl;
import fa.fams.service.serviceimpl.LocationServiceImpl;
import fa.fams.service.serviceimpl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan(basePackages = "fa.fams.service.serviceimpl")
@ComponentScan(basePackages = "fa.fams.repository")
@ComponentScan(basePackages = "fa.fams.utility")
@TestPropertySource(locations = "classpath:application.properties")
class CandidateServiceImplTest {

  @InjectMocks
  public CandidateServiceImpl candidateServiceImpl;

  @Mock
  public CandidateRepository candidateRepository;

  @Mock
  public TraineeRepository traineeRepository;

  @Mock
  public StatusRepository statusRepository;

  @Mock
  public UserServiceImpl userServiceImpl;

  @Mock
  public LocationServiceImpl locationServiceImpl;

  @Mock
  public UserRepository userRepository;

  @Mock
  public CandidateUtils candidateUtils;

  @Mock
  public DateUtils dateUtils;

  @Mock
  public ChannelServiceImpl channelServiceImpl;

  public Candidate candidate;

  @BeforeEach
  void setUp() throws Exception {

  }

  @Test
  void testUpdateCandidate() {
    fail("Not yet implemented");
  }

  @Test
  void testGetAll() {
    fail("Not yet implemented");
  }

  @Test
  void testSaveCandidate() {

    assertEquals(1, candidateServiceImpl.getCandidateById(1));
  }

  @Test
  void testSaveCandidateVO() {
    fail("Not yet implemented");
  }

  /**
   * Author: LongNV35
   * 
   * Test normal case
   */

  @Test
  public void testGetAllCandidate() {

    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDate localDate1 = LocalDate.parse("2020-05-06", pattern);
    LocalDate localDate2 = LocalDate.parse("2020-03-03", pattern);
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

    List<Candidate> actual = new ArrayList<>();
    actual.add(new Candidate(4, localDate1, "Interview - Fail", "abc", "abc",
        channel, location));
    actual.add(new Candidate(5, localDate2, "Test - Pass", "abc", "abc",
        channel2, location2));
    actual.add(new Candidate(6, localDate3, "Test - Fail", "abc", "abc",
        channel3, location3));
    actual.add(new Candidate(534, localDate3, "Test - Fail", "abc", "abc",
        channel3, location3));

    when(candidateRepository.findAll()).thenReturn(actual);

    List<Candidate> expected = candidateServiceImpl.getAllCandidate();
    Log4J.getLogger().info(expected);

    assertEquals(expected, actual);

  }

  /**
   * test case nomal: candidate is not tranferred, have role, candidateId = 1.
   */
  @Test
  void testTranferCandidate1() {
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_ADMIN");
    Trainee trainee = null;
    Status status = new Status();
    status.setStatusId(1);
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(1);
    candidate.setStatus("Interview - Pass");
    candidate.setCandidateProfile(candidateProfile);
    String level = "FA";
    when(candidateRepository.findByCandidateID(1)).thenReturn(candidate);
    when(traineeRepository.findTraineeByCandidateProfileCandidateProfileId(1))
        .thenReturn(trainee);
    when(statusRepository.getOne(1)).thenReturn(status);
    Assertions.assertThrows(NullPointerException.class, () -> {
      candidateServiceImpl.tranferCandidate(1, roles, level);
    });
  }

  /**
   * test case abnomal: candidate is not tranferred, have role, candidateId = 0.
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
    Candidate candidate = null;
    String level = "FA";
    when(candidateRepository.findByCandidateID(0)).thenReturn(candidate);
    when(traineeRepository.findTraineeByCandidateProfileCandidateProfileId(0))
        .thenReturn(trainee);
    when(statusRepository.getOne(1)).thenReturn(status);
    assertEquals("fail",
        candidateServiceImpl.tranferCandidate(0, roles, level));
  }

  /**
   * test case abnomal: candidate is not tranferred, have role, candidateId =
   * 100.
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
    Candidate candidate = null;
    String level = "FA";
    when(candidateRepository.findByCandidateID(0)).thenReturn(candidate);
    when(traineeRepository.findTraineeByCandidateProfileCandidateProfileId(0))
        .thenReturn(trainee);
    when(statusRepository.getOne(1)).thenReturn(status);
    assertEquals("fail",
        candidateServiceImpl.tranferCandidate(100, roles, level));
  }

  /**
   * test case abnomal: candidate is not tranferred, have role, candidateId =
   * -100.
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
    Candidate candidate = null;
    String level = "FA";
    when(candidateRepository.findByCandidateID(0)).thenReturn(candidate);
    when(traineeRepository.findTraineeByCandidateProfileCandidateProfileId(0))
        .thenReturn(trainee);
    when(statusRepository.getOne(1)).thenReturn(status);
    assertEquals("fail",
        candidateServiceImpl.tranferCandidate(-100, roles, level));
  }

  /**
   * test case abnomal: candidate is tranferred, not have role, candidateId = 1.
   */
  @Test
  void testTranferCandidate5() {
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_USER");
    Trainee trainee = null;
    Status status = new Status();
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(1);
    candidate.setStatus("Interview - Pass");
    candidate.setCandidateProfile(candidateProfile);
    String level = "FA";
    when(candidateRepository.findByCandidateID(1)).thenReturn(candidate);
    when(traineeRepository.findTraineeByCandidateProfileCandidateProfileId(1))
        .thenReturn(trainee);
    when(statusRepository.getOne(1)).thenReturn(status);
    assertEquals("fail",
        candidateServiceImpl.tranferCandidate(1, roles, level));
  }

  /**
   * test case abnomal: candidate is tranferred, not have role, candidateId = 0.
   */
  @Test
  void testTranferCandidate6() {
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_USER");
    Trainee trainee = new Trainee();
    Status status = new Status();
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(1);
    candidate.setCandidateProfile(candidateProfile);
    trainee.setCandidateProfile(candidateProfile);
    String level = "";
    when(traineeRepository.findTraineeByCandidateProfileCandidateProfileId(1))
        .thenReturn(trainee);
    when(statusRepository.getOne(1)).thenReturn(status);
    assertEquals("fail",
        candidateServiceImpl.tranferCandidate(0, roles, level));
  }

  /**
   * test case abnomal: candidate is tranferred, not have role, candidateId =
   * 100.
   */
  @Test
  void testTranferCandidate7() {
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_USER");
    Trainee trainee = new Trainee();
    Status status = new Status();
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(1);
    candidate.setCandidateProfile(candidateProfile);
    trainee.setCandidateProfile(candidateProfile);
    String level = "";
    when(traineeRepository.findTraineeByCandidateProfileCandidateProfileId(1))
        .thenReturn(trainee);
    when(statusRepository.getOne(1)).thenReturn(status);
    assertEquals("fail",
        candidateServiceImpl.tranferCandidate(100, roles, level));
  }

  /**
   * test case abnomal: candidate is tranferred, not have role, candidateId =
   * -100.
   */
  @Test
  void testTranferCandidate8() {
    Set<String> roles = new HashSet<String>();
    roles.add("ROLE_USER");
    Trainee trainee = new Trainee();
    Status status = new Status();
    status.setStatusName("Waiting for Class");
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    Candidate candidate = new Candidate();
    candidate.setCandidateID(1);
    candidate.setCandidateProfile(candidateProfile);
    trainee.setCandidateProfile(candidateProfile);
    String level = "";
    when(traineeRepository.findTraineeByCandidateProfileCandidateProfileId(1))
        .thenReturn(trainee);
    when(statusRepository.getOne(1)).thenReturn(status);
    assertEquals("fail",
        candidateServiceImpl.tranferCandidate(-100, roles, level));
  }

  /**
   * normal case: employee and candidate exist.
   */
  @Test
  void testGetHistory1() {
    User user = new User(1, "thai", "thai123", "thainguyen", null);
    Candidate candidate = new Candidate(1, LocalDate.now(), "New", "", null,
        null);
    when(userRepository.findById(1)).thenReturn(user);
    when(candidateRepository.findByCandidateID(1)).thenReturn(candidate);
    assertEquals(candidateServiceImpl.getHistory(1, 1),
        "<br />" + DateUtils.getDateTimeFormater2().format(LocalDateTime.now())
            + " - Updated by - " + user.getFullName());
  }

  /**
   * normal case: employee exist but candidate not exist.
   */
  @Test
  void testGetHistory2() {
    User user = new User(1, "thai", "thai123", "thainguyen", null);
    when(userRepository.findById(1)).thenReturn(user);
    when(candidateRepository.findByCandidateID(1)).thenReturn(null);
    assertEquals(candidateServiceImpl.getHistory(1, 1),
        DateUtils.getDateTimeFormater2().format(LocalDateTime.now())
            + " - Created by - " + user.getFullName());
  }

  /**
   * normal case: employee and candidate not exist.
   */
  @Test
  void testGetHistory3() {
    when(userRepository.findById(1)).thenReturn(null);
    when(candidateRepository.findByCandidateID(1)).thenReturn(null);
    assertEquals(candidateServiceImpl.getHistory(1, 1), null);
  }

  /**
   * normal case: employee not exist but candidate exist.
   */
  @Test
  void testGetHistory4() {
    Candidate candidate = new Candidate(1, LocalDate.now(), "New", "", null,
        null);
    when(userRepository.findById(1)).thenReturn(null);
    when(candidateRepository.findByCandidateID(1)).thenReturn(candidate);
    assertEquals(candidateServiceImpl.getHistory(1, 1), null);
  }
}
