package ccit.g2airline.project11deployableweb.controller;

import ccit.g2airline.project11deployableweb.model.web.ReservedFlightModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import ccit.g2airline.project11deployableweb.config.DatabaseConfig;
import ccit.g2airline.project11deployableweb.dictionary.DatabaseTable;
import ccit.g2airline.project11deployableweb.dictionary.WebVariable;
import ccit.g2airline.project11deployableweb.helper.firebase.DatabaseHelper;
import ccit.g2airline.project11deployableweb.model.BaseModel;
import ccit.g2airline.project11deployableweb.model.database.ReservationModel;
import ccit.g2airline.project11deployableweb.myInterface.database.CreateData;
import ccit.g2airline.project11deployableweb.myInterface.database.DeleteData;
import ccit.g2airline.project11deployableweb.myInterface.database.ReadData;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ReservationController extends BaseController implements CreateData, ReadData, DeleteData {
    @Override
    public void create(HttpServletRequest request, BaseModel baseModel) {
        ReservedFlightModel model = (ReservedFlightModel) baseModel;
        DatabaseReference reference = DatabaseConfig.getReference(DatabaseTable.TABLE_RESERVATIONS);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = String.valueOf(dataSnapshot.getChildrenCount());
                String id = DatabaseHelper.parseDataCountAsId(dataSnapshot.getChildrenCount());
                model.getReservationModel().setId(id);
                reference.child(key).setValue(model.getReservationModel(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError error, DatabaseReference reference) {
                        request.setAttribute(
                            WebVariable.ALERT, 
                            (
                                error == null
                                ? "You Have Successfully Reserved A Ticket!\nPlease Check Your Ticket For More Information"
                                : "SERVER ERROR!!!\nPlease Try Again Later!"
                            )
                        );

                        for (int i = 0; i < model.getReservationInfoModels().size(); i++) {
                            model.getReservationInfoModels().get(i).setReservation_id(id);
                        }

                        ReservationInfoController ric = new ReservationInfoController();
                        ric.create(request, model);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
                request.setAttribute(WebVariable.ALERT, "SERVER ERROR!!!\nPlease Try Again Later!");
                request.getAsyncContext().complete();
            }
        });
    }

    @Override
    public void get(HttpServletRequest request, BaseModel baseModel) {
        ReservedFlightModel model = (ReservedFlightModel) baseModel;
        DatabaseReference reference = DatabaseConfig.getReference(DatabaseTable.TABLE_RESERVATIONS);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    ReservationModel result = data.getValue(ReservationModel.class);
                    if (result.getUser_id().equals(model.getAuthModel().getId()) && result.getActive().equals("1")) {
                        model.setReservationModel(result);
                        break;
                    }
                }

                ReservationInfoController ric = new ReservationInfoController();
                ric.get(request, model);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.getMessage();
                request.setAttribute(WebVariable.ALERT, "SERVER ERROR!!!\nPlease Try Again Later!");
                request.getAsyncContext().complete();
            }
        });
    }

    @Override
    public void delete(HttpServletRequest request, BaseModel baseModel) {
        ReservationModel model = (ReservationModel) baseModel;
        model.setActive("0");
        DatabaseReference reference = DatabaseConfig.getReference(DatabaseTable.TABLE_RESERVATIONS);
        String key = DatabaseHelper.parseSqlId(model.getId());
        reference.child(key).setValue(model, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference reference) {
                request.setAttribute(
                    WebVariable.ALERT,
                    (
                        error == null
                        ? "You Have Successfully Cancelled The Ticket!"
                        : "SERVER ERROR!!!\nPlease Try Again Later"
                    )
                );
                request.getAsyncContext().complete();
            }
        });
    }
}
