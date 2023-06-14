package ccit.g2airline.project11deployableweb.controller;

import java.util.ArrayList;
import java.util.List;

import ccit.g2airline.project11deployableweb.model.web.ReservedFlightModel;
import ccit.g2airline.project11deployableweb.myInterface.database.CreateData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import ccit.g2airline.project11deployableweb.config.DatabaseConfig;
import ccit.g2airline.project11deployableweb.dictionary.DatabaseTable;
import ccit.g2airline.project11deployableweb.dictionary.WebVariable;
import ccit.g2airline.project11deployableweb.helper.firebase.DatabaseHelper;
import ccit.g2airline.project11deployableweb.model.BaseModel;
import ccit.g2airline.project11deployableweb.model.database.ReservationInfoModel;
import ccit.g2airline.project11deployableweb.myInterface.database.MultipleCreateData;
import ccit.g2airline.project11deployableweb.myInterface.database.ReadData;
import jakarta.servlet.http.HttpServletRequest;

public class ReservationInfoController extends BaseController implements ReadData, CreateData {

    @Override
    public void create(HttpServletRequest request, BaseModel baseModel) {
        ReservedFlightModel model = (ReservedFlightModel) baseModel;
        DatabaseReference reference = DatabaseConfig.getReference(DatabaseTable.TABLE_RESERVATIONS_INFO);
        for (int i = 0; i < model.getReservationInfoModels().size(); i++) {
            int iterator = i;
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String key = DatabaseHelper.parseDataCountAsId(dataSnapshot.getChildrenCount());
                    ReservationInfoModel reservationInfoModel = model.getReservationInfoModels().get(iterator);
                    reference.child(key).setValue(reservationInfoModel, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError error, DatabaseReference newReference) {
                            if (error == null) {
                                request.setAttribute(WebVariable.ALERT, "SERVER ERROR!!!\nPlease Try Again Later!");
                            }
                            if (iterator == model.getReservationInfoModels().size() - 1) {
                                request.getAsyncContext().complete();
                            }
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
    }

    @Override
    public void get(HttpServletRequest request, BaseModel baseModel) {
        ReservedFlightModel model = (ReservedFlightModel) baseModel;
        DatabaseReference reference = DatabaseConfig.getReference(DatabaseTable.TABLE_RESERVATIONS_INFO);
        List<ReservationInfoModel> reservationInfoModels = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    ReservationInfoModel result = data.getValue(ReservationInfoModel.class);
                    if (result.getReservation_id().equals(model.getReservationModel().getId())) {
                        reservationInfoModels.add(result);
                    }
                }

                model.setReservationInfoModels(reservationInfoModels);
                request.setAttribute("ReservedFlightModel", model);
                request.getAsyncContext().complete();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
                request.setAttribute(WebVariable.ALERT, "SERVER ERROR!!!\nPlease Try Again Later!");
                request.getAsyncContext().complete();
            }
        });
    }

    
}
