package ccit.g2airline.project11deployableweb.servlet.reservation;

import ccit.g2airline.project11deployableweb.dictionary.WebRoute;
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/reservation/create.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        response.sendRedirect("/");
    }
}
