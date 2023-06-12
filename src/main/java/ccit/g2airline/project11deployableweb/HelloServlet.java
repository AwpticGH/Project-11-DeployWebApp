package ccit.g2airline.project11deployableweb;

import java.io.*;

import ccit.g2airline.project11deployableweb.config.DatabaseConfig;
import ccit.g2airline.project11deployableweb.config.FirebaseConfig;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    DatabaseReference ref;

    public void init() {
        FirebaseConfig.init();
        String path = "users/1";
        ref = DatabaseConfig.getReference(path);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        // Hello
        System.out.println(ref.getKey());
        System.out.println(ref.getDatabase().toString());
        System.out.println(ref.getParent().toString());
        System.out.println(ref.getRoot().toString());
        System.out.println(ref.child("email").toString());

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("f");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("g");
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("a");
                PrintWriter out = null;
                try {
                    out = response.getWriter();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("b");
                out.println("<html><body>");
                out.println("<h1>" + dataSnapshot.getKey() + "</h1>");
                out.println("<h1>" + dataSnapshot.child("email").getKey() + "</h1>");
                out.println("<h1>" + dataSnapshot.getValue().toString() + "</h1>");
                out.println("<h1>" + dataSnapshot.child("email").getValue().toString() + "</h1>");
                out.println("</body></html>");
                System.out.println("c");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("DATABASE ERROR");
                databaseError.getMessage();
            }
        });
        System.out.println("e");
    }

    public void destroy() {
    }
}