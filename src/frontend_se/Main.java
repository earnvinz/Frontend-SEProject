package frontend_se;

import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Page/Signin.fxml"));
        primaryStage.setTitle("frontend-SE-project");
        primaryStage.setScene(new Scene(root, 426, 470));
        primaryStage.show();
        new FadeIn(root).play();
    }


    public static void main(String[] args) {

        launch(args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();

    }
}
