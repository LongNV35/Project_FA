package fa.fams.service.serviceimpl.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fa.fams.common.log.Log4J;
import fa.fams.model.Candidate;
import fa.fams.model.Channel;
import fa.fams.model.Location;
import fa.fams.model.Role;
import fa.fams.model.User;
import fa.fams.repository.CandidateRepository;
import fa.fams.repository.UserRepository;
import fa.fams.service.service.CustomUserDetailsService;
import fa.fams.service.serviceimpl.CandidateServiceImpl;
import fa.fams.service.serviceimpl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ActiveProfiles("test")
public class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  CandidateRepository candidateRepository;

  @Autowired
  private CandidateServiceImpl candidateService;

  @Autowired
  CustomUserDetailsService userDetailsService;

  @BeforeEach
  public void setUp() throws Exception {

  }

  /**
   * Author: LongNV35
   * 
   * Test nomal case
   * 
   * @throws Exception: If the user already exists
   */
  @Test
  public void testCreateUser() throws Exception {

    Role role = new Role();
    role.setId(2);
    Role role2 = new Role();
    role2.setId(1);

    List<Role> roles = new ArrayList<>();
    roles.add(role);
    roles.add(role2);

    User user = new User("test2", new BCryptPasswordEncoder().encode("12345"),
        "test2");
    user.setRoles(roles);

    when(userRepository.save(user)).thenReturn(user);

    boolean actual = userService.createUser(user);
    Log4J.getLogger().info(user);

    assertTrue(actual);
  }

  /**
   * Author: LongNV35
   * 
   * Test abnomal case
   * 
   * @throws Exception: If the user already exists
   */
  @Test
  public void testCreateUser2() throws Exception {

    Role role = new Role();
    role.setId(2);
    Role role2 = new Role();
    role2.setId(1);

    List<Role> roles = new ArrayList<>();
    roles.add(role);
    roles.add(role2);

    User user = new User("test2", new BCryptPasswordEncoder().encode("12345"),
        "test2");
    user.setRoles(roles);

//    when(userRepository.save(user)).thenReturn(user);

    boolean actual = userService.createUser(user);
    Log4J.getLogger().info(user);

    assertTrue(actual);
  }

  /**
   * author: LongNV35
   * 
   * Test case normal
   * 
   * Test the valid user with valid role
   */

  @org.junit.Test
  @WithMockUser(username = "dieunt1", roles = "ADMIN")
  public void testValidRole() {

    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDate localDate = LocalDate.parse("2020-10-10", pattern);

    Channel channel = new Channel();
    channel.setChannelId(1);

    Location location = new Location();
    location.setLocationId(1);

    Candidate candidate = new Candidate(localDate, "New", "abc", channel,
        location);

//    when(candidateRepository.save(candidate)).thenReturn(candidate);

    boolean actual = candidateService.saveCandidate(candidate);

    Log4J.getLogger().info(candidate);
    Log4J.getLogger().info(actual);
    assertTrue(actual);

  }

  /**
   * author: LongNV35
   * 
   * Test abnormal case
   * 
   * Test the valid user with invalid role
   */
  @org.junit.Test(expected = AccessDeniedException.class)
  @WithMockUser(username = "longnv35", password = "12345", roles = "USER")
  public void testInValidRole() {

    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDate localDate = LocalDate.parse("2020-10-10", pattern);

    Channel channel = new Channel();
    channel.setChannelId(1);

    Location location = new Location();
    location.setLocationId(1);

    Candidate candidate = new Candidate(localDate, "New", "abc", channel,
        location);

//    when(candidateRepository.save(candidate)).thenReturn(candidate);

    boolean actual = candidateService.saveCandidate(candidate);

//    Log4J.getLogger().info(userDetails);
    Log4J.getLogger().info(candidate);
    Log4J.getLogger().info(actual);
//    verify(candidateRepository, times(1)).save(candidate);
    assertTrue(actual);

  }

  /**
   * author: LongNV35
   * 
   * Test abnormal case
   * 
   * Test the INVALID user
   */
  @org.junit.Test(expected = AccessDeniedException.class)
  @WithMockUser(username = "user", password = "12345", roles = "USER")
  public void testInvalidUser() {

    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDate localDate = LocalDate.parse("2020-10-10", pattern);

    Channel channel = new Channel();
    channel.setChannelId(1);

    Location location = new Location();
    location.setLocationId(1);

    Candidate candidate = new Candidate(localDate, "New", "abc", channel,
        location);

//    when(candidateRepository.save(candidate)).thenReturn(candidate);

    boolean actual = candidateService.saveCandidate(candidate);

    Log4J.getLogger().info(candidate);
    Log4J.getLogger().info(actual);
//    verify(candidateRepository, times(1)).save(candidate);
    assertTrue(actual);

  }

}