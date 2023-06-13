package ccit.g2airline.project11deployableweb.model.database;

import ccit.g2airline.project11deployableweb.model.BaseModel;

public class FlightModel extends BaseModel {

    private String airplane_id;
    private String id;
    private String route_id;
    private String time_of_departure;

    public String getAirplane_id() {
        return airplane_id;
    }

    public void setAirplane_id(String airplane_id) {
        this.airplane_id = airplane_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getTime_of_departure() {
        return time_of_departure;
    }

    public void setTime_of_departure(String time_of_departure) {
        this.time_of_departure = time_of_departure;
    }
}
