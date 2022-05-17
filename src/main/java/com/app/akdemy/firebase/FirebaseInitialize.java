package com.app.akdemy.firebase;

import java.io.FileInputStream;
import java.io.InputStream;

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
            
            InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://akdemy-7d7bc-default-rtdb.firebaseio.com")
            .build();

            if(FirebaseApp.getApps().isEmpty()){
                FirebaseApp.initializeApp(options);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
