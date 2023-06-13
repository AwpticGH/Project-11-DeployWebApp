package ccit.g2airline.project11deployableweb.helper.firebase;

public class DatabaseHelper {

    public static String parseSqlId(String sqlId) {
        return String.valueOf(Integer.parseInt(sqlId) - 1);
    }

    public static String parseDataCountAsId(long count) {
        return String.valueOf(count + 1);
    }

}
