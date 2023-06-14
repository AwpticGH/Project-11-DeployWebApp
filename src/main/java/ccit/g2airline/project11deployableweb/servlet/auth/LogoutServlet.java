package ccit.g2airline.project11deployableweb.servlet.auth;

import ccit.g2airline.project11deployableweb.dictionary.WebRoute;
import ccit.g2airline.project11deployableweb.myInterface.servlet.GetServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "authLogoutServlet", value = WebRoute.AUTH_LOGOUT)
public class LogoutServlet extends HttpServlet implements GetServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(WebRoute.FLIGHT_INDEX);
    }
}
