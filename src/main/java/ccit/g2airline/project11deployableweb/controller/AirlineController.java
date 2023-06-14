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
                    if (iterator == model.getAirlineModels().size()) {
                        break;
                    }
                    System.out.println("Loop : " + (iterator + 1));
                    AirlineModel result = data.getValue(AirlineModel.class);

                    String resultAirlineId = result.getId();
                    String airlineId = model.getAirlineModels().get(iterator).getId();

                    System.out.println("Result AirlineID : " + resultAirlineId);
                    System.out.println("Wanted AirlineID : " + airlineId);

                    if (resultAirlineId.equals(airlineId)) {
                        System.out.println("Airline Found");
                        model.getAirlineModels().set(iterator, result);
                    }
                    iterator++;
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
