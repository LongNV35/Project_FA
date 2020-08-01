package fa.fams.service.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import fa.fams.common.valueobjects.DashboardVo;
import fa.fams.common.valueobjects.MessageVo;
import fa.fams.model.CandidateProfile;

public interface CandidateProfileService {

  List<DashboardVo> countCandidateByStatusLocation(String status,
      String location);

  ArrayList<List<DashboardVo>> getListsCountCandidateByStatusLocation(
      String status, String location);

  CandidateProfile findByCandidateProfileId(int id);

  List<CandidateProfile> findByAccountStartsWith(String account);

  CandidateProfile create(String applicationDate, String status, int employeeId,
      int channelId, int siteId, String name, String account,
      String dateOfBirth, boolean gender, String gradurationYear, String phone,
      String email, String type, String skill, String foreignLanguage,
      String level, MultipartFile file, String note, int universityId,
      String universityOtherName, int facultyId, String facultyOtherName,
      MessageVo messageVo);

  CandidateProfile update(int candidateId, String applicationDate,
      String status, int employeeId, int channelId, int siteId, String name,
      String account, String dateOfBirth, boolean gender,
      String gradurationYear, String phone, String email, String type,
      String skill, String foreignLanguage, String level, MultipartFile file,
      String note, int universityId, String universityOtherName, int facultyId,
      String facultyOtherName, MessageVo messageVo);
}