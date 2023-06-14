package ccit.g2airline.project11deployableweb.controller.admin;

import ccit.g2airline.project11deployableweb.config.DatabaseConfig;
import ccit.g2airline.project11deployableweb.controller.BaseController;
import ccit.g2airline.project11deployableweb.dictionary.DatabaseTable;
import ccit.g2airline.project11deployableweb.model.BaseModel;
import ccit.g2airline.project11deployableweb.myInterface.database.DeleteData;
import com.google.firebase.database.DatabaseReference;
import jakarta.servlet.http.HttpServletRequest;

public class DatabaseController extends BaseController {

    public void delete(String table, long start, long finish) {
        DatabaseReference reference = DatabaseConfig.getReference(table);
        while (start <= finish) { // Ending key to delete
            reference.child(String.valueOf(start)).removeValueAsync();
            start++;
        }
    }
}
