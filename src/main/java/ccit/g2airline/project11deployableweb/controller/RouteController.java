package ccit.g2airline.project11deployableweb.controller;

import ccit.g2airline.project11deployableweb.config.DatabaseConfig;
import ccit.g2airline.project11deployableweb.dictionary.DatabaseTable;
import ccit.g2airline.project11deployableweb.dictionary.WebVariable;
import ccit.g2airline.project11deployableweb.model.BaseModel;
import ccit.g2airline.project11deployableweb.model.database.RouteModel;
import ccit.g2airline.project11deployableweb.model.web.FlightSearchedModel;
import ccit.g2airline.project11deployableweb.myInterface.database.ReadData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import jakarta.servlet.http.HttpServletRequest;

public class RouteController extends BaseController implements ReadData {
    @Override
    public void get(HttpServletRequest request, BaseModel baseModel) {
        System.out.println("Getting Route");
        FlightSearchedModel model = (FlightSearchedModel) baseModel;
        DatabaseReference reference = DatabaseConfig.getReference(DatabaseTable.TABLE_ROUTES);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int iterator = 0; // debug
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    RouteModel result = data.getValue(RouteModel.class);

                    String resultDepartureId = result.getDeparture_id();
                    String resultDestinationId = result.getDestination_id();
                    String departureId = model.getAirportDepartureModel().getId();
                    String destinationId = model.getAirportDestinationModel().getId();

                    System.out.println("Loop : " + (iterator + 1)); // debug
                    System.out.println("RouteID : " + result.getId());
                    System.out.println("Result DepartureID : " + resultDepartureId);
                    System.out.println("Result DestinationID : " + resultDestinationId);
                    System.out.println("Wanted DepartureID : " + departureId);
                    System.out.println("Wanted DestinationID : " + destinationId);

                    if (resultDepartureId.equals(departureId) && resultDestinationId.equals(destinationId)) {
                        model.setRouteModel(result);
                        System.out.println("RouteID Has Been Set : " + model.getRouteModel().getId());
                        FlightController fc = new FlightController();
                        fc.get(request, model);
                        break;
                    }
                    iterator++;
                }
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
