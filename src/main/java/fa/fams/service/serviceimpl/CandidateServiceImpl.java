package fa.fams.service.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import fa.fams.common.log.Log4J;
import fa.fams.common.utility.CandidateUtils;
import fa.fams.common.utility.DateUtils;
import fa.fams.common.utility.MessagesList;
import fa.fams.common.valueobjects.MessageVo;
import fa.fams.model.Candidate;
import fa.fams.model.Channel;
import fa.fams.model.Location;
import fa.fams.model.Trainee;
import fa.fams.model.User;
import fa.fams.repository.CandidateRepository;
import fa.fams.repository.ChannelRepository;
import fa.fams.repository.LocationRepository;
import fa.fams.repository.StatusRepository;
import fa.fams.repository.TraineeRepository;
import fa.fams.repository.UserRepository;
import fa.fams.service.service.CandidateService;

/**
 * this class is a candidate service. Validate and process candidate info
 * between controller and repository
 *
 */
@Service
@Qualifier(value = "candidateServiceImpl")
public class CandidateServiceImpl implements CandidateService {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  ClassAdminServiceImpl classAdminServiceImpl;

  @Autowired
  private ChannelRepository channelRepository;

  @Autowired
  private LocationRepository locationEpository;

  @Autowired
  TraineeRepository traineeRepository;

  @Autowired
  StatusRepository statusRepository;

  @Autowired
  SendEmailServiceImpl sendEmailServiceImpl;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  CandidateServiceImpl candidateServiceImpl;

  /**
   * this fuction to get candidate has candidateId = param candidateId.
   */
  @Override
  public Candidate getCandidateById(int candidateId) {
    Candidate candidate = checkCandidate(candidateId);
    if (candidate == null) {
      return null;
    } else {
      return candidate;
    }
  }

  @Override
  public Candidate save(Candidate candidate) {
    return candidateRepository.save(candidate);
  }

  @Override
  public Candidate findByCandidateId(int id) {
    return candidateRepository.findByCandidateID(id);
  }

  /**
   * this fuction to process tranfer candidate.
   */
  @Override
  public String tranferCandidate(int candidateId, Set<String> roles,
      String level) {
    Candidate candidate = candidateRepository.findByCandidateID(candidateId);
    if (("fail".equals(checkRole(roles)) == true) || (candidate == null)) {
      return "fail";
    }
    Trainee trainee = traineeRepository
        .findTraineeByCandidateProfileCandidateProfileId(
            candidate.getCandidateProfile().getCandidateProfileId());
    if (("Interview - Pass".equals(candidate.getStatus()) == true)
        && (trainee == null)) {
      if (sendEmail(candidate, level) == true) {
        processTranfer(trainee, candidate);
        return "success";
      } else {
        return "fail";
      }
    }
    return "fail";
  }

  /**
   * the fuction to set data into trainee, update candidate.
   * 
   * @param trainee   - param to recive from view
   * @param candidate - param to recive from view
   */
  private void processTranfer(Trainee trainee, Candidate candidate) {
    trainee = new Trainee(
        statusRepository.findStatusByStatusName("Waiting for Class"),
        candidate.getCandidateProfile());
    traineeRepository.save(trainee);
    candidate.setStatus("Tranferred");
    candidateRepository.save(candidate);
  }

  /**
   * the fuction to send email to candidate.
   * 
   * @param candidate - param recive from view
   * @param level     - param recive from view
   * @return
   */
  private boolean sendEmail(Candidate candidate, String level) {
    if (sendEmailServiceImpl.sendEmail(candidate, level) == true) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean updateStatusInActive(int id) {
    Candidate candidate = candidateRepository.findByCandidateID(id);
    if (candidate != null) {
      return candidateRepository.updateStatusInActive(id) > 0;
    } else {
      Log4J.getLogger().info(MessagesList.MSG8);
    }
    return true;
  }

  /**
   * this function get a candidate from paramaters.
   * 
   * @param candidateId     - candidate id for case update
   * @param applicationDate - date when create or update candidate
   * @param status          - status of candidate
   * @param employeeId      - user id when login to sysytem
   * @param channelId       - channel id of candidate
   * @param siteId          - site id of candidate
   * @param messageVo       - stored message
   * @return
   */
  public Candidate convertToCandidate(int candidateId, String applicationDate,
      String status, int employeeId, int channelId, int siteId,
      MessageVo messageVo) {
    LocalDate applicationDateConverted = DateUtils
        .convertToLocalDate1(applicationDate, DateUtils.getDateTimeFormater1());
    if (applicationDateConverted == null) {
      messageVo.setMessageOfCandidate("MSG5");
      return null;
    } else {
      if (applicationDateConverted.isEqual(LocalDate.now()) == false) {
        messageVo.setMessageOfCandidate("MSG10");
        return null;
      } else {
        String history = getHistory(candidateId, employeeId);
        if (history == null) {
          messageVo.setMessageOfCandidate("MSG5");
          return null;
        }
        Channel channel = channelRepository.findByChannelId(channelId);
        if (channel == null) {
          messageVo.setMessageOfCandidate("MSG5");
          return null;
        } else {
          Location location = locationEpository.findByLocationId(siteId);
          if (location == null) {
            messageVo.setMessageOfCandidate("MSG5");
            return null;
          } else {
            Candidate candidate = new Candidate(candidateId,
                applicationDateConverted, status, history, channel, location);
            return candidate;
          }
        }
      }
    }
  }

  /**
   * this function to get history value for candidate.
   * 
   * @param candidateId - candidate id to determind candidate
   * @param employeeId  - employee id to determaind user logined to system
   * @return
   */
  public String getHistory(int candidateId, int employeeId) {
    User user = userRepository.findById(employeeId);
    if (user == null) {
      return null;
    } else {
      Candidate candidate = candidateRepository.findByCandidateID(candidateId);
      if (candidate == null) {
        return CandidateUtils.createHistory(user.getFullName(), "Created by");
      } else {
        StringBuilder history = new StringBuilder();
        history.append(candidate.getHistory() + "<br />");
        history.append(
            CandidateUtils.createHistory(user.getFullName(), "Updated by"));
        return history.toString();
      }
    }
  }

  /**
   * the fuction to check role of user.
   * 
   * @param role - param take off from user
   * @return
   */
  private String checkRole(Set<String> roles) {
    for (Iterator<String> iterator = roles.iterator(); iterator.hasNext();) {
      String temp = iterator.next();
      if ("FA Manager".equals(temp) == true
          || "System Admin".equals(temp) == true
          || "ROLE_ADMIN".equals(temp) == true) {
        return "success";
      }
    }
    return "fail";
  }

  /**
   * the fuction to check candidate is null.
   * 
   * @param candidateId - param take off from view
   * @return
   */
  private Candidate checkCandidate(int candidateId) {
    List<Candidate> listOfCandidate = candidateRepository.findAll();
    int sizeOfListCandidate = listOfCandidate.size();
    Candidate candidate = new Candidate();
    if (candidateId != 0) {
      for (int i = 0; i < sizeOfListCandidate; i++) {
        if (candidateId == listOfCandidate.get(i).getCandidateID()) {
          candidate = listOfCandidate.get(i);
        }
      }
      if (candidate.getCandidateID() == 0) {
        return null;
      }
    }
    return candidate;
  }

  @Override
  public List<Candidate> getAllCandidate() {
    List<Candidate> listCandidates = candidateRepository.findAll();
    List<Candidate> candidates = new ArrayList<>();
    for (Candidate candidate : listCandidates) {
      if (!candidate.getStatus().equalsIgnoreCase("inactive")) {
        candidates.add(candidate);
      }
    }
    return candidates;
  }

  @PreAuthorize("hasRole('ADMIN')")
  @Override
  public boolean saveCandidate(Candidate candidate) {
    return candidateRepository.save(candidate) != null;
  }
}