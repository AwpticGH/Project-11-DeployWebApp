package ccit.g2airline.project11deployableweb.servlet;

import ccit.g2airline.project11deployableweb.config.FirebaseConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FirebaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);

        if (!FirebaseConfig.isInitialized()) {
            FirebaseConfig.init();
            System.out.println("Firebase Initialized");
        }
    }
}
