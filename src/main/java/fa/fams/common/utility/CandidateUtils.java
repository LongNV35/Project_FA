package fa.fams.common.utility;

import java.time.LocalDateTime;

public class CandidateUtils {

  /**
   * this function to get a history line with class admin name.
   * 
   * @param employeeName - user name
   * @param status  - candidate's status 
   * @return - String: history or empty value
   */
  public static String createHistory(String employeeName, String status) {
    if (employeeName == null || status == null) {
      return null;
    } else {
      String dateNow = DateUtils.getDateTimeFormater2()
          .format(LocalDateTime.now());
      return dateNow + " - " + status + " - " + employeeName;
    }
  }

  public static boolean isEmptyOrNull(String param) {
    if (param.isEmpty() || param == null) {
      return true;
    } else {
      return false;
    }
  }
}
