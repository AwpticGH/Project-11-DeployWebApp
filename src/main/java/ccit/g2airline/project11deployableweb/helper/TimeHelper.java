package ccit.g2airline.project11deployableweb.helper;

public class TimeHelper {
    
    private static String parseMinuteToTime(String minute) {
        String seconds = "00";
        int hours = Integer.parseInt(minute) / 60;
        int minutes = Integer.parseInt(minute) % 60;

        return hours + ":" + minutes + ":" + seconds;
    }
    
    public static String removeSecondsFromTime(String time) {
        String[] newTime = time.split(":");
        return newTime[0] + ":" + newTime[1];
    }

    public static String addTime(String time, String timeOfFlight) {
        String[] x = time.split(":");
        String[] y = parseMinuteToTime(timeOfFlight).split(":");

        int hours = Integer.parseInt(x[0]) + Integer.parseInt(y[0]) + (Integer.parseInt(y[1])/60);
        int minutes = (Integer.parseInt(x[1]) + Integer.parseInt(y[1]) + (Integer.parseInt(y[2])/60)) % 60;

        String strHours;
        String strMinutes;

        if (minutes < 10) {
            strMinutes = "0" + minutes;
        }
        else {
            strMinutes = String.valueOf(minutes);
        }
        if (hours < 10) {
            strHours = "0" + hours;
        }
        else {
            strHours = String.valueOf(hours);
        }
        String timeResult = strHours + ":" + strMinutes;
        return timeResult;
    }
}