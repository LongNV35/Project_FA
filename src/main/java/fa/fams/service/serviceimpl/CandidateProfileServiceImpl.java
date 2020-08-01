package fa.fams.service.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContext;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import fa.fams.common.log.Log4J;
import fa.fams.common.utility.Constant;
import fa.fams.common.utility.DateUtils;
import fa.fams.common.valueobjects.DashboardVo;
import fa.fams.common.valueobjects.MessageVo;
import fa.fams.model.Candidate;
import fa.fams.model.CandidateProfile;
import fa.fams.model.Faculty;
import fa.fams.model.University;
import fa.fams.repository.CandidateProfileRepository;
import fa.fams.repository.CandidateRepository;
import fa.fams.repository.FacultyRepository;
import fa.fams.repository.UniversityRepository;
import fa.fams.service.service.CandidateProfileService;

/**
 * this class is a candidate profile service. Validate and process candidate
 * profile info bettween controller and repository
 *
 */
@Service
public class CandidateProfileServiceImpl implements CandidateProfileService {
  private ArrayList<List<DashboardVo>> listDashboardVos;
  @Autowired
  private CandidateProfileRepository candidateProfileRepository;
  @Autowired
  private UniversityRepository universityRepository;
  @Autowired
  private FacultyRepository facultyRepository;
  @Autowired
  ServletContext servletContext;
  @Autowired
  CandidateServiceImpl candidateServiceImpl;
  @Autowired
  private CandidateRepository candidateRepository;

  @Override
  public List<CandidateProfile> findByAccountStartsWith(String account) {
    return candidateProfileRepository.findByAccountStartsWith(account);
  }

  @Override
  public CandidateProfile findByCandidateProfileId(int id) {
    return candidateProfileRepository.findByCandidateProfileId(id);
  }

  /**
   * this function to get a candidate profile object from paramaters.
   * 
   * @param name                - name of candidate
   * @param account             - account name
   * @param dateOfBirth         - date of birth of candidate
   * @param gender              - gender of candidate
   * @param gradurationYear     - graduration year of candidate
   * @param phone               - phone of candidate
   * @param email               - email of candidate
   * @param type                - type value
   * @param skill               - skill of candidate
   * @param foreignLanguage     - foreign language of candidate
   * @param level               - level of candidate
   * @param file                - file cv of user
   * @param status              - status value
   * @param note                - note value
   * @param universityId        - university id of candidate
   * @param universityOtherName - university name user custom
   * @param facultyId           - faculty id of candidate
   * @param facultyOtherName    - faculty name user custom
   * @param candidate           - containe a candidate
   * @param messages            - list message status
   * @return - a candidate profile object
   */
  private CandidateProfile convertToCandidateProfile(String name,
      String account, String dateOfBirth, boolean gender,
      String gradurationYear, String phone, String email, String type,
      String skill, String foreignLanguage, String level, MultipartFile file,
      String status, String note, int universityId, String universityOtherName,
      int facultyId, String facultyOtherName, Candidate candidate,
      MessageVo messageVo) {
    LocalDate dateConverted = DateUtils.convertToLocalDate1(dateOfBirth,
        DateUtils.getDateTimeFormater1());
    if (dateConverted == null) {
      messageVo.setMessageOfCandidate("MSG5");
      return null;
    } else {
      if (LocalDate.now().compareTo(dateConverted) == -1) {
        messageVo.setMessageOfCandidate("MSG10");
        return null;
      } else {
        LocalDate gradurationYearConverted = getGradurationYear(
            gradurationYear);
        if (gradurationYearConverted == null) {
          messageVo.setMessageOfCandidate("MSG5");
          return null;
        } else {
          String cvName = getFileName(candidate.getCandidateID(), file);
          University university = getUniversity(universityId,
              universityOtherName);
          if (university == null) {
            messageVo.setMessageOfCandidate("MSG5");
            return null;
          }
          Faculty faculty = getFaculty(facultyId, facultyOtherName);
          if (faculty == null) {
            messageVo.setMessageOfCandidate("MSG5");
            return null;
          }
          CandidateProfile candidateProfile = new CandidateProfile(
              candidate.getCandidateID(), name, account, dateConverted, gender,
              gradurationYearConverted, phone, email, type, skill,
              foreignLanguage, level, cvName, status, note, university, faculty,
              candidate);
          return candidateProfile;
        }
      }
    }
  }

  /**
   * this function get file name from multipart file param.
   * 
   * @param candidateId - get previous cv name if case update
   * @param file        - multipart file to get file name
   * @return
   */
  public String getFileName(int candidateId, MultipartFile file) {
    if (!file.isEmpty()) {
      return file.getOriginalFilename();
    } else {
      CandidateProfile candidateProfile = candidateProfileRepository
          .findByCandidateProfileId(candidateId);
      if (candidateProfile != null) {
        return candidateProfile.getCv();
      } else {
        return "";
      }
    }
  }

  /**
   * this function convert param to a local graduration year.
   * 
   * @param gradurationYear - param type String
   * @return
   */
  public LocalDate getGradurationYear(String gradurationYear) {
    LocalDate gradurationYearConverted = DateUtils
        .convertToLocalDate1(gradurationYear, DateUtils.getDateTimeFormater1());
    if (gradurationYearConverted == null) {
      return null;
    } else {
      if (LocalDate.now().compareTo(gradurationYearConverted) == -1) {
        return null;
      } else {
        return gradurationYearConverted;
      }
    }
  }

  /**
   * this function to get a university from DB or insert and then get a
   * university from DB.
   * 
   * @param universityId        - university id to get
   * @param universityOtherName - university name custom for insert to DB
   * @return
   */
  public University getUniversity(int universityId,
      String universityOtherName) {
    University university = universityRepository
        .findByUniversityId(universityId);
    if (university != null) {
      return university;
    } else {
      try {
        return universityRepository.save(new University(universityOtherName));
      } catch (ConstraintViolationException e) {
        Log4J.getLogger().error(e.getMessage());
        return null;
      }
    }
  }

  /**
   * this function to get a faculty from DB or insert and then get a faculty
   * from DB.
   * 
   * @param facultyId        - faculty id to get
   * @param facultyOtherName - faculty name custom for insert to DB
   * @return
   */
  private Faculty getFaculty(int facultyId, String facultyOtherName) {
    Faculty faculty = facultyRepository.findByFacultyId(facultyId);
    if (faculty != null) {
      return faculty;
    } else {
      try {
        return facultyRepository.save(new Faculty(facultyOtherName));
      } catch (HibernateException e) {
        return null;
      }
    }
  }

  /**
   * this function to save a file to disk with dynamic path.
   * 
   * @param file - multipart file be sent from view page to controller
   * @throws Exception - exception
   */
  public boolean saveFileToDisk(MultipartFile file) {
    if (!file.isEmpty()) {
      File f = new File(
          servletContext.getRealPath("/") + "WEB-INF\\pageResources\\cv\\",
          file.getOriginalFilename());
      try {
        file.transferTo(f);
        return true;
      } catch (IllegalStateException | IOException e) {
        Log4J.getLogger().error(e.getMessage());
        return false;
      }
    }
    return false;
  }

  /**
   * the function to find DashboardVo by status and location.
   * 
   * @author minhlv6
   * @param status   - status of candidate
   * @param location - location of candidate
   * @return - return list DashboardVo
   */
  @Override
  public List<DashboardVo> countCandidateByStatusLocation(String status,
      String location) {
    if ("All".equalsIgnoreCase(location)) {
      location = "%%";
    }
    List<DashboardVo> listDashboardVo = new ArrayList<DashboardVo>();
    DashboardVo dashboardVo = null;
    for (Object[] ele : candidateProfileRepository
        .countCandidateByStatusLocation(status, location)) {
      dashboardVo = new DashboardVo(status, ele[0].toString(),
          Long.valueOf(ele[1].toString()));
      listDashboardVo.add(dashboardVo);
    }
    if (listDashboardVo.size() == 0) {
      listDashboardVo.add(new DashboardVo(status, "Empty", 0));
    }
    return listDashboardVo;
  }

  /**
   * the function to get all lists DashboardVo if status= "All", else get list
   * by status of candidate.
   * 
   * @author minhlv6
   * @param status   - status of candidate
   * @param location - location of candidate
   * @return - return lists DashboardVo
   */
  @Override
  public ArrayList<List<DashboardVo>> getListsCountCandidateByStatusLocation(
      String status, String location) {
    listDashboardVos = new ArrayList<List<DashboardVo>>();
    if ("All".equalsIgnoreCase(status)) {
      List<String> listNameOfStatus = Arrays
          .asList(Constant.listStatusOfCandidate);
      listNameOfStatus.forEach(x -> listDashboardVos
          .add(countCandidateByStatusLocation(x, location)));
    } else {
      listDashboardVos.add(countCandidateByStatusLocation(status, location));
    }
    return listDashboardVos;
  }

  /**
   * this function to create a candidate profile to DB from paramaters.
   */
  @Override
  public CandidateProfile create(String applicationDate, String status,
      int employeeId, int channelId, int siteId, String name, String account,
      String dateOfBirth, boolean gender, String gradurationYear, String phone,
      String email, String type, String skill, String foreignLanguage,
      String level, MultipartFile file, String note, int universityId,
      String universityOtherName, int facultyId, String facultyOtherName,
      MessageVo messageVo) {
    Candidate candidate = candidateServiceImpl.convertToCandidate(-1,
        applicationDate, status, employeeId, channelId, siteId, messageVo);
    if (candidate == null) {
      return null;
    } else {
      CandidateProfile candidateProfile = this.convertToCandidateProfile(name,
          account, dateOfBirth, gender, gradurationYear, phone, email, type,
          skill, foreignLanguage, level, file, status, note, universityId,
          universityOtherName, facultyId, facultyOtherName, candidate,
          messageVo);
      if (candidateProfile == null) {
        return null;
      } else {
        try {
          candidateProfile = candidateProfileRepository.save(candidateProfile);
          if (candidateProfile == null) {
            messageVo.setMessageOfCandidate("MSG35");
          } else {
            messageVo.setMessageOfCandidate("MSG23");
          }
          saveFileToDisk(file);
          return candidateProfile;
        } catch (DataIntegrityViolationException | HibernateException e) {
          Log4J.getLogger().error(e.getMessage());
          messageVo.setMessageOfCandidate("MSG35");
          return null;
        }
      }
    }
  }

  /**
   * this function to update a candidate profile to DB from paramaters.
   */
  @Override
  public CandidateProfile update(int candidateId, String applicationDate,
      String status, int employeeId, int channelId, int siteId, String name,
      String account, String dateOfBirth, boolean gender,
      String gradurationYear, String phone, String email, String type,
      String skill, String foreignLanguage, String level, MultipartFile file,
      String note, int universityId, String universityOtherName, int facultyId,
      String facultyOtherName, MessageVo messageVo) {
    if (candidateRepository.findByCandidateID(candidateId) == null) {
      messageVo.setMessageOfCandidate("MSG36");
      return null;
    }
    Candidate candidate = candidateServiceImpl.convertToCandidate(candidateId,
        applicationDate, status, employeeId, channelId, siteId, messageVo);
    if (candidate == null) {
      return null;
    } else {
      CandidateProfile candidateProfile = this.convertToCandidateProfile(name,
          account, dateOfBirth, gender, gradurationYear, phone, email, type,
          skill, foreignLanguage, level, file, status, note, universityId,
          universityOtherName, facultyId, facultyOtherName, candidate,
          messageVo);
      if (candidateProfile == null) {
        return null;
      } else {
        try {
          candidateProfile = candidateProfileRepository.save(candidateProfile);
          if (candidateProfile == null) {
            messageVo.setMessageOfCandidate("MSG36");
          } else {
            messageVo.setMessageOfCandidate("MSG22");
          }
          saveFileToDisk(file);
          return candidateProfile;
        } catch (DataIntegrityViolationException | HibernateException e) {
          Log4J.getLogger().error(e.getMessage());
          messageVo.setMessageOfCandidate("MSG36");
          return null;
        }
      }
    }
  }
}