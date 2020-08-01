package fa.fams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@ComponentScan(value = { "fa.fams.*" })
@EnableJpaRepositories(basePackages = "fa.fams.repository")
@EntityScan("fa.fams.model")
@EnableWebSecurity
public class Java1908FamsG2Application {

  public static void main(String[] args) {
    ConfigurableApplicationContext run = SpringApplication
        .run(Java1908FamsG2Application.class, args);

  }
}
