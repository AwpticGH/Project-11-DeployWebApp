package ccit.g2airline.project11deployableweb.controller;

import ccit.g2airline.project11deployableweb.config.DatabaseConfig;
import ccit.g2airline.project11deployableweb.dictionary.DatabaseTable;
import ccit.g2airline.project11deployableweb.dictionary.WebVariable;
import ccit.g2airline.project11deployableweb.model.BaseModel;
import ccit.g2airline.project11deployableweb.model.database.AirportModel;
import ccit.g2airline.project11deployableweb.model.web.FlightSearchedModel;
import ccit.g2airline.project11deployableweb.myInterface.database.ReadData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import jakarta.servlet.http.HttpServletRequest;

public class AirportController extends BaseController implements ReadData {
    @Override
    public void get(HttpServletRequest request, BaseModel baseModel) {
        System.out.println("Getting Airport");
        FlightSearchedModel model = (FlightSearchedModel) baseModel;
        DatabaseReference reference = DatabaseConfig.getReference(DatabaseTable.TABLE_AIRPORTS);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int iterator = 0; // debug
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    AirportModel result = data.getValue(AirportModel.class);
                    System.out.println("Loop : " + (iterator + 1));
                    System.out.println("City : " + result.getCity());
                    if (model.getAirportDepartureModel().getId() != null && model.getAirportDestinationModel().getId() != null) {
                        break;
                    }
                    if (result.getCity().equals(model.getAirportDepartureModel().getCity())) {
                        model.setAirportDepartureModel(result);
                    }
                    if (result.getCity().equals(model.getAirportDestinationModel().getCity())) {
                        model.setAirportDestinationModel(result);
                    }
                    iterator++;
                }

                RouteController rc = new RouteController();
                rc.get(request, model);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("Cancelled");
                System.out.println(databaseError.getMessage());
                request.setAttribute(WebVariable.ALERT, "SERVER ERROR!!!\nPLEASE TRY AGAIN LATER!!!");
                request.getAsyncContext().complete();
            }
        });
    }
}
