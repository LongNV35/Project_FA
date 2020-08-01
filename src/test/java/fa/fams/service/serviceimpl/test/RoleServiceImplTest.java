package fa.fams.service.serviceimpl.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Locale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import fa.fams.common.log.Log4J;
import fa.fams.model.Role;
import fa.fams.repository.RoleRepository;
import fa.fams.service.serviceimpl.RoleServiceImpl;

@SpringBootTest
class RoleServiceImplTest {

  @Mock
  private RoleRepository roleRepository;

  @InjectMocks
  private RoleServiceImpl roleServiceImpl;

  @Autowired
  private MessageSource messageSource;

  @BeforeEach
  void setUp() throws Exception {
  }

  /**
   * Test normal case.
   * 
   * @author LongNV35
   * 
   * @throws Exception If the role already exists
   */
  @Test
  void testCreateRole() throws Exception {
    Role role = new Role("USER", "USER");

    when(roleRepository.save(role)).thenReturn(role);

    boolean actual = roleServiceImpl.createRole(role);

    Log4J.getLogger().info(role);
    assertTrue(actual);
  }
  
  /**
   * Test abnormal case.
   * 
   * @author LongNV35
   * 
   */
  @Test
  void testCreateRole2() throws Exception {
    Role role = new Role("USER", "USER");

//    when(roleRepository.save(role)).thenReturn(role);

    boolean actual = roleServiceImpl.createRole(role);

    Log4J.getLogger().info(role);
    assertTrue(actual);
  }

  @Test
  void testFindByCode() {
    Log4J.getLogger().info("testing 1234");
    Log4J.getLogger().info(messageSource.getMessage("MSG1", null, Locale.US));
  }

}