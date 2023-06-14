package ccit.g2airline.project11deployableweb.servlet.auth;

import ccit.g2airline.project11deployableweb.controller.AuthController;
import ccit.g2airline.project11deployableweb.dictionary.WebRoute;
import ccit.g2airline.project11deployableweb.dictionary.WebVariable;
import ccit.g2airline.project11deployableweb.model.database.AuthModel;
import ccit.g2airline.project11deployableweb.myInterface.servlet.GetServlet;
import ccit.g2airline.project11deployableweb.myInterface.servlet.PostServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "authRegisterServlet", value = WebRoute.AUTH_CREATE, asyncSupported = true)
public class CreateServlet extends HttpServlet implements GetServlet, PostServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/auth/create.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter(WebVariable.USERNAME);
        String name = request.getParameter(WebVariable.NAME);
        String email = request.getParameter(WebVariable.EMAIL);
        String password = request.getParameter(WebVariable.PASSWORD);
        String passwordConfirmed = request.getParameter(WebVariable.PASSWORD_CONFIRMATION);
        String title = request.getParameter(WebVariable.TITLE);
        String phoneNumber = request.getParameter(WebVariable.PHONE_NUMBER);
        String gender = request.getParameter(WebVariable.GENDER);

        if (passwordConfirmed.equals(password)) {
            AuthModel model = new AuthModel();
            model.setUsername(username);
            model.setName(name);
            model.setEmail(email);
            model.setPassword(password);
            model.setTitle(title);
            model.setPhone_number(phoneNumber);
            model.setGender(gender);

            request.startAsync();
            AuthController controller = new AuthController();
            controller.create(request, model);

            response.sendRedirect(WebRoute.FLIGHT_INDEX);
        }
    }
}
