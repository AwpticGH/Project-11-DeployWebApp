package ccit.g2airline.project11deployableweb.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/**
 *
 * @author rafih
 */
public class DateHelper {
    
    public static Date parseDate(String date) throws ParseException {
        String[] text = date.split("-");
        String strNewDate = text[2] + "/" + text[1] + "/" + text[0];
        Date newDate = new SimpleDateFormat("dd/MM/yyyy").parse(strNewDate);
        return newDate;
    }

    public static String dateFormat(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM");
        return sdf.format(parseDate(date));
    }
    
    public static String dateFormat(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(parseDate(date));
    }

    public static String parseDatetimeToDate(String datetime) throws ParseException {
        return datetime.split(" ")[0];
    }
    
    public static String parseAgeFromDob(String dob) throws ParseException {
        if (dob != null) {
            LocalDate currentDate = LocalDate.now();
            LocalDate dateOfBirth = LocalDate.parse(dob);
            int age = Period.between(dateOfBirth, currentDate).getYears();

            return String.valueOf(age);
        }
        else {
            return null;
        }
    }
}
