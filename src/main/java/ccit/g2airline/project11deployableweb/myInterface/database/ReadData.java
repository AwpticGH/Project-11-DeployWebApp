package ccit.g2airline.project11deployableweb.myInterface.database;

import ccit.g2airline.project11deployableweb.model.BaseModel;
import jakarta.servlet.http.HttpServletRequest;

public interface ReadData {

    void get(HttpServletRequest request, BaseModel baseModel);
}
