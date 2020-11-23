package frontend_se.Page;

import animatefx.animation.*;
import frontend_se.Model.BankAccount;
import frontend_se.Model.Customer;
import frontend_se.Service.BankAccountService;
import frontend_se.Service.CustomerService;
import frontend_se.Static.OnlineCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
    private BankAccountService bankAccountService;

    @FXML
    AnchorPane pane;
    @FXML
    private Button profilebtn;

    @FXML
    private Button tranferbtn;

    @FXML
    private Button logoutbtn;

    @FXML
    private TableView<BankAccount> table;

    @FXML
    private TableColumn<BankAccount, String> acnumber;

    @FXML
    private TableColumn<BankAccount, String> type;

    @FXML
    private TableColumn<BankAccount, Double> balance;

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 426, 470));
        stage.show();
        stage.setResizable(false);
        new FadeIn(root).play();
    }

    @FXML
    private void profile(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 423, 401));
        stage.show();
        stage.setResizable(false);
        new FadeIn(root).play();
    }

    @FXML
    private void tranfer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Tranfer.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 514, 455));
        stage.show();
        stage.setResizable(false);
        new FadeInLeft(root).play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FadeInLeftBig(profilebtn).play();
        new FadeInLeftBig(logoutbtn).play();
        new FadeInLeftBig(tranferbtn).play();


//        CustomerService customerService = new CustomerService(new RestTemplate());
//        List<Customer> allcus =  customerService.getAllcustomer();


        acnumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        bankAccountService = new BankAccountService(new RestTemplate());
        List<BankAccount> bankAccounts = bankAccountService.getCustomerBankAccount(OnlineCustomer.getCustomer().getId());

        for(BankAccount b : bankAccounts){
            table.getItems().add(b);
        }
        new BounceInDown(table).play();



    }
}
