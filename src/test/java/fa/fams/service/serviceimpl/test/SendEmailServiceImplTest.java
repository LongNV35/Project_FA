package fa.fams.service.serviceimpl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import fa.fams.model.Candidate;
import fa.fams.model.CandidateProfile;
import fa.fams.service.serviceimpl.SendEmailServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@TestPropertySource(locations = "/application.properties")
@ComponentScan(basePackages = "fa.training.service")
class SendEmailServiceImplTest {

  @Autowired
  public SendEmailServiceImpl sendEmailServiceImpl;

  @BeforeEach
  void setUp() throws Exception {
  }

  /**
   * test case nomal: level not null, candidate not null.
   */
  @Test
  void testSendEmail1() {
    Candidate candidate = new Candidate();
    candidate.setCandidateID(1);
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    candidateProfile.setEmail("chuthanh913@gmail.com");
    candidateProfile.setFullName("Chu Thanh");
    candidate.setCandidateProfile(candidateProfile);
    String level = "FA";
    assertEquals(true, sendEmailServiceImpl.sendEmail(candidate, level));
  }

  /**
   * test case abnomal: level not null, candidate null.
   */
  @Test
  void testSendEmail2() {
    Candidate candidate = null;
    String level = "FA";
    Assertions.assertThrows(NullPointerException.class, () -> {
      sendEmailServiceImpl.sendEmail(candidate, level);
    });
  }

  /**
   * test case abnomal: level null, candidate not null.
   */
  @Test
  void testSendEmail3() {
    Candidate candidate = new Candidate();
    candidate.setCandidateID(1);
    CandidateProfile candidateProfile = new CandidateProfile();
    candidateProfile.setCandidateProfileId(1);
    candidateProfile.setEmail("chuthanh913@gmail.com");
    candidateProfile.setFullName("Chu Thanh");
    candidate.setCandidateProfile(candidateProfile);
    String level = null;
    assertEquals(true, sendEmailServiceImpl.sendEmail(candidate, level));
  }

  /**
   * test case abnomal: level null, candidate null.
   */
  @Test
  void testSendEmail4() {
    Candidate candidate = null;
    String level = null;
    Assertions.assertThrows(NullPointerException.class, () -> {
      sendEmailServiceImpl.sendEmail(candidate, level);
    });
  }

}
