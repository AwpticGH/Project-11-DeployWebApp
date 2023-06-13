package ccit.g2airline.project11deployableweb.helper;

public class IntegerHelper {
    
    public static int countAvailableSeats(int reservedSeats, int totalSeats) {
        int availableSeats = totalSeats - reservedSeats;
        return availableSeats;
    }
}
