package ccit.g2airline.project11deployableweb.model.database;

import ccit.g2airline.project11deployableweb.model.BaseModel;

public class SeatsModel extends BaseModel {

    private String airplane_id;
    private String id;
    private String number;
    private String row;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }
}
