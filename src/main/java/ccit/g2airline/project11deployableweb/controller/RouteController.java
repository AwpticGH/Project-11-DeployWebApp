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
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    RouteModel result = data.getValue(RouteModel.class);
                    if (result.getDeparture_id().equals(model.getRouteModel().getDeparture_id()) && result.getDestination_id().equals(model.getRouteModel().getDestination_id())) {
                        model.setRouteModel(result);

                        FlightController fc = new FlightController();
                        fc.get(request, model);
                    }
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
