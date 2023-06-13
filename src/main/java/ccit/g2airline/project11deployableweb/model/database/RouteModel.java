package ccit.g2airline.project11deployableweb.model.database;

import ccit.g2airline.project11deployableweb.model.BaseModel;

public class RouteModel extends BaseModel {

    private String departure_id;
    private String destination_id;
    private String id;
    private String time_of_flight;

    public String getDeparture_id() {
        return departure_id;
    }

    public void setDeparture_id(String departure_id) {
        this.departure_id = departure_id;
    }

    public String getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(String destination_id) {
        this.destination_id = destination_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime_of_flight() {
        return time_of_flight;
    }

    public void setTime_of_flight(String time_of_flight) {
        this.time_of_flight = time_of_flight;
    }
}
