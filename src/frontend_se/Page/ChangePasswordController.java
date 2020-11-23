package frontend_se.Page;

import animatefx.animation.FadeIn;
import frontend_se.Model.Customer;
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
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangePasswordController implements Initializable {

    @FXML
    private PasswordField Opassword;

    @FXML
    private PasswordField Npassword;

    @FXML
    private PasswordField Cpassword;

    @FXML
    private Label alert;

    @FXML
    void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 423, 401));
        stage.show();
        stage.setResizable(false);
        new FadeIn(root).play();
    }

    @FXML
    void accept(ActionEvent event) {
        if(Opassword.getText().equals(OnlineCustomer.getCustomer().getPin())){
            if(Npassword.getText().equals(Cpassword.getText())){
                Customer update = OnlineCustomer.getCustomer();
                update.setPin(Npassword.getText());


                CustomerService service = new CustomerService(new RestTemplate());
                service.updateOneCustomer(update.getId(),update);

                alert.setText("Password was changed :) ");
                alert.setVisible(true);

                Opassword.setText("");
                Npassword.setText("");
                Cpassword.setText("");


            }
            else{
                alert.setText("Password doesn't match :( ");
                alert.setVisible(true);
            }

        }
        else{
            alert.setText("Password Incorrected :( ");
            alert.setVisible(true);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alert.setVisible(false);
    }
}
