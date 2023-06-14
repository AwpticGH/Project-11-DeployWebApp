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

public class AirlineController extends BaseController implements ReadData {
    @Override
    public void get(HttpServletRequest request, BaseModel baseModel) {
        System.out.println("Getting Airline");
        FlightSearchedModel model = (FlightSearchedModel) baseModel;
        DatabaseReference reference = DatabaseConfig.getReference(DatabaseTable.TABLE_AIRLINES);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int iterator = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    AirlineModel airlineModel = data.getValue(AirlineModel.class);
                    if (airlineModel.getId().equals(model.getAirlineModels().get(iterator).getId())) {
                        model.getAirlineModels().set(iterator, airlineModel);
                    }
                    if (iterator == model.getAirlineModels().size() - 1) {
                        break;
                    }
                }

                request.setAttribute("FlightSearchedModel", model);
                request.getAsyncContext().complete();
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
