package ccit.g2airline.project11deployableweb.controller;

import ccit.g2airline.project11deployableweb.config.DatabaseConfig;
import ccit.g2airline.project11deployableweb.dictionary.DatabaseTable;
import ccit.g2airline.project11deployableweb.dictionary.WebVariable;
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
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    FlightModel result = data.getValue(FlightModel.class);
                    AirplaneModel airplaneModel = new AirplaneModel();
                    if (result.getRoute_id().equals(model.getRouteModel().getId()) &&
                            (result.getTime_of_departure().contains(model.getFlightModels().get(0).getTime_of_departure()) ||
                                    result.getTime_of_departure().equals(model.getFlightModels().get(0).getTime_of_departure()))) {
                        if (model.getFlightModels().get(0).getId() == null) {
                            model.getFlightModels().add(0, result);
                        }
                        else {
                            model.getFlightModels().add(result);
                        }

                        airplaneModel.setId(result.getAirplane_id());
                        airplaneModels.add(airplaneModel);
                    }
                }
                model.setAirplaneModels(airplaneModels);

                AirplaneController ac = new AirplaneController();
                ac.get(request, model);
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
