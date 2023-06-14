package ccit.g2airline.project11deployableweb.servlet.reservation;

import ccit.g2airline.project11deployableweb.controller.ReservationController;
import ccit.g2airline.project11deployableweb.dictionary.WebRoute;
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

@WebServlet(name = "reservationCreateServlet", value = WebRoute.RESERVATION_CREATE, asyncSupported = true)
public class CreateServlet extends HttpServlet implements GetServlet, PostServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AuthModel authModel = (AuthModel) request.getSession().getAttribute("AuthModel");
        boolean isLogged = authModel != null;
        if (isLogged) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/reservation/create.jsp");
            dispatcher.forward(request, response);
        }
        else {
            String referer = request.getHeader("referer");
            response.setHeader("Location", referer);
            response.flushBuffer();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReservationModel reservationModel = new ReservationModel();

        ReservationInfoModel reservationInfoModel = new ReservationInfoModel();

        AuthModel authModel = (AuthModel) request.getSession().getAttribute("AuthModel");
        ReservedFlightModel model = new ReservedFlightModel();
        model.setAuthModel(authModel);

        ReservationController rc = new ReservationController();
        request.startAsync();
        rc.create(request, model);

        response.sendRedirect(WebRoute.FLIGHT_INDEX);
    }
}
