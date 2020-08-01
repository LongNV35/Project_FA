package fa.fams.web.controller;

import java.util.List;
import java.util.Set;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import fa.fams.common.exception.ParamNotFoundException;
import fa.fams.common.log.Log4J;
import fa.fams.common.utility.CandidateUtils;
import fa.fams.common.utility.DateUtils;
import fa.fams.common.utility.MessagesList;
import fa.fams.common.valueobjects.MessageVo;
import fa.fams.model.Candidate;
import fa.fams.model.CandidateProfile;
import fa.fams.model.Channel;
import fa.fams.model.EntryTest;
import fa.fams.model.Faculty;
import fa.fams.model.InterView;
import fa.fams.model.Location;
import fa.fams.model.University;
import fa.fams.service.serviceimpl.CandidateProfileServiceImpl;
import fa.fams.service.serviceimpl.CandidateServiceImpl;
import fa.fams.service.serviceimpl.ChannelServiceImpl;
import fa.fams.service.serviceimpl.ClassAdminServiceImpl;
import fa.fams.service.serviceimpl.EntryTestServiceImpl;
import fa.fams.service.serviceimpl.FacultyServiceImpl;
import fa.fams.service.serviceimpl.InterViewServiceImpl;
import fa.fams.service.serviceimpl.LocationServiceImpl;
import fa.fams.service.serviceimpl.UniversityServiceImpl;
import fa.fams.service.serviceimpl.UserServiceImpl;

/**
 * this class is a candidate controller. Control to sent and receive data.
 * bettween client and server.
 *
 */
@Controller
@PropertySource(value = { "classpath:messages.properties" })
public class CandidateController {
  @Autowired
  private CandidateServiceImpl candidateServiceImpl;

  @Autowired
  private EntryTestServiceImpl entryTestServiceImpl;

  @Autowired
  private InterViewServiceImpl interViewServiceImpl;

  @Autowired
  private ChannelServiceImpl channelServiceImpl;

  @Autowired
  private CandidateProfileServiceImpl candidateProfileServiceImpl;

  @Autowired
  private LocationServiceImpl locationServiceImpl;

  @Autowired
  private UniversityServiceImpl universityServiceImpl;

  @Autowired
  private FacultyServiceImpl facultyServiceImpl;

  @Autowired
  ClassAdminServiceImpl classAdminServiceImpl;

  @Autowired
  UserServiceImpl userServiceImpl;

  /**
   * viewCandidateListing return a view candidate listing.
   * 
   * @param model - Add data to the model so that the view can be retrieved and
   *              used.
   * 
   * @return: the candidate listing
   */
  @RequestMapping(value = "/listcandidate", method = RequestMethod.GET)
  public String viewCandidateListing(Model model) {
    List<Candidate> candidates = candidateServiceImpl.getAllCandidate();
    model.addAttribute("candidates", candidates);
    return "candidate_listing";
  }

  @RequestMapping(value = "/getUserNameCount", method = RequestMethod.POST)
  @ResponseBody
  public int getUserNameCount(
      @RequestParam("userNameTemp") String userNameTemp) {
    int count = 1;
    List<CandidateProfile> candidateProfiles = candidateProfileServiceImpl
        .findByAccountStartsWith(userNameTemp);
    count = candidateProfiles.size();
    return count + 1;
  }

  /**
   * The fuction to display candidate's information.
   * 
   * @param candidateId - param when user click in employeeId
   * @param model       - param take off attibute
   * @param request     - param to setup param to view
   * @return
   */
  @RequestMapping(value = "/viewcandidate", method = RequestMethod.GET)
  public String viewCandidateProfile(
      @RequestParam(required = false, name = "candidateId") String candidateId,
      Model model, HttpServletRequest request) {
    Candidate candidate = candidateServiceImpl
        .findByCandidateId(Integer.parseInt(candidateId));
    if (candidate == null || candidate.getCandidateProfile() == null) {
      request.getSession(false).setAttribute("view_candidate_message", false);
      List<Candidate> candidates = candidateServiceImpl.getAllCandidate();
      model.addAttribute("candidates", candidates);
      return "candidate_listing";
    }
    setModel(model, candidate);
    return "view_candidate_profile";
  }

  /**
   * this fuction to tranfer candidate to trainee.
   * 
   * @param candidateID - param when user see candidate's information
   * 
   * @param level       - param when user choose position for candidate
   * 
   * @param model       - param to send data from controller to view
   * 
   * @param request     - param to send data from controller to view
   * 
   * @param session     - param to take data from difference controller
   * 
   * @return
   * 
   * @throws MessagingException - exception can be met when send email
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(value = "/tranfer", method = RequestMethod.POST)
  public String tranferCandidate(
      @RequestParam(required = false, name = "candidateId") String candidateID,
      @RequestParam(required = false, name = "level") String level, Model model,
      HttpServletRequest request, HttpSession session)
      throws MessagingException {
    int candidateId = Integer.parseInt(candidateID);
    Candidate candidate = candidateServiceImpl.findByCandidateId(candidateId);
    Set<String> roles = (Set<String>) session.getAttribute("roles");
    if ("success".equals(candidateServiceImpl.tranferCandidate(candidateId,
        roles, level)) == true) {
      request.getSession(false).setAttribute("view_candidate_message", true);
      List<Candidate> candidates = candidateServiceImpl.getAllCandidate();
      model.addAttribute("candidates", candidates);
      return "candidate_listing";
    }
    setModel(model, candidate);
    request.getSession(false).setAttribute("tranfer_message", true);
    return "view_candidate_profile";
  }

  /**
   * the fuction to send params to view.
   * 
   * @param model     - param to send data from controller to view
   * 
   * @param candidate - param to send data from controller to view
   */
  private void setModel(Model model, Candidate candidate) {
    model.addAttribute("candidate", candidate);
    model.addAttribute("applicationDate",
        DateUtils.formatDate2(candidate.getApplicationDate()));
    model.addAttribute("dateOfBirth", DateUtils
        .formatDate2(candidate.getCandidateProfile().getDateOfBirth()));
    model.addAttribute("graduationYear", DateUtils
        .formatDate1(candidate.getCandidateProfile().getGraduationYear()));
  }

  /**
   * this fuction to catch exception when run app.
   * 
   * @param request - param to send data from controller to view
   * 
   * @return
   */
  @ExceptionHandler(ParamNotFoundException.class)
  public ModelAndView handleUserNotFoundException(HttpServletRequest request,
      Exception exception) {

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("exception", exception);
    modelAndView.addObject("url", request.getRequestURL());

    modelAndView.setViewName("error");
    return modelAndView;
  }

  /**
   * updateStatusInActive() used to change the status of a candidate from.
   * active to inactive
   * 
   * @param listCandidateID - receive a list of candidateIDs from the view
   * 
   * @return: the list candidate page
   */
  @RequestMapping(value = "/deleteCandidate", method = RequestMethod.POST)
  public String deleteCandidate(@RequestBody List<Integer> listCandidateID,
      Model model, HttpServletRequest request) {
    if (!listCandidateID.isEmpty()) {
      for (Integer candidateID : listCandidateID) {
        candidateServiceImpl.updateStatusInActive(candidateID);
      }
      request.getSession(false).setAttribute("deleteCandidateMSG", true);

    } else {
      Log4J.getLogger().info(MessagesList.MSG32);
    }

    return "redirect:/listcandidate";
  }

  /**
   * this function to view create candidate page.
   * 
   * @param model - stored object from controller to view
   * @return - redirect to create candidate view page
   */
  @RequestMapping(value = "/viewCreateCandidate", method = RequestMethod.GET)
  public String viewCreateCandidatePage(Model model) {
    List<Channel> channels = channelServiceImpl.findAll();
    model.addAttribute("channels", channels);
    List<Location> locations = locationServiceImpl.findAll();
    model.addAttribute("locations", locations);
    List<University> universities = universityServiceImpl.findAll();
    model.addAttribute("universities", universities);
    List<Faculty> faculties = facultyServiceImpl.findAll();
    model.addAttribute("faculties", faculties);
    return "create_candidate_profile";
  }

  /**
   * this function to save a candidate from view.
   * 
   * @param file                - file cv of user when submit form
   * @param employeeId          - id of user logined
   * @param type                - type value when submit form
   * @param status              - status value when submit form
   * @param applicationDate     - date now of system when submit form
   * @param channelId           - channel id when submit form
   * @param siteId              - site id when submit form
   * @param account             - account name when submit form
   * @param name                - name of candidate when submit form
   * @param gender              - gender of candidate when submit form
   * @param dateOfBirth         - date of birth of candidate when submit form
   * @param universityId        - university id when submit form
   * @param universityOtherName - university name user custom when submit form
   * @param facultyId           - faculty id when submit form
   * @param facultyOtherName    - faculty name user custom when submit form
   * @param phone               - phone of candidate when submit form
   * @param email               - email of candidate when submit form
   * @param skill               - skill of candidate when submit form
   * @param gradurationYear     - graduration year of candidate when submit form
   * @param foreignLanguage     - foreign language of candidate when submit form
   * @param level               - level of candidate when submit form
   * @param note                - note value when submit form
   * @param listEntryTest       - list entry test of result tab
   * @param listInterview       - list interview of result tab
   * @param request             - httpRequest object
   * @return - return a name of view page
   * @throws Exception - exception can happen
   */
  @RequestMapping(value = "/createCandidate", method = RequestMethod.POST)
  public String createCandidate(@RequestParam("cv") MultipartFile file,
      @RequestParam int employeeId, @RequestParam("type") String type,
      @RequestParam String status, @RequestParam String applicationDate,
      @RequestParam("channel_dropdown") int channelId,
      @RequestParam("site_dropdown") int siteId, @RequestParam String account,
      @RequestParam String name, @RequestParam boolean gender,
      @RequestParam String dateOfBirth, @RequestParam int universityId,
      @RequestParam(required = false) String universityOtherName,
      @RequestParam int facultyId,
      @RequestParam(required = false) String facultyOtherName,
      @RequestParam String phone, @RequestParam String email,
      @RequestParam String skill, @RequestParam String gradurationYear,
      @RequestParam String foreignLanguage, @RequestParam String level,
      @RequestParam String note, @RequestParam List<String> listEntryTest,
      @RequestParam List<String> listInterview, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    MessageVo messageVo = new MessageVo();
    if ((CandidateUtils.isEmptyOrNull(status))
        || (CandidateUtils.isEmptyOrNull(applicationDate))
        || (CandidateUtils.isEmptyOrNull(name))
        || (CandidateUtils.isEmptyOrNull(dateOfBirth))
        || (CandidateUtils.isEmptyOrNull(phone))
        || (CandidateUtils.isEmptyOrNull(email))
        || (CandidateUtils.isEmptyOrNull(gradurationYear))
        || (universityId < 0
            && CandidateUtils.isEmptyOrNull(universityOtherName))
        || (facultyId < 0 && CandidateUtils.isEmptyOrNull(facultyOtherName))) {
      messageVo.setMessageOfCandidate("MSG5");
      session.setAttribute("message", messageVo);
      return "redirect:/viewCreateCandidate";
    } else {
      CandidateProfile candidateProfile = candidateProfileServiceImpl.create(
          applicationDate, status, employeeId, channelId, siteId, name, account,
          dateOfBirth, gender, gradurationYear, phone, email, type, skill,
          foreignLanguage, level, file, note, universityId, universityOtherName,
          facultyId, facultyOtherName, messageVo);
      session.setAttribute("message", messageVo);
      if (candidateProfile == null) {
        return "redirect:/viewCreateCandidate";
      } else {
        Candidate candidate = candidateProfile.getCandidate();
        entryTestServiceImpl.saveOrUpdateWithCandidate(listEntryTest, candidate,
            employeeId, messageVo);
        session.setAttribute("message", messageVo);
        interViewServiceImpl.saveOrUpdateWithCandidate(listInterview, candidate,
            employeeId, messageVo);
        session.setAttribute("message", messageVo);
        return "redirect:/viewCreateCandidate";
      }
    }
  }

  /**
   * this function to save a candidate from view.
   * 
   * @param file                - file cv of user when submit form
   * @param employeeId          - id of user logined
   * @param type                - type value when submit form
   * @param status              - status value when submit form
   * @param applicationDate     - date now of system when submit form
   * @param channelId           - channel id when submit form
   * @param siteId              - site id when submit form
   * @param account             - account name when submit form
   * @param name                - name of candidate when submit form
   * @param gender              - gender of candidate when submit form
   * @param dateOfBirth         - date of birth of candidate when submit form
   * @param universityId        - university id when submit form
   * @param universityOtherName - university name user custom when submit form
   * @param facultyId           - faculty id when submit form
   * @param facultyOtherName    - faculty name user custom when submit form
   * @param phone               - phone of candidate when submit form
   * @param email               - email of candidate when submit form
   * @param skill               - skill of candidate when submit form
   * @param gradurationYear     - graduration year of candidate when submit form
   * @param foreignLanguage     - foreign language of candidate when submit form
   * @param level               - level of candidate when submit form
   * @param note                - note value when submit form
   * @param listEntryTest       - list entry test of result tab
   * @param listInterview       - list interview of result tab
   * @param candidateId         - candidate id for case update infomation
   * @param request             - httpRequest object
   * @return - return a name of view page
   * @throws Exception - exception can happen
   */
  @RequestMapping(value = "/updateCandidate", method = RequestMethod.POST)
  public String updateCandidate(@RequestParam("cv") MultipartFile file,
      @RequestParam int employeeId, @RequestParam("type") String type,
      @RequestParam String status, @RequestParam String applicationDate,
      @RequestParam("channel_dropdown") int channelId,
      @RequestParam("site_dropdown") int siteId, @RequestParam String account,
      @RequestParam String name, @RequestParam boolean gender,
      @RequestParam String dateOfBirth, @RequestParam int universityId,
      @RequestParam(required = false) String universityOtherName,
      @RequestParam int facultyId,
      @RequestParam(required = false) String facultyOtherName,
      @RequestParam String phone, @RequestParam String email,
      @RequestParam String skill, @RequestParam String gradurationYear,
      @RequestParam String foreignLanguage, @RequestParam String level,
      @RequestParam String note, @RequestParam List<String> listEntryTest,
      @RequestParam List<String> listInterview,
      @RequestParam(required = false) int candidateId,
      HttpServletRequest request) throws Exception {
    HttpSession session = request.getSession(false);
    MessageVo messageVo = new MessageVo();
    if ((CandidateUtils.isEmptyOrNull(status))
        || (CandidateUtils.isEmptyOrNull(applicationDate))
        || (CandidateUtils.isEmptyOrNull(name))
        || (CandidateUtils.isEmptyOrNull(dateOfBirth))
        || (CandidateUtils.isEmptyOrNull(phone))
        || (CandidateUtils.isEmptyOrNull(email))
        || (CandidateUtils.isEmptyOrNull(gradurationYear))
        || (universityId < 0
            && CandidateUtils.isEmptyOrNull(universityOtherName))
        || (facultyId < 0 && CandidateUtils.isEmptyOrNull(facultyOtherName))) {
      messageVo.setMessageOfCandidate("MSG5");
      session.setAttribute("message", messageVo);
      return "redirect:/listcandidate";
    } else {
      CandidateProfile candidateProfile = candidateProfileServiceImpl.update(
          candidateId, applicationDate, status, employeeId, channelId, siteId,
          name, account, dateOfBirth, gender, gradurationYear, phone, email,
          type, skill, foreignLanguage, level, file, note, universityId,
          universityOtherName, facultyId, facultyOtherName, messageVo);
      session.setAttribute("message", messageVo);
      if (candidateProfile == null) {
        return "redirect:/listcandidate";
      } else {
        Candidate candidate = candidateProfile.getCandidate();
        entryTestServiceImpl.saveOrUpdateWithCandidate(listEntryTest, candidate,
            employeeId, messageVo);
        session.setAttribute("message", messageVo);
        interViewServiceImpl.saveOrUpdateWithCandidate(listInterview, candidate,
            employeeId, messageVo);
        session.setAttribute("message", messageVo);
        return "redirect:/listcandidate";
      }
    }
  }

  /**
   * this function to view update candidate page.
   * 
   * @param model             - stored object from controller to view
   * @param candidateIdString - candidate id for update be sent from view
   * @return - redirect to update candidate view page
   */
  @RequestMapping(value = "/viewUpdateCandidate", method = RequestMethod.POST)
  public String viewUpdateCandidatePage(Model model,
      @RequestParam("candidateId") String candidateIdString) {
    int candidateId = Integer.parseInt(candidateIdString);
    Candidate candidate = candidateServiceImpl.findByCandidateId(candidateId);
    model.addAttribute("candidate", candidate);
    CandidateProfile candidateProfile = candidateProfileServiceImpl
        .findByCandidateProfileId(candidateId);
    model.addAttribute("candidateProfile", candidateProfile);
    List<EntryTest> entryTests = entryTestServiceImpl
        .getAllByCandidateId(candidateId);
    model.addAttribute("entryTests", entryTests);
    List<InterView> interViews = interViewServiceImpl
        .getAllByCandidateId(candidateId);
    model.addAttribute("interViews", interViews);
    List<Channel> channels = channelServiceImpl.findAll();
    model.addAttribute("channels", channels);
    List<Location> locations = locationServiceImpl.findAll();
    model.addAttribute("locations", locations);
    List<University> universities = universityServiceImpl.findAll();
    model.addAttribute("universities", universities);
    List<Faculty> faculties = facultyServiceImpl.findAll();
    model.addAttribute("faculties", faculties);
    return "update_candidate_profile";
  }
}