package ccit.g2airline.project11deployableweb.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FirebaseConfig {

    private static final String filePath = "H:\\My Drive\\CCIT\\Project\\IntelliJ\\Project-11-DeployableWeb\\serviceAccount.json";

    public static void init() {
            FirebaseApp.initializeApp();
//        try {
//            FileInputStream inputStream = new FileInputStream(filePath);
//            FirebaseOptions options = FirebaseOptions.builder()
//                    .setCredentials(GoogleCredentials.fromStream(inputStream))
//                    .build();
//
//            FirebaseApp.initializeApp(options);
//        }
//        catch (FileNotFoundException e) {
//            System.out.println("File Not Found!");
//            e.printStackTrace();
//        }
//        catch (IOException e) {
//            System.out.println("Output of OAuth 2.0 JSON File Contains an Error");
//            e.printStackTrace();
//        }
    }
}
