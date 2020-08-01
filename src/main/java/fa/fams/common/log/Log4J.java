package fa.fams.common.log;

import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4J {
  private static Logger logger = LogManager.getLogger(Log4J.class);

  public static Logger getLogger() {
    PropertyConfigurator
        .configure(Log4J.class.getResourceAsStream("/log4j.properties"));
    return logger;
  }
}
