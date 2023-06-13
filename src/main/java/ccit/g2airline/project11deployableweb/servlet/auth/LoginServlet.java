package ccit.g2airline.project11deployableweb.servlet.auth;

import ccit.g2airline.project11deployableweb.controller.AuthController;
import ccit.g2airline.project11deployableweb.model.database.AuthModel;
import ccit.g2airline.project11deployableweb.servlet.FirebaseServlet;
import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "authLoginServlet", value = "/auth/login", asyncSupported = true)
public class LoginServlet extends FirebaseServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AuthModel model = new AuthModel();
        model.setEmail(email);
        model.setPassword(password);

        AsyncContext async = request.startAsync();
        AuthController lc = new AuthController();
        lc.get(request, model);

        response.sendRedirect("/");
    }
}
