package com.samsung.sodam.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FCMInitializer {

    @Value("classpath:/firebase/fcm_account_key.json")
    private Resource resource;

    @PostConstruct
    public void initFirebase() {
        try {
            // Service Account를 이용하여 Fireabse Admin SDK 초기화
            FileInputStream serviceAccount = null;
            try {
                serviceAccount = new FileInputStream(resource.getFile());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);

            System.out.println("FCM 초기화 완");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}