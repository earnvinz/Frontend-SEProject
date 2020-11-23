package frontend_se.Page;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInLeft;
import frontend_se.Service.CustomerService;
import frontend_se.Static.OnlineCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class SigninController {
    @FXML
    Hyperlink register;
    @FXML
    private TextField Id;
    @FXML
    private PasswordField pin;
    private CustomerService customerService;

    public void login(ActionEvent event) throws IOException {
        this.customerService = new CustomerService(new RestTemplate());
        if(Id.getText().equals("test") && pin.getText().equals("test")){

            Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 611, 427));
            stage.show();
            stage.setResizable(false);
        }
        else if(!Id.getText().equals("")) {


                int id = Integer.parseInt(Id.getText());
                if (customerService.getoneCustomer(id).getPin().equals(pin.getText())) {
                    OnlineCustomer.setCustomer(customerService.getoneCustomer(id));


                    Parent root = FXMLLoader.load(getClass().getResource("Account.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root, 611, 427));
                    stage.show();
                    stage.setResizable(false);

                }

        }


    }
    public void register(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 523, 402));
        stage.show();
        stage.setResizable(false);
        new FadeIn(root).play();
    }
}
