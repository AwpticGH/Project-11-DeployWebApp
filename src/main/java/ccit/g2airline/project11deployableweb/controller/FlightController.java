package ccit.g2airline.project11deployableweb.controller;

import ccit.g2airline.project11deployableweb.config.DatabaseConfig;
import ccit.g2airline.project11deployableweb.dictionary.DatabaseTable;
import ccit.g2airline.project11deployableweb.dictionary.WebRoute;
import ccit.g2airline.project11deployableweb.dictionary.WebVariable;
import ccit.g2airline.project11deployableweb.helper.DatetimeHelper;
import ccit.g2airline.project11deployableweb.model.BaseModel;
import ccit.g2airline.project11deployableweb.model.database.AirplaneModel;
import ccit.g2airline.project11deployableweb.model.database.FlightModel;
import ccit.g2airline.project11deployableweb.model.web.FlightSearchedModel;
import ccit.g2airline.project11deployableweb.myInterface.database.ReadData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FlightController extends BaseController implements ReadData {

    @Override
    public void get(HttpServletRequest request, BaseModel baseModel) {
        System.out.println("Getting Flight");
        FlightSearchedModel model = (FlightSearchedModel) baseModel;
        DatabaseReference reference = DatabaseConfig.getReference(DatabaseTable.TABLE_FLIGHTS);

        List<AirplaneModel> airplaneModels = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int iterator = 0; // debug
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    System.out.println("Loop : " + (iterator + 1));
                    FlightModel result = data.getValue(FlightModel.class);
                    AirplaneModel airplaneModel = new AirplaneModel();

                    String resultRouteId = result.getRoute_id();
                    String resultTimeOfDeparture = result.getTime_of_departure();
                    String resultDateOfDeparture;
                    try {
                        resultDateOfDeparture = DatetimeHelper.parseDatetimeToDate(resultTimeOfDeparture);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                    String routeId = model.getRouteModel().getId();
                    String dateOfDeparture;
                    if (model.getFlightModels().get(0).getId() == null) {
                        dateOfDeparture = model.getFlightModels().get(0).getTime_of_departure();
                    }
                    else {
                        try {
                            dateOfDeparture = DatetimeHelper.parseDatetimeToDate(model.getFlightModels().get(0).getTime_of_departure());
                        }
                        catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    System.out.println("Result RouteId : " + resultRouteId);
                    System.out.println("Result TimeOfDeparture : " + resultTimeOfDeparture);
                    System.out.println("Wanted RouteId : " + routeId);
                    System.out.println("Wanted TimeOfDeparture : " + dateOfDeparture);

                    if (resultRouteId.equals(routeId) && resultDateOfDeparture.equals(dateOfDeparture)) {
                        System.out.println("Flight Found on " + (iterator + 1));
                        if (model.getFlightModels().get(0).getId() == null) {
                            model.getFlightModels().set(0, result);
                        }
                        else {
                            model.getFlightModels().add(result);
                        }
                        airplaneModel.setId(result.getAirplane_id());
                        airplaneModels.add(airplaneModel);
                        System.out.println("AirplaneID : " + airplaneModel.getId());
                    }
                    iterator++;
                }
                model.setAirplaneModels(airplaneModels);

                if (!model.getAirplaneModels().isEmpty()) {
                    AirplaneController ac = new AirplaneController();
                    ac.get(request, model);
                }
                else {
                    System.out.println("No Flights Found!!");
                    request.setAttribute(WebVariable.ALERT, "No Flights Found!!!");
                    request.setAttribute("FlightSearchedModel", model);
                    request.getAsyncContext().complete();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Cancelled");
                System.out.println(databaseError.getMessage());
                request.setAttribute(WebVariable.ALERT, "SERVER ERROR!!!\nPlease Try Again Later!");
                request.getAsyncContext().complete();
            }
        });
    }
}
