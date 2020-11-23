package frontend_se.Page;


import frontend_se.Model.Customer;
import frontend_se.Service.CustomerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class RegisterController {
    private CustomerService customerService = new CustomerService(new RestTemplate());
    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField cardnumber;

    @FXML
    private PasswordField password;


    public void Back(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 426, 470));
        stage.show();
        stage.setResizable(false);
    }
    public void accept(ActionEvent event) throws IOException{
        if(!fname.getText().equals("") && !lname.getText().equals("") && !cardnumber.getText().equals("") && !password.getText().equals("")
        && customerService.getBycardNumber(cardnumber.getText()) == null){
            customerService.createCustomer(new Customer(fname.getText(),lname.getText(),cardnumber.getText(),password.getText()));

            fname.setText("");
            lname.setText("");
            cardnumber.setText("");
            password.setText("");
        }
        else{
            System.out.println("notpass");
        }

    }
}
