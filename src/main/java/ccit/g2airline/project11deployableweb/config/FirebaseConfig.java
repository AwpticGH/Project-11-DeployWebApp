package ccit.g2airline.project11deployableweb.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.servlet.http.HttpServletRequest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FirebaseConfig {

    public static void init() {
        String path = "H:\\My Drive\\CCIT\\Project\\IntelliJ\\Project-11-DeployableWeb\\src\\main\\webapp\\secret\\ServiceAccountKey.json";
        FileInputStream serviceAccount;
        FirebaseOptions options;
        try {
            serviceAccount = new FileInputStream(path);
            options = FirebaseOptions.builder()
                    .setProjectId("g2airline-8447d")
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        FirebaseApp.initializeApp(options);
    }

    public static boolean isInitialized() {
        return !FirebaseApp.getApps().isEmpty();
    }
}
