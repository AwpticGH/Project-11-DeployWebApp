package ccit.g2airline.project11deployableweb.servlet.auth;

import ccit.g2airline.project11deployableweb.controller.AuthController;
import ccit.g2airline.project11deployableweb.dictionary.WebRoute;
import ccit.g2airline.project11deployableweb.model.database.AuthModel;
import ccit.g2airline.project11deployableweb.myInterface.servlet.GetServlet;
import ccit.g2airline.project11deployableweb.myInterface.servlet.PostServlet;
import ccit.g2airline.project11deployableweb.servlet.FirebaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "authLoginServlet", value = WebRoute.AUTH_LOGIN, asyncSupported = true)
public class LoginServlet extends HttpServlet implements PostServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AuthModel model = new AuthModel();
        model.setEmail(email);
        model.setPassword(password);

        request.startAsync();
        AuthController lc = new AuthController();
        lc.get(request, model);

        response.sendRedirect(WebRoute.FLIGHT_INDEX);
    }
}
