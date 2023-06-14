package ccit.g2airline.project11deployableweb.model.web;

import ccit.g2airline.project11deployableweb.model.BaseModel;
import ccit.g2airline.project11deployableweb.model.database.AuthModel;
import ccit.g2airline.project11deployableweb.model.database.ReservationModel;
import ccit.g2airline.project11deployableweb.model.database.ReservationInfoModel;

import java.util.List;

public class ReservedFlightModel extends BaseModel {

    private AuthModel authModel;
    private ReservationModel reservationModel;
    private List<ReservationInfoModel> reservationInfoModels;

    public AuthModel getAuthModel() {
        return authModel;
    }

    public void setAuthModel(AuthModel authModel) {
        this.authModel = authModel;
    }

    public ReservationModel getReservationModel() {
        return reservationModel;
    }

    public void setReservationModel(ReservationModel reservationModel) {
        this.reservationModel = reservationModel;
    }

    public List<ReservationInfoModel> getReservationInfoModels() {
        return reservationInfoModels;
    }

    public void setReservationInfoModels(List<ReservationInfoModel> reservationInfoModel) {
        this.reservationInfoModels = reservationInfoModel;
    }
}
