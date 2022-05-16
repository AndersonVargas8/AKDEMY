package com.app.akdemy.firebase;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.stereotype.Service;

@Service
public class FirebaseInitialize {
    
    @PostConstruct
    public void initialize(){
        try {
            
            String path = getClass().getClassLoader().getResource("serviceAccountKey.json").toString();
            
            FileInputStream serviceAccount =
            new FileInputStream(path);

            FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://akdemy-7d7bc-default-rtdb.firebaseio.com")
            .build();

            FirebaseApp.initializeApp(options);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
