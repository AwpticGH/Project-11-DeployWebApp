package ccit.g2airline.project11deployableweb.servlet.reservation;

import ccit.g2airline.project11deployableweb.controller.ReservationController;
import ccit.g2airline.project11deployableweb.dictionary.WebRoute;
import ccit.g2airline.project11deployableweb.dictionary.WebVariable;
import ccit.g2airline.project11deployableweb.helper.DateHelper;
import ccit.g2airline.project11deployableweb.model.database.AuthModel;
import ccit.g2airline.project11deployableweb.model.database.ReservationInfoModel;
import ccit.g2airline.project11deployableweb.model.database.ReservationModel;
import ccit.g2airline.project11deployableweb.model.web.ReservedFlightModel;
import ccit.g2airline.project11deployableweb.myInterface.servlet.GetServlet;
import ccit.g2airline.project11deployableweb.myInterface.servlet.PostServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "reservationCreateServlet", value = WebRoute.RESERVATION_CREATE, asyncSupported = true)
public class CreateServlet extends HttpServlet implements GetServlet, PostServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String flightId = request.getParameter(WebVariable.FLIGHT_ID);
        String passCount = request.getParameter(WebVariable.PASSENGER_COUNT);
        String seatClass = request.getParameter(WebVariable.SEAT_CLASS);

        AuthModel authModel = (AuthModel) request.getSession().getAttribute("AuthModel");
        boolean isLogged = authModel != null;
        if (isLogged) {
            request.setAttribute(WebVariable.FLIGHT_ID, flightId);
            request.setAttribute(WebVariable.PASSENGER_COUNT, passCount);
            request.setAttribute(WebVariable.SEAT_CLASS, seatClass);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/reservation/create.jsp");
            dispatcher.forward(request, response);
        }
        else {
            request.setAttribute(WebVariable.ALERT, "Please Login First!");
            response.sendRedirect(WebRoute.FLIGHT_INDEX);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AuthModel authModel = (AuthModel) request.getSession().getAttribute("AuthModel");
        String userId = authModel.getId();
        String flightId = request.getParameter(WebVariable.FLIGHT_ID);
        String passCount = request.getParameter(WebVariable.PASSENGER_COUNT);
        String seatClass = request.getParameter(WebVariable.SEAT_CLASS);

        ReservationModel reservationModel = new ReservationModel();
        reservationModel.setActive("1");
        reservationModel.setPaid("1");
        reservationModel.setUser_id(userId);
        reservationModel.setFlight_id(flightId);

        List<ReservationInfoModel> reservationInfoModels = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(passCount); i++) {
            String name = request.getParameter(WebVariable.passengerName(i));
            String gender = request.getParameter(WebVariable.passengerGender(i));
            String dateOfBirth = request.getParameter(WebVariable.passengerDateOfBirth(i));
            String age;
            try {
                age = DateHelper.parseAgeFromDate(dateOfBirth);
            }
            catch (ParseException e) {
                throw new RuntimeException(e);
            }
            String phoneNumber = request.getParameter(WebVariable.passengerPhoneNumber(i));

            ReservationInfoModel reservationInfoModel = new ReservationInfoModel();
            reservationInfoModel.setName(name);
            reservationInfoModel.setGender(gender);
            reservationInfoModel.setAge(age);
            reservationInfoModel.setPhone_number(phoneNumber);
            reservationInfoModel.setSeat_class(seatClass);

            reservationInfoModels.add(reservationInfoModel);
        }

        ReservedFlightModel model = new ReservedFlightModel();
        model.setAuthModel(authModel);
        model.setReservationModel(reservationModel);
        model.setReservationInfoModels(reservationInfoModels);

        ReservationController rc = new ReservationController();
        request.startAsync();
        rc.create(request, model);

        response.sendRedirect(WebRoute.FLIGHT_INDEX);
    }
}
