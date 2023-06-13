package ccit.g2airline.project11deployableweb;

import java.io.*;

import ccit.g2airline.project11deployableweb.config.DatabaseConfig;
import ccit.g2airline.project11deployableweb.config.FirebaseConfig;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    DatabaseReference ref;

    public void init() {
        FirebaseConfig.init();
        String path = "users/1/email";
        ref = DatabaseConfig.getReference(path);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/hello.jsp");
        try {
            System.out.println("dispatching");
            dispatcher.forward(request, response);
            System.out.println("dispatched");
        } catch (ServletException e) {
            System.out.println("SERVLET ERROR");
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("FILE NOT FOUND");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String email = dataSnapshot.getValue().toString();
                System.out.println(email);
                request.setAttribute("email", email);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("DATABASE ERROR");
                databaseError.getMessage();
            }
        });
    }

    public void destroy() {
    }
}