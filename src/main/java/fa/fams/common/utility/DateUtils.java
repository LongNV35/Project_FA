package fa.fams.common.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import fa.fams.common.log.Log4J;

public class DateUtils {
  public static String formatDate1(LocalDate date) {
    DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter
        .ofPattern("MMM-yyyy");
    return dateTimeFormatter2.format(date);
  }

  public static String formatDate2(LocalDate date) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter
        .ofPattern("dd-MMM-yyyy");
    return dateTimeFormatter.format(date);
  }

  /**
   * this function to get local date from date type String and a date time
   * formater.
   * 
   * @param dateString        - input date
   * @param dateTimeFormatter - formatter
   * @return - a local date after format
   */
  public static LocalDate convertToLocalDate1(String dateString,
      DateTimeFormatter dateTimeFormatter) {
    LocalDate localDate = null;
    try {
      localDate = LocalDate.parse(dateString, dateTimeFormatter);
    } catch (DateTimeParseException e) {
      Log4J.getLogger().error(e.getMessage());
      return null;
    }
    return localDate;
  }

  public static DateTimeFormatter getDateTimeFormater1() {
    return DateTimeFormatter.ofPattern("dd-MM-yyyy");
  }

  public static DateTimeFormatter getDateTimeFormater2() {
    return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
  }


}
