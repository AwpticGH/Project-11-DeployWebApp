package ccit.g2airline.project11deployableweb.controller;

import ccit.g2airline.project11deployableweb.config.DatabaseConfig;
import ccit.g2airline.project11deployableweb.dictionary.DatabaseTable;
import ccit.g2airline.project11deployableweb.dictionary.WebVariable;
import ccit.g2airline.project11deployableweb.model.BaseModel;
import ccit.g2airline.project11deployableweb.model.database.AirlineModel;
import ccit.g2airline.project11deployableweb.model.database.AirplaneModel;
import ccit.g2airline.project11deployableweb.model.web.FlightSearchedModel;
import ccit.g2airline.project11deployableweb.myInterface.database.ReadData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AirplaneController extends BaseController implements ReadData {
    @Override
    public void get(HttpServletRequest request, BaseModel baseModel) {
        System.out.println("Getting Airplane");
        FlightSearchedModel model = (FlightSearchedModel) baseModel;
        List<AirlineModel> airlineModels = new ArrayList<>();
        DatabaseReference reference = DatabaseConfig.getReference(DatabaseTable.TABLE_AIRPLANES);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int iterator = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (iterator == model.getAirplaneModels().size()) {
                        break;
                    }
                    System.out.println("Loop : " + (iterator + 1)); // debug

                    AirplaneModel result = data.getValue(AirplaneModel.class);
                    AirlineModel airlineModel = new AirlineModel();
                    AirplaneModel airplaneModel = model.getAirplaneModels().get(iterator);

                    String resultAirplaneId = result.getId();
                    String airplaneId = airplaneModel.getId();

                    System.out.println("Result AirplaneId : " + resultAirplaneId);
                    System.out.println("Wanted AirplaneId : " + airplaneId);

                    if (resultAirplaneId.equals(airplaneId)) {
                        model.getAirplaneModels().set(iterator, result);
                        airlineModel.setId(result.getAirline_id());
                        airlineModels.add(airlineModel);
                        System.out.println("Airline " + (iterator + 1) + " has been set : " + airlineModel.getId());
                        iterator++;
                    }
                }
                model.setAirlineModels(airlineModels);

                AirlineController ac = new AirlineController();
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
