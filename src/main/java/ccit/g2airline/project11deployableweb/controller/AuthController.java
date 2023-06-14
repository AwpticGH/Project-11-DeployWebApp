package ccit.g2airline.project11deployableweb.controller;

import ccit.g2airline.project11deployableweb.config.DatabaseConfig;
import ccit.g2airline.project11deployableweb.dictionary.WebVariable;
import ccit.g2airline.project11deployableweb.helper.firebase.DatabaseHelper;
import ccit.g2airline.project11deployableweb.dictionary.DatabaseTable;
import ccit.g2airline.project11deployableweb.model.database.AuthModel;
import ccit.g2airline.project11deployableweb.model.BaseModel;
import ccit.g2airline.project11deployableweb.myInterface.database.CreateData;
import ccit.g2airline.project11deployableweb.myInterface.database.ReadData;
import ccit.g2airline.project11deployableweb.myInterface.database.UpdateData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AuthController extends BaseController implements CreateData, UpdateData, ReadData {


    @Override
    public void get(HttpServletRequest request, BaseModel baseModel) {
        AuthModel model = (AuthModel) baseModel;
        DatabaseReference ref = DatabaseConfig.getReference(DatabaseTable.TABLE_USERS);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    AuthModel result = data.getValue(AuthModel.class);
                    if (result.getEmail().equals(model.getEmail()) && result.getPassword().equals(model.getPassword())) {
                        HttpSession session = request.getSession();
                        session.setAttribute("AuthModel", result);
                        request.getAsyncContext().complete();
                        System.out.println("Login Success");
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("DATABASE ERROR");
                databaseError.getMessage();
                request.getAsyncContext().complete();
            }
        });
    }

    @Override
    public void create(HttpServletRequest request, BaseModel baseModel) {
        AuthModel model = (AuthModel) baseModel;
        DatabaseReference reference = DatabaseConfig.getReference(DatabaseTable.TABLE_USERS);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long count = dataSnapshot.getChildrenCount();
                String id = DatabaseHelper.parseDataCountAsId(count);
                model.setId(id);
                reference.child(String.valueOf(count)).setValue(model, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        request.getAsyncContext().complete();
                        request.setAttribute("my-alert", "You Have Successfully Registered, You Can Now Log In");
                        System.out.println("Create Success");
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.getMessage();
                request.getAsyncContext().complete();
                request.setAttribute("my-alert", "Register Failed! Please Try Again Later");
            }
        });
    }

    @Override
    public void update(HttpServletRequest request, BaseModel baseModel) {
        AuthModel model = (AuthModel) baseModel;
        DatabaseReference reference = DatabaseConfig.getReference(DatabaseTable.TABLE_USERS);
        String key = DatabaseHelper.parseSqlId(model.getId());
        reference.child(key).setValue(model, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                request.setAttribute(
                        WebVariable.ALERT,
                        (
                                databaseError == null
                                ? "You Have Successfully Updated Your Account!"
                                : "Update Account Failed, Please Try Again Later!"
                        )
                );
                if (databaseError == null) {
                    request.getSession().setAttribute("AuthModel", model);
                }
                request.getAsyncContext().complete();
            }
        });
    }
}
