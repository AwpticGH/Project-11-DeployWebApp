package ccit.g2airline.project11deployableweb.myInterface.database;

import java.util.List;
import ccit.g2airline.project11deployableweb.model.BaseModel;
import jakarta.servlet.http.HttpServletRequest;

public interface MultipleCreateData {
    
    void create(HttpServletRequest request, List<BaseModel> baseModels);

}
