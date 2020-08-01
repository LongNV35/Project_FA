package fa.fams.service.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import fa.fams.common.log.Log4J;
import fa.fams.common.utility.CandidateUtils;
import fa.fams.common.utility.DateUtils;
import fa.fams.common.valueobjects.MessageVo;
import fa.fams.model.Candidate;
import fa.fams.model.EntryTest;
import fa.fams.model.User;
import fa.fams.repository.EntryTestRepository;
import fa.fams.repository.UserRepository;
import fa.fams.service.service.EntryTestService;

/**
 * this class is a entry test service. Validate and process entry test info
 * between controller and repository
 *
 */
@Service
public class EntryTestServiceImpl implements EntryTestService {

  @Autowired
  private EntryTestRepository entryTestRepository;

  @Autowired
  ClassAdminProfileServiceImpl classAdminProfileServiceImpl;

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<EntryTest> getAllByCandidateId(int candidateId) {
    return entryTestRepository.getAllByCandidateCandidateID(candidateId);
  }

  /**
   * this function save or update entry tests and update candidate infomation.
   */
  @Override
  public List<EntryTest> saveOrUpdateWithCandidate(List<String> listEntryTest,
      Candidate candidate, int employeeId, MessageVo messageVo) {
    User user = userRepository.findById(employeeId);
    if (listEntryTest == null || listEntryTest.size() == 0 || candidate == null
        || user == null) {
      return null;
    } else {
      List<EntryTest> entryTests = new ArrayList<EntryTest>();
      String[] tempArrEntryTest = null;
      EntryTest entryTest = null;
      StringBuilder history;
      LocalDate date;
      int time = 1;
      for (String s : listEntryTest) {
        tempArrEntryTest = s.split("&and&");
        entryTest = new EntryTest();
        try {
          entryTest.setTime(time);
          date = DateUtils.convertToLocalDate1(tempArrEntryTest[0],
              DateUtils.getDateTimeFormater1());
          if (date == null) {
            messageVo.setMessageOfEntryTest("MSG5");
            return null;
          }
          if (LocalDate.now().compareTo(date) == -1) {
            messageVo.setMessageOfEntryTest("MSG10");
            return null;
          }
          entryTest.setDate(date);
          entryTest.setLanguageValuator(tempArrEntryTest[1]);
          int languageResult = Integer.parseInt(tempArrEntryTest[2]);
          if (languageResult > 100) {
            messageVo.setMessageOfEntryTest("MSG6");
            return null;
          }
          entryTest.setLanguageResult(languageResult);
          entryTest.setTechnicalValuator(tempArrEntryTest[3]);
          int technicalResult = Integer.parseInt(tempArrEntryTest[4]);
          if (technicalResult > 100) {
            messageVo.setMessageOfEntryTest("MSG6");
            return null;
          }
          entryTest.setTechnicalResult(technicalResult);
          entryTest.setResult(tempArrEntryTest[5]);
          if (listEntryTest.indexOf(s) == (listEntryTest.size() - 1)) {
            candidate.setStatus("Test - "
                + (tempArrEntryTest[5].equals("1") ? "Pass" : "Fail"));
            history = new StringBuilder();
            history.append(candidate.getHistory());
            history.append("</br>" + (CandidateUtils
                .createHistory(user.getFullName(), "Test Updated by")));
            candidate.setHistory(history.toString());
          }
          entryTest.setCandidate(candidate);
          if (tempArrEntryTest.length == 7) {
            int entryTestId = Integer.parseInt(tempArrEntryTest[6]);
            entryTest.setTestId(entryTestId);
          }
          entryTest = entryTestRepository.save(entryTest);
          entryTests.add(entryTest);
        } catch (NumberFormatException | DataIntegrityViolationException
            | HibernateException e) {
          Log4J.getLogger().error(e.getMessage());
          messageVo.setMessageOfEntryTest("MSG37");
          return null;
        }
        time++;
      }
      messageVo.setMessageOfEntryTest("MSG38");
      return entryTests;
    }
  }
}