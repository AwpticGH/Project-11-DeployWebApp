package ccit.g2airline.project11deployableweb.model.database;

import ccit.g2airline.project11deployableweb.model.BaseModel;

public class AirplaneModel extends BaseModel {

    private String airline_id;
    private String business_seats;
    private String economy_seats;
    private String first_class_seats;
    private String id;
    private String premium_economy_seats;
    private String total_seats;
    private String type;

    public String getAirline_id() {
        return airline_id;
    }

    public void setAirline_id(String airline_id) {
        this.airline_id = airline_id;
    }

    public String getBusiness_seats() {
        return business_seats;
    }

    public void setBusiness_seats(String business_seats) {
        this.business_seats = business_seats;
    }

    public String getEconomy_seats() {
        return economy_seats;
    }

    public void setEconomy_seats(String economy_seats) {
        this.economy_seats = economy_seats;
    }

    public String getFirst_class_seats() {
        return first_class_seats;
    }

    public void setFirst_class_seats(String first_class_seats) {
        this.first_class_seats = first_class_seats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPremium_economy_seats() {
        return premium_economy_seats;
    }

    public void setPremium_economy_seats(String premium_economy_seats) {
        this.premium_economy_seats = premium_economy_seats;
    }

    public String getTotal_seats() {
        return total_seats;
    }

    public void setTotal_seats(String total_seats) {
        this.total_seats = total_seats;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
