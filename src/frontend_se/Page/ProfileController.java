package frontend_se.Page;

import animatefx.animation.FadeIn;
import frontend_se.Static.OnlineCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private
    Label fname;

    @FXML
    private Label lname;

    @FXML
    private Label cardnumber;

    @FXML
    private Label pin;

    @FXML
    public void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Account.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 611, 427));
        stage.show();
        stage.setResizable(false);
        new FadeIn(root).play();
    }

    @FXML
    public void ChangePassword(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChangePassword.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 423, 266));
        stage.show();
        stage.setResizable(false);
        new FadeIn(root).play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fname.setText(OnlineCustomer.getCustomer().getFname());
        lname.setText(OnlineCustomer.getCustomer().getLname());
        cardnumber.setText(OnlineCustomer.getCustomer().getCardnumber());

        String hashpassword = "";
        for(int i = 0 ; i < OnlineCustomer.getCustomer().getPin().length();i++){
            hashpassword = hashpassword+"*";
        }

        pin.setText(hashpassword);
    }
}
