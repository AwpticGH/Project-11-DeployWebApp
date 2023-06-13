package ccit.g2airline.project11deployableweb.controller;

import ccit.g2airline.project11deployableweb.config.DatabaseConfig;
import ccit.g2airline.project11deployableweb.dictionary.DatabaseTable;
import ccit.g2airline.project11deployableweb.dictionary.WebVariable;
import ccit.g2airline.project11deployableweb.model.BaseModel;
import ccit.g2airline.project11deployableweb.model.database.FlightModel;
import ccit.g2airline.project11deployableweb.model.web.FlightSearchModel;
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
        FlightSearchModel searchModel = (FlightSearchModel) baseModel;
        List<FlightModel> listOfFlight = new ArrayList<>();
        DatabaseReference reference = DatabaseConfig.getReference(DatabaseTable.TABLE_FLIGHTS);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    FlightModel result = data.getValue(FlightModel.class);
                    if (result.getRoute_id().equals(searchModel.getRouteId()) && result.getTime_of_departure().contains(searchModel.getDateOfDeparture())) {
                        listOfFlight.add(result);
                    }
                }

                request.setAttribute("flights", listOfFlight);
                request.getAsyncContext().complete();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.getMessage();
                request.setAttribute(WebVariable.ALERT, "SERVER ERROR!!!\nPlease Try Again Later!");
                request.getAsyncContext().complete();
            }
        });
    }
}
