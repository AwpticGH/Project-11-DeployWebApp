package ccit.g2airline.project11deployableweb.servlet.flight;

import ccit.g2airline.project11deployableweb.controller.admin.DatabaseController;
import ccit.g2airline.project11deployableweb.dictionary.WebRoute;
import ccit.g2airline.project11deployableweb.model.BaseModel;
import ccit.g2airline.project11deployableweb.myInterface.servlet.GetServlet;
import ccit.g2airline.project11deployableweb.servlet.FirebaseServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "flightIndexServlet", value = WebRoute.FLIGHT_INDEX)
public class IndexServlet extends FirebaseServlet implements GetServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/flight/index.jsp");
        dispatcher.forward(request, response);
    }
}
