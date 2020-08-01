package fa.fams.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    // Sét đặt dịch vụ để tìm kiếm User trong Database.
    // Và sét đặt PasswordEncoder.
    auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
        .antMatchers("/login", "/logout", "/welcome").permitAll()
        .antMatchers("/home").hasAnyRole("ADMIN", "", "USER").antMatchers("/")
        .permitAll().and().formLogin().loginPage("/login")
        .loginProcessingUrl("/j_spring_security_check")
        .usernameParameter("j_username").passwordParameter("j_password")
        .defaultSuccessUrl("/home").failureUrl("/login?error=true").and()
        .logout()
        .and().exceptionHandling().accessDeniedPage("/403");
  }
}
