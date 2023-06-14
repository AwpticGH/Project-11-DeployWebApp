package ccit.g2airline.project11deployableweb.model.web;

import ccit.g2airline.project11deployableweb.model.BaseModel;
import ccit.g2airline.project11deployableweb.model.database.*;

import java.util.List;

public class FlightSearchedModel extends BaseModel {

    private List<AirlineModel> airlineModels;
    private List<AirplaneModel> airplaneModels;
    private AirportModel airportDepartureModel;
    private AirportModel airportDestinationModel;
    private RouteModel routeModel;
    private List<FlightModel> flightModels;
    private String passengerSeatClass;

    public List<AirlineModel> getAirlineModels() {
        return airlineModels;
    }

    public void setAirlineModels(List<AirlineModel> airlineModels) {
        this.airlineModels = airlineModels;
    }

    public List<AirplaneModel> getAirplaneModels() {
        return airplaneModels;
    }

    public void setAirplaneModels(List<AirplaneModel> airplaneModels) {
        this.airplaneModels = airplaneModels;
    }

    public AirportModel getAirportDepartureModel() {
        return airportDepartureModel;
    }

    public void setAirportDepartureModel(AirportModel airportDepartureModel) {
        this.airportDepartureModel = airportDepartureModel;
    }

    public AirportModel getAirportDestinationModel() {
        return airportDestinationModel;
    }

    public void setAirportDestinationModel(AirportModel airportDestinationModel) {
        this.airportDestinationModel = airportDestinationModel;
    }

    public RouteModel getRouteModel() {
        return routeModel;
    }

    public void setRouteModel(RouteModel routeModel) {
        this.routeModel = routeModel;
    }

    public List<FlightModel> getFlightModels() {
        return flightModels;
    }

    public void setFlightModels(List<FlightModel> flightModels) {
        this.flightModels = flightModels;
    }
    public String getPassengerSeatClass() {
        return passengerSeatClass;
    }

    public void setPassengerSeatClass(String passengerSeatClass) {
        this.passengerSeatClass = passengerSeatClass;
    }
}
