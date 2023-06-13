package ccit.g2airline.project11deployableweb.controller;

import ccit.g2airline.project11deployableweb.model.BaseModel;
import ccit.g2airline.project11deployableweb.myInterface.database.CreateData;
import ccit.g2airline.project11deployableweb.myInterface.database.ReadData;
import ccit.g2airline.project11deployableweb.myInterface.database.UpdateData;
import jakarta.servlet.http.HttpServletRequest;

public class ReservationController extends BaseController implements CreateData, ReadData, UpdateData {
    @Override
    public void create(HttpServletRequest request, BaseModel baseModel) {

    }

    @Override
    public void get(HttpServletRequest request, BaseModel baseModel) {

    }

    @Override
    public void update(HttpServletRequest request, BaseModel baseModel) {

    }
}
