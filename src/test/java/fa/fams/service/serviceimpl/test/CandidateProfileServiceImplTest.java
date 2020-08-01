package fa.fams.service.serviceimpl.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import fa.fams.common.log.Log4J;
import fa.fams.common.valueobjects.DashboardVo;
import fa.fams.model.CandidateProfile;
import fa.fams.model.University;
import fa.fams.repository.CandidateProfileRepository;
import fa.fams.repository.UniversityRepository;
import fa.fams.service.serviceimpl.CandidateProfileServiceImpl;

@SpringBootTest
class CandidateProfileServiceImplTest {
  @Mock
  private CandidateProfileRepository candidateProfileRepository;
  @Mock
  private UniversityRepository universityRepository;

  @InjectMocks
  CandidateProfileServiceImpl candidateProfileService;

  private String status;
  private String location;

  /**
   * test when database is empty.
   */
  @Test
  void testCountCandidateByStatusLocationUC1() {
    status = "New";
    location = "Location1";
    List<Object[]> resultRepository = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation(status,
        location)).thenReturn(resultRepository);
    List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
    listExpected.add(new DashboardVo(status, "Empty", 0));
    List<DashboardVo> listActual = candidateProfileService
        .countCandidateByStatusLocation(status, location);
    Log4J.getLogger().info("testFindByStatusLocationUC1 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database has 4 record, status="New", location="Location1".
   */
  @Test
  void testCountCandidateByStatusLocationUC2() {
    String status = "New";
    String location = "Location1";
    List<Object[]> resultRepository = new ArrayList<Object[]>();
    Object[] queryReturn = { "Java", 3 };
    resultRepository.add(queryReturn);
    when(candidateProfileRepository.countCandidateByStatusLocation(status,
        location)).thenReturn(resultRepository);
    List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
    listExpected.add(new DashboardVo(status, "Java", 3));
    List<DashboardVo> listActual = candidateProfileService
        .countCandidateByStatusLocation(status, location);
    Log4J.getLogger().info("testFindByStatusLocationUC2 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database has 4 record, status="New", location="All".
   */
  @Test
  void testCountCandidateByStatusLocationUC3() {
    status = "New";
    location = "All";
    List<Object[]> resultRepository = new ArrayList<Object[]>();
    Object[] queryReturn = { "Java", 4 };
    resultRepository.add(queryReturn);
    when(
        candidateProfileRepository.countCandidateByStatusLocation(status, "%%"))
            .thenReturn(resultRepository);
    List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
    listExpected.add(new DashboardVo(status, "Java", 4));
    List<DashboardVo> listActual = candidateProfileService
        .countCandidateByStatusLocation(status, location);
    Log4J.getLogger().info("testFindByStatusLocationUC3 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database has 4 record, status="New", location=null.
   */
  @Test
  void testCountCandidateByStatusLocationUC4() {
    status = "New";
    location = null;
    List<Object[]> resultRepository = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation(status,
        location)).thenReturn(resultRepository);
    List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
    listExpected.add(new DashboardVo(status, "Empty", 0));
    List<DashboardVo> listActual = candidateProfileService
        .countCandidateByStatusLocation(status, location);
    Log4J.getLogger().info("testFindByStatusLocationUC4 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database has 4 record, status="New", location="".
   */
  @Test
  void testCountCandidateByStatusLocationUC5() {
    status = "New";
    location = "";
    List<Object[]> resultRepository = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation(status,
        location)).thenReturn(resultRepository);
    List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
    listExpected.add(new DashboardVo(status, "Empty", 0));
    List<DashboardVo> listActual = candidateProfileService
        .countCandidateByStatusLocation(status, location);
    Log4J.getLogger().info("testFindByStatusLocationUC5 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database has 4 record, status="All", location="Location1".
   */
  @Test
  void testCountCandidateByStatusLocationUC6() {
    status = "All";
    location = "Location1";
    List<Object[]> resultRepository = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation(status,
        location)).thenReturn(resultRepository);
    List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
    listExpected.add(new DashboardVo(status, "Empty", 0));
    List<DashboardVo> listActual = candidateProfileService
        .countCandidateByStatusLocation(status, location);
    Log4J.getLogger().info("testFindByStatusLocationUC6 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database has 4 record, status="All", location="All".
   */
  @Test
  void testCountCandidateByStatusLocationUC7() {
    status = "All";
    location = "All";
    List<Object[]> resultRepository = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation(status,
        location)).thenReturn(resultRepository);
    List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
    listExpected.add(new DashboardVo(status, "Empty", 0));
    List<DashboardVo> listActual = candidateProfileService
        .countCandidateByStatusLocation(status, location);
    Log4J.getLogger().info("testFindByStatusLocationUC7 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database has 4 record, status=null, location="Location1".
   */
  @Test
  void testCountCandidateByStatusLocationUC8() {
    status = null;
    location = "Location1";
    List<Object[]> resultRepository = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation(status,
        location)).thenReturn(resultRepository);
    List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
    listExpected.add(new DashboardVo(status, "Empty", 0));
    List<DashboardVo> listActual = candidateProfileService
        .countCandidateByStatusLocation(status, location);
    Log4J.getLogger().info("testFindByStatusLocationUC8 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database has 4 record, status=null, location=null.
   */
  @Test
  void testCountCandidateByStatusLocationUC9() {
    status = null;
    location = null;
    List<Object[]> resultRepository = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation(status,
        location)).thenReturn(resultRepository);
    List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
    listExpected.add(new DashboardVo(status, "Empty", 0));
    List<DashboardVo> listActual = candidateProfileService
        .countCandidateByStatusLocation(status, location);
    Log4J.getLogger().info("testFindByStatusLocationUC9 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database has 4 record, status="", location="".
   */
  @Test
  void testCountCandidateByStatusLocationUC10() {
    status = "";
    location = "";
    List<Object[]> resultRepository = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation(status,
        location)).thenReturn(resultRepository);
    List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
    listExpected.add(new DashboardVo(status, "Empty", 0));
    List<DashboardVo> listActual = candidateProfileService
        .countCandidateByStatusLocation(status, location);
    Log4J.getLogger().info("testFindByStatusLocationUC10 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database is empty, status="New", location="Location1".
   */
  @Test
  void testGetListsCountCandidateByStatusLocationUC1() {
    status = "New";
    location = "Location1";

    List<Object[]> resultRepository = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation(status,
        location)).thenReturn(resultRepository);

    ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
    List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
    listVoInExpected.add(new DashboardVo(status, "Empty", 0));
    listExpected.add(listVoInExpected);

    ArrayList<List<DashboardVo>> listActual = candidateProfileService
        .getListsCountCandidateByStatusLocation(status, location);
    Log4J.getLogger()
        .info("testGetListAllByStatusLocationUC1 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database has 5 record, status="New", location="Location1".
   */
  @Test
  void testGetListsCountCandidateByStatusLocationUC2() {
    status = "New";
    location = "Location1";
    List<Object[]> resultRepository = new ArrayList<Object[]>();
    Object[] queryReturn = { "Java", 3 };
    resultRepository.add(queryReturn);
    when(candidateProfileRepository.countCandidateByStatusLocation(status,
        location)).thenReturn(resultRepository);
    ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
    List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
    listVoInExpected.add(new DashboardVo(status, "Java", 3));
    listExpected.add(listVoInExpected);
    ArrayList<List<DashboardVo>> listActual = candidateProfileService
        .getListsCountCandidateByStatusLocation(status, location);
    Log4J.getLogger()
        .info("testGetListAllByStatusLocationUC2 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database has 5 record, status="New", location="All".
   */
  @Test
  void testGetListsCountCandidateByStatusLocationUC3() {
    status = "New";
    location = "All";
    List<Object[]> resultRepository = new ArrayList<Object[]>();
    Object[] queryReturn = { "Java", 4 };
    resultRepository.add(queryReturn);
    when(
        candidateProfileRepository.countCandidateByStatusLocation(status, "%%"))
            .thenReturn(resultRepository);
    ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
    List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
    listVoInExpected.add(new DashboardVo(status, "Java", 4));
    listExpected.add(listVoInExpected);

    ArrayList<List<DashboardVo>> listActual = candidateProfileService
        .getListsCountCandidateByStatusLocation(status, location);
    Log4J.getLogger()
        .info("testGetListAllByStatusLocationUC3 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database has 5 record, status="New", location=null.
   */
  @Test
  void testGetListsCountCandidateByStatusLocationUC4() {
    status = "New";
    location = null;
    List<Object[]> resultRepository = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation(status,
        location)).thenReturn(resultRepository);
    ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
    List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
    listVoInExpected.add(new DashboardVo(status, "Empty", 0));
    listExpected.add(listVoInExpected);

    ArrayList<List<DashboardVo>> listActual = candidateProfileService
        .getListsCountCandidateByStatusLocation(status, location);
    Log4J.getLogger()
        .info("testGetListAllByStatusLocationUC4 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database has 5 record, status="New", location="".
   */
  @Test
  void testGetListsCountCandidateByStatusLocationUC5() {
    status = "New";
    location = "";
    List<Object[]> resultRepository = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation(status,
        location)).thenReturn(resultRepository);
    ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
    List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
    listVoInExpected.add(new DashboardVo(status, "Empty", 0));
    listExpected.add(listVoInExpected);
    ArrayList<List<DashboardVo>> listActual = candidateProfileService
        .getListsCountCandidateByStatusLocation(status, location);
    Log4J.getLogger()
        .info("testGetListAllByStatusLocationUC4 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database has 5 record, status="All", location="Location1".
   */
  @Test
  void testGetListsCountCandidateByStatusLocationUC6() {
    status = "All";
    location = "Location1";
    /*
     * when status= all, system have to call 5 time repository
     */
    List<Object[]> resultRepository1 = new ArrayList<Object[]>();
    Object[] queryReturn1 = { "Java", 3 };
    resultRepository1.add(queryReturn1);
    when(candidateProfileRepository.countCandidateByStatusLocation("New",
        location)).thenReturn(resultRepository1);

    List<Object[]> resultRepository2 = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation("Test- Pass",
        location)).thenReturn(resultRepository2);

    List<Object[]> resultRepository3 = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation("Test- Fail",
        location)).thenReturn(resultRepository3);

    List<Object[]> resultRepository4 = new ArrayList<Object[]>();
    Object[] queryReturn3 = { "Java", 1 };
    resultRepository4.add(queryReturn3);
    when(candidateProfileRepository
        .countCandidateByStatusLocation("Interview- Pass", location))
            .thenReturn(resultRepository4);

    List<Object[]> resultRepository5 = new ArrayList<Object[]>();
    when(candidateProfileRepository
        .countCandidateByStatusLocation("Interview- Fail", location))
            .thenReturn(resultRepository5);

    ArrayList<List<DashboardVo>> listExpected = getExpectedForGetUC6();

    ArrayList<List<DashboardVo>> listActual = candidateProfileService
        .getListsCountCandidateByStatusLocation(status, location);
    Log4J.getLogger()
        .info("testGetListAllByStatusLocationUC6 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * this function to get list expected for testGetListsCountCandidateByStatusLocationUC6
   * when status= "All".
   * 
   * @return - list expected
   */
  private ArrayList<List<DashboardVo>> getExpectedForGetUC6() {
    ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();

    List<DashboardVo> listWithNew = new ArrayList<DashboardVo>();
    listWithNew.add(new DashboardVo("New", "Java", 3));

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
   * test when database has 5 record, status="All", location="All".
   */
  @Test
  void testGetListsCountCandidateByStatusLocationUC7() {
    status = "All";
    location = "All";
    /*
     * when status= all, system have to call 5 time repository
     */
    List<Object[]> resultRepository1 = new ArrayList<Object[]>();
    Object[] queryReturn1 = { "Java", 4 };
    resultRepository1.add(queryReturn1);
    when(candidateProfileRepository.countCandidateByStatusLocation("New", "%%"))
        .thenReturn(resultRepository1);

    List<Object[]> resultRepository2 = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation("Test- Pass",
        "%%")).thenReturn(resultRepository2);

    List<Object[]> resultRepository3 = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation("Test- Fail",
        "%%")).thenReturn(resultRepository3);

    List<Object[]> resultRepository4 = new ArrayList<Object[]>();
    Object[] queryReturn3 = { "Java", 1 };
    resultRepository4.add(queryReturn3);
    when(candidateProfileRepository
        .countCandidateByStatusLocation("Interview- Pass", "%%"))
            .thenReturn(resultRepository4);

    List<Object[]> resultRepository5 = new ArrayList<Object[]>();
    when(candidateProfileRepository
        .countCandidateByStatusLocation("Interview- Fail", "%%"))
            .thenReturn(resultRepository5);

    ArrayList<List<DashboardVo>> listExpected = getExpectedForGetUC7();

    ArrayList<List<DashboardVo>> listActual = candidateProfileService
        .getListsCountCandidateByStatusLocation(status, location);
    Log4J.getLogger()
        .info("testGetListAllByStatusLocationUC7 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * this function to get list expected for testGetListsCountCandidateByStatusLocationUC7
   * when status= "All".
   * 
   * @return - list expected
   */
  private ArrayList<List<DashboardVo>> getExpectedForGetUC7() {
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
   * test when database has 5 record, status=null, location="Location1".
   */
  @Test
  void testGetListsCountCandidateByStatusLocationUC8() {
    String status = null;
    String location = "Location1";

    List<Object[]> resultRepository = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation(status,
        location)).thenReturn(resultRepository);

    ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
    List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
    listVoInExpected.add(new DashboardVo(status, "Empty", 0));
    listExpected.add(listVoInExpected);

    ArrayList<List<DashboardVo>> listActual = candidateProfileService
        .getListsCountCandidateByStatusLocation(status, location);
    Log4J.getLogger()
        .info("testGetListAllByStatusLocationUC8 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database has 5 record, status=null, location=null.
   */
  @Test
  void testGetListsCountCandidateByStatusLocationUC9() {
    status = null;
    location = null;

    List<Object[]> resultRepository = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation(status,
        location)).thenReturn(resultRepository);

    ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
    List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
    listVoInExpected.add(new DashboardVo(status, "Empty", 0));
    listExpected.add(listVoInExpected);

    ArrayList<List<DashboardVo>> listActual = candidateProfileService
        .getListsCountCandidateByStatusLocation(status, location);
    Log4J.getLogger()
        .info("testGetListAllByStatusLocationUC9 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * test when database has 5 record, status="", location="Location1".
   */
  @Test
  void testGetListsCountCandidateByStatusLocationUC10() {
    status = "";
    location = "Location1";

    List<Object[]> resultRepository = new ArrayList<Object[]>();
    when(candidateProfileRepository.countCandidateByStatusLocation(status,
        location)).thenReturn(resultRepository);

    ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
    List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
    listVoInExpected.add(new DashboardVo(status, "Empty", 0));
    listExpected.add(listVoInExpected);

    ArrayList<List<DashboardVo>> listActual = candidateProfileService
        .getListsCountCandidateByStatusLocation(status, location);
    Log4J.getLogger()
        .info("testGetListAllByStatusLocationUC9 actual:" + listActual);
    assertEquals(listExpected, listActual);
  }

  /**
   * normal case: file empty and candidate id exist.
   */
  @Test
  void testGetFileName1() {
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCv("thai.pdf");
    MultipartFile file = new MockMultipartFile("file", "thai.pdf",
        "contentType", "".getBytes());
    when(candidateProfileRepository.findByCandidateProfileId(1))
        .thenReturn(candidateProfile);
    assertEquals(candidateProfileService.getFileName(1, file),
        candidateProfile.getCv());
  }

  /**
   * normal case: file empty and candidate id not exist.
   */
  @Test
  void testGetFileName2() {
    MultipartFile file = new MockMultipartFile("file", "thai.pdf",
        "contentType", "".getBytes());
    when(candidateProfileRepository.findByCandidateProfileId(1))
        .thenReturn(null);
    assertEquals(candidateProfileService.getFileName(1, file), "");
  }

  /**
   * normal case: file not empty and candidate id not exist.
   */
  @Test
  void testGetFileName3() {
    MultipartFile file = new MockMultipartFile("file", "thai.pdf",
        "contentType", "content".getBytes());
    when(candidateProfileRepository.findByCandidateProfileId(1))
        .thenReturn(null);
    assertEquals(candidateProfileService.getFileName(1, file),
        file.getOriginalFilename());
  }

  /**
   * normal case: file not empty and candidate id exist.
   */
  @Test
  void testGetFileName4() {
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCv("thai.pdf");
    MultipartFile file = new MockMultipartFile("file", "thai.pdf",
        "contentType", "content".getBytes());
    when(candidateProfileRepository.findByCandidateProfileId(1))
        .thenReturn(candidateProfile);
    assertEquals(candidateProfileService.getFileName(1, file),
        file.getOriginalFilename());
  }

  /**
   * normal case: graduration year valid.
   */
  @Test
  void testGetGradurationYear1() {
    String gradurationYear = "10-04-2020";
    assertNotNull(candidateProfileService.getGradurationYear(gradurationYear));
  }

  /**
   * normal case: graduration year invalid.
   */
  @Test
  void testGetGradurationYear2() {
    String gradurationYear = "10-04/2020";
    assertNull(candidateProfileService.getGradurationYear(gradurationYear));
  }

  /**
   * normal case: graduration year is future date.
   */
  @Test
  void testGetGradurationYear3() {
    String gradurationYear = "10-04-2021";
    assertNull(candidateProfileService.getGradurationYear(gradurationYear));
  }

  /**
   * normal case: university id exist.
   */
  @Test
  void testGetUniversity1() {
    University university = new University(1, "HaUI", "none", null);
    when(universityRepository.findByUniversityId(1)).thenReturn(university);
    assertEquals(
        candidateProfileService.getUniversity(1, "University other name"),
        university);
  }

  /**
   * normal case: university id not exist and save new university success.
   */
  @Test
  void testGetUniversity2() {
    University university1 = new University(1, "University other name", "none",
        null);
    University university2 = new University("University other name");
    when(universityRepository.findByUniversityId(1)).thenReturn(null);
    when(universityRepository.save(university2)).thenReturn(university1);
    assertEquals(
        candidateProfileService.getUniversity(1, "University other name"),
        university1);
  }

  /**
   * normal case: university id not exist and save new university unsuccess.
   */
  @Test
  void testGetUniversity3() {
    University university2 = new University("");
    when(universityRepository.findByUniversityId(1)).thenReturn(null);
    when(universityRepository.save(university2)).thenReturn(null);
    assertEquals(candidateProfileService.getUniversity(1, ""), null);
  }
}
