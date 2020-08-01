package fa.fams.web.controller;

import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import fa.fams.service.serviceimpl.UserServiceImpl;

@Controller
public class LoginController {

  @Autowired
  UserServiceImpl userServiceImpl;

  /**
   * method homePage return the main page.
   * 
   * @param model          - Add data to the model so that the view can be
   *                       retrieved and used
   * @param authentication - authenticate and retrieve user rights
   * @return: the main page
   */
  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public String homePage(Model model, Authentication authentication,
      HttpSession session) {
    String userName = getPrincipal();
    int id;
    id = userServiceImpl.findByUserName(userName).getId();
    model.addAttribute("principal", userName);
    authentication = SecurityContextHolder.getContext().getAuthentication();
    Set<String> roles = authentication.getAuthorities().stream()
        .map(r -> r.getAuthority()).collect(Collectors.toSet());
    model.addAttribute("roles", roles);
    session.setAttribute("roles", roles);
    session.setAttribute("employeeId", id);
    return "main";
  }

  @RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
  public String loginPage() {
    return "login";
  }

  /**
   * method getPrincipal help us get the authenticated principal's userName.
   * 
   * @return: the userName used in the authentication process
   */
  public String getPrincipal() {
    String userName = null;
    Object principal = SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    if (principal instanceof UserDetails) {
      userName = ((UserDetails) principal).getUsername();
    } else {
      userName = principal.toString();
    }
    return userName;
  }

  /**
   * The logout method is used to log out the account and return to the login
   * page.
   * 
   * @param request  - It is an object to receive requests from the client
   * 
   * @param response - It is an object to handle the response returned to the
   *                 user
   * 
   * @return: the login page
   */
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout(HttpServletRequest request,
      HttpServletResponse response) {
    Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
    if (authentication != null) {
      new SecurityContextLogoutHandler().logout(request, response,
          authentication);
    }
    return "redirect:/login";
  }
}
