package fa.fams.web.controller.test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import fa.fams.repository.CandidateRepository;
import fa.fams.repository.TraineeRepository;
import fa.fams.service.serviceimpl.CandidateServiceImpl;


@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan(basePackages = "fa.training.service")
@ComponentScan(basePackages = "fa.training.repository")
@TestPropertySource(locations = "classpath:application.properties")
class GlobalControllerExceptionTest {

  @Autowired
  @Mock
  public CandidateServiceImpl candidateServiceImpl;

  @Autowired
  public CandidateRepository candidateRepository;

  @Autowired
  public TraineeRepository traineeRepository;

  @BeforeEach
  void setUp() throws Exception {
  }

  @Test
  void testHandleConflict() {
    fail("Not yet implemented");
  }

  @Test
  void testHandleConstraintViolation() {
    fail("Not yet implemented");
  }

  @Test
  void testHandleNotFound() {
    candidateServiceImpl = Mockito.mock(CandidateServiceImpl.class);
    when(candidateServiceImpl.getCandidateById(10))
        .thenThrow(NotFound.class);
  }

  @Test
  void testHandleBadRequest() {
    fail("Not yet implemented");
  }

  @Test
  void testHandleForbidden() {
    fail("Not yet implemented");
  }

  @Test
  void testHandleInternalServer() {
    fail("Not yet implemented");
  }

  @Test
  void testDatabaseError() {
    fail("Not yet implemented");
  }

  @Test
  void testHandleArray() {
    fail("Not yet implemented");
  }

  @Test
  void testHandleNullPointer() {
    candidateServiceImpl = Mockito.mock(CandidateServiceImpl.class);
    when(candidateServiceImpl.getCandidateById(10))
        .thenThrow(NullPointerException.class);
  }

  @Test
  void testNumberFormat() {
    candidateServiceImpl = Mockito.mock(CandidateServiceImpl.class);
    when(candidateServiceImpl.getCandidateById(Integer.parseInt("a")))
        .thenThrow(NumberFormatException.class);
  }

  @Test
  void testHandleClassNotFound() {
    fail("Not yet implemented");
  }

  @Test
  void testHandleDateTimeParse() {
    fail("Not yet implemented");
  }

  @Test
  void testHandleIllegalState() {
    fail("Not yet implemented");
  }

  @Test
  void testHandleIO() {
    fail("Not yet implemented");
  }

  @Test
  void testHandleError() {
    fail("Not yet implemented");
  }

}
