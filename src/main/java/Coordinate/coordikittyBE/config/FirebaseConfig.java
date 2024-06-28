package Coordinate.coordikittyBE.config;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    @Value("${fcm.key}")
    private String firebaseKey;

    @PostConstruct
    public void init() {
        try {
            // 기존 Firebase 초기화가 있는지 확인
            if (FirebaseApp.getApps().isEmpty()) {
                ClassPathResource resource = new ClassPathResource(firebaseKey);
                InputStream serviceAccount = resource.getInputStream();

                GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(credentials)
                        .build();

                FirebaseApp.initializeApp(options);
                System.out.println("Firebase initialization successful");
            } else {
                System.out.println("Firebase already initialized");
            }
        } catch (IOException e) {
            // 로그 출력 또는 에러 처리
            System.err.println("Error initializing Firebase: " + e.getMessage());
            e.printStackTrace();
        }
    }
}