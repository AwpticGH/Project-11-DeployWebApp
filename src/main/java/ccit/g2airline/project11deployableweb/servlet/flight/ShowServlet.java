package ccit.g2airline.project11deployableweb.servlet.flight;

import ccit.g2airline.project11deployableweb.controller.AirportController;
import ccit.g2airline.project11deployableweb.dictionary.WebVariable;
import ccit.g2airline.project11deployableweb.helper.StringHelper;
import ccit.g2airline.project11deployableweb.model.web.FlightSearchModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "flightShowServlet", value = "/flight/get", asyncSupported = true)
public class ShowServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String departure = request.getParameter(WebVariable.DEPARTURE_CITY);
        String destination = request.getParameter(WebVariable.DESTINATION_CITY);
        String departureDate = request.getParameter(WebVariable.DEPARTURE_DATE);
        String returnDate = request.getParameter(WebVariable.RETURN_DATE);
        String passengerSeatClass = request.getParameter(WebVariable.PASSENGER_SEAT_CLASS);
        String passengerCount = StringHelper.getPassCount(passengerSeatClass);
        String seatClass = StringHelper.getSeatClass((passengerSeatClass));

        FlightSearchModel searchModel = new FlightSearchModel();
        searchModel.setDepartureCity(departure);
        searchModel.setDestinationCity(destination);
        searchModel.setDateOfDeparture(departureDate);
        searchModel.setDateOfReturn(returnDate);
        searchModel.setPassengerCount(passengerCount);
        searchModel.setSeatClass(seatClass);

        AirportController ac = new AirportController();
        ac.get(request, searchModel);

        request.startAsync();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/flight/show.jsp");
        dispatcher.forward(request, response);
    }
}
