package ccit.g2airline.project11deployableweb.myInterface.database;

import ccit.g2airline.project11deployableweb.model.BaseModel;
import jakarta.servlet.http.HttpServletRequest;

public interface UpdateData {

    void update(HttpServletRequest request, BaseModel baseModel);
}
