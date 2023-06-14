package ccit.g2airline.project11deployableweb.servlet.flight;

import ccit.g2airline.project11deployableweb.controller.AirportController;
import ccit.g2airline.project11deployableweb.dictionary.WebRoute;
import ccit.g2airline.project11deployableweb.dictionary.WebVariable;
import ccit.g2airline.project11deployableweb.helper.StringHelper;
import ccit.g2airline.project11deployableweb.model.database.AirportModel;
import ccit.g2airline.project11deployableweb.model.database.FlightModel;
import ccit.g2airline.project11deployableweb.model.web.FlightSearchedModel;
import ccit.g2airline.project11deployableweb.myInterface.servlet.GetServlet;
import ccit.g2airline.project11deployableweb.servlet.FirebaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "flightShowServlet", value = WebRoute.FLIGHT_SHOW, asyncSupported = true)
public class ShowServlet extends FirebaseServlet implements GetServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String departureValue = request.getParameter(WebVariable.DEPARTURE_CITY);
        String departure = StringHelper.castCity(departureValue);
        String destinationValue = request.getParameter(WebVariable.DESTINATION_CITY);
        String destination = StringHelper.castCity(destinationValue);
        String departureDate = request.getParameter(WebVariable.DEPARTURE_DATE);
        String passengerSeatClass = request.getParameter(WebVariable.PASSENGER_SEAT_CLASS);

        AirportModel airportDepartureModel = new AirportModel();
        airportDepartureModel.setCity(departure);

        AirportModel airportDestinationModel = new AirportModel();
        airportDestinationModel.setCity(destination);

        List<FlightModel> flightModels = new ArrayList<>();
        FlightModel flightModel = new FlightModel();
        flightModel.setTime_of_departure(departureDate);
        flightModels.add(0, flightModel);

        FlightSearchedModel model = new FlightSearchedModel();
        model.setAirportDepartureModel(airportDepartureModel);
        model.setAirportDestinationModel(airportDestinationModel);
        model.setFlightModels(flightModels);
        model.setPassengerSeatClass(passengerSeatClass);

        System.out.println("Looking For Departure : " + departure);
        System.out.println("Looking For Destination : " + destination);
        System.out.println("Looking For Date : " + departureDate);

        AsyncContext asyncContext = request.startAsync();
        asyncContext.setTimeout(60000);
        AirportController ac = new AirportController();
        ac.get(request, model);
        request.getAsyncContext().addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent asyncEvent) throws IOException {
                try {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/flight/show.jsp");
                    dispatcher.forward(request, response);
                }
                catch (ServletException e) {
                    response.sendRedirect(WebRoute.FLIGHT_INDEX);
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onTimeout(AsyncEvent asyncEvent) throws IOException {

            }

            @Override
            public void onError(AsyncEvent asyncEvent) throws IOException {
                System.out.println("Async Error");
                System.out.println(asyncEvent.toString());
                request.setAttribute(WebVariable.ALERT, "An Error Has Occurred!");
                response.sendRedirect(WebRoute.FLIGHT_INDEX);
            }

            @Override
            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {

            }
        });
    }
}
