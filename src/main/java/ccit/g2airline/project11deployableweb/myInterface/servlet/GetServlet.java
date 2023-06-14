package ccit.g2airline.project11deployableweb.myInterface.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface GetServlet {

    void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

}
