package fa.fams.service.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.fams.common.log.Log4J;
import fa.fams.common.utility.CandidateUtils;
import fa.fams.common.utility.DateUtils;
import fa.fams.common.valueobjects.MessageVo;
import fa.fams.model.Candidate;
import fa.fams.model.InterView;
import fa.fams.model.User;
import fa.fams.repository.InterViewRepository;
import fa.fams.repository.UserRepository;
import fa.fams.service.service.InterViewService;

/**
 * this class is a interview service. Validate and process interview info
 * between controller and repository
 *
 */
@Service
public class InterViewServiceImpl implements InterViewService {

  @Autowired
  private InterViewRepository interViewRepository;

  @Autowired
  ClassAdminProfileServiceImpl classAdminProfileServiceImpl;

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<InterView> getAllByCandidateId(int candidateId) {
    return interViewRepository.getAllByCandidateCandidateID(candidateId);
  }

  /**
   * this function save or update interview and update candidate infomation.
   */
  @Override
  public List<InterView> saveOrUpdateWithCandidate(List<String> listInterview,
      Candidate candidate, int employeeId, MessageVo messageVo) {
    User user = userRepository.findById(employeeId);
    if (listInterview == null || listInterview.size() == 0 || candidate == null
        || user == null) {
      return null;
    } else {
      List<InterView> interViews = new ArrayList<InterView>();
      String[] tempArrInterview = null;
      InterView interView = null;
      StringBuilder history;
      LocalDate date;
      int time = 1;
      for (String s : listInterview) {
        tempArrInterview = s.split("&and&");
        interView = new InterView();
        try {
          interView.setInterviewTime(time);
          date = DateUtils.convertToLocalDate1(tempArrInterview[0],
              DateUtils.getDateTimeFormater1());
          if (date == null) {
            messageVo.setMessageOfInterview("MSG5");
            return null;
          }
          if (LocalDate.now().compareTo(date) == -1) {
            messageVo.setMessageOfInterview("MSG10");
            return null;
          }
          interView.setInterviewDate(date);
          interView.setInterviewer(tempArrInterview[1]);
          interView.setComments(tempArrInterview[2]);
          interView.setResult(tempArrInterview[3]);
          if (listInterview.indexOf(s) == (listInterview.size() - 1)) {
            candidate.setStatus("Interview - "
                + (tempArrInterview[3].equals("1") ? "Pass" : "Fail"));
            history = new StringBuilder();
            history.append(candidate.getHistory());
            history.append("</br>" + (CandidateUtils
                .createHistory(user.getFullName(), "Interview Updated by")));
            candidate.setHistory(history.toString());
          }
          interView.setCandidate(candidate);
          if (tempArrInterview.length == 5) {
            int interviewId = Integer.parseInt(tempArrInterview[4]);
            interView.setInterviewId(interviewId);
          }
        } catch (NumberFormatException e) {
          Log4J.getLogger().error(e.getMessage());
          messageVo.setMessageOfInterview("MSG37");
          return null;
        }
        interViewRepository.save(interView);
        interViews.add(interView);
        time++;
      }
      messageVo.setMessageOfInterview("MSG38");
      return interViews;
    }
  }
}