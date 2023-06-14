package ccit.g2airline.project11deployableweb.helper;

import java.text.ParseException;

public class DatetimeHelper {

    public static String parseDatetimeToDate(String datetime) throws ParseException {
        return datetime.split(" ")[0];
    }

    public static String parseDatetimeToTime(String datetime) throws ParseException {
        return datetime.split(" ")[1];
    }

}
