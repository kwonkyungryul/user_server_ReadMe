package shop.readmecorp.userserverreadme.config;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void init() {
        try {
            // TODO 배포 시 임시로 만듦
            FileInputStream serviceAccount = new FileInputStream("/Users/nomadlab/Documents/git/user_server_ReadMe/src/main/resources/static/readmeoauth.json");
//            FileInputStream serviceAccount = new FileInputStream("/home/ubuntu/projects/readmeoauth.json");

            FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sendPushNotification(String token, String title, String body, String notificationType, String notificationData) {

        // FCM 메시지 생성
        Message message = Message.builder()
                .setToken(token)
                .putData("title", title)
                .putData("body", body)
                .putData("notificationType", notificationType)
                .putData("notificationData", notificationData)
                .build();

        // FCM 서버로 메시지 전송
        try {
            ApiFuture<String> send = FirebaseMessaging.getInstance().sendAsync(message);
            String response = send.get();
            System.out.println("Successfully sent message: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
