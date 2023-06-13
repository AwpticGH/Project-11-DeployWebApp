package ccit.g2airline.project11deployableweb.controller;

import ccit.g2airline.project11deployableweb.config.DatabaseConfig;
import ccit.g2airline.project11deployableweb.dictionary.DatabaseTable;
import ccit.g2airline.project11deployableweb.dictionary.WebVariable;
import ccit.g2airline.project11deployableweb.model.BaseModel;
import ccit.g2airline.project11deployableweb.model.database.AirportModel;
import ccit.g2airline.project11deployableweb.model.web.FlightSearchModel;
import ccit.g2airline.project11deployableweb.myInterface.database.ReadData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import jakarta.servlet.http.HttpServletRequest;

public class AirportController extends BaseController implements ReadData {
    @Override
    public void get(HttpServletRequest request, BaseModel baseModel) {
        FlightSearchModel searchModel = (FlightSearchModel) baseModel;
        DatabaseReference reference = DatabaseConfig.getReference(DatabaseTable.TABLE_AIRPORTS);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String departureId = null;
                String destinationId = null;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    AirportModel airportModel = data.getValue(AirportModel.class);
                    if (departureId != null && destinationId != null) {
                        break;
                    }
                    if (airportModel.getCity().equals(searchModel.getDepartureCity())) {
                        departureId = airportModel.getId();
                    }
                    if (airportModel.getCity().equals(searchModel.getDestinationCity())) {
                        destinationId = airportModel.getId();
                    }
                }

                searchModel.setDepartureId(departureId);
                searchModel.setDestinationId(destinationId);

                RouteController rc = new RouteController();
                rc.get(request, searchModel);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.getMessage();
                request.setAttribute(WebVariable.ALERT, "SERVER ERROR!!!\nPLEASE TRY AGAIN LATER!!!");
                request.getAsyncContext().complete();
            }
        });
    }
}
