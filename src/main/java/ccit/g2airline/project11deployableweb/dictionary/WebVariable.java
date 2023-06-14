package ccit.g2airline.project11deployableweb.dictionary;

public class WebVariable {

    public static final String ALERT = "my-alert";
    public static final String ACCOUNT_ID = "account-id";
    public static final String USERNAME = "username";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PASSWORD_CONFIRMATION = "password-confirmation";
    public static final String DATE_OF_BIRTH = "date-of-birth";
    public static final String GENDER = "gender";
    public static final String TITLE = "title";
    public static final String PHONE_NUMBER = "phone-number";
    public static final String DEPARTURE_CITY = "from-city";
    public static final String DESTINATION_CITY = "to-city";
    public static final String DEPARTURE_DATE = "departure-date";
    public static final String RETURN_DATE = "return-date";
    public static final String PASSENGER_SEAT_CLASS = "passenger-seat-class";
    public static final String PASSENGER_COUNT = "passenger-count";
    public static final String SEAT_CLASS = "seat-class";
    public static final String FLIGHT_ID = "flight-id";

    public static String passengerName(int index) {
        return "passenger-name-" + String.valueOf(index);
    }

    public static String passengerGender(int index) {
        return "passenger-gender-" + String.valueOf(index);
    }

    public static String passengerDateOfBirth(int index) {
        return "passenger-date-of-birth-" + String.valueOf(index);
    }

    public static String passengerPhoneNumber(int index) {
        return "passenger-phone-number-" + String.valueOf(index);
    }
}
