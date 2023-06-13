package ccit.g2airline.project11deployableweb.servlet;

import ccit.g2airline.project11deployableweb.config.FirebaseConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class FirebaseServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();

        FirebaseConfig.init();
        System.out.println("Firebase Initialized");
    }
}
