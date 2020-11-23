package frontend_se.Page;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import frontend_se.Model.BankAccount;
import frontend_se.Service.BankAccountService;
import frontend_se.Static.OnlineCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TranferController implements Initializable {
    private int count;
    private BankAccountService bankAccountService;
    private List<BankAccount> bankAccountList;
    @FXML
    private AnchorPane pane;

    @FXML
    private CheckBox confirm;

    @FXML
    private Label acnumber;

    @FXML
    private Label type;

    @FXML
    private Label balance;

    @FXML
    private Button next,back;

    @FXML
    private AnchorPane amountpane;

    @FXML
    TextField tfaccountnumber,amount;
    @FXML
    private Label wacnumber,wamount,winning;



    @FXML
    void back(ActionEvent event) {
        count -= 1;
        acnumber.setText("Account-number   :   "+bankAccountList.get(count).getAccountNumber());
        type.setText("Type                       :   "+bankAccountList.get(count).getType());
        balance.setText("Balance                  :   " + bankAccountList.get(count).getBalance());
        if(count == 0){
            back.setDisable(true);
            next.setDisable(false);
        }
        else{
            back.setDisable(false);
            next.setDisable(false);
        }
    }

    @FXML
    void next(ActionEvent event) {
        count += 1;


        acnumber.setText("Account-number   :   "+bankAccountList.get(count).getAccountNumber());
        type.setText("Type                       :   "+bankAccountList.get(count).getType());
        balance.setText("Balance                  :   " + bankAccountList.get(count).getBalance());


        if(count+1 == bankAccountList.size()){
            next.setDisable(true);
            back.setDisable(false);
        }
        else{
            back.setDisable(false);
            next.setDisable(false);
        }
    }

    @FXML
    void backpage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Account.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 611, 427));
        stage.show();
        stage.setResizable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        count = 0 ;

        BankAccountService bankAccountService = new BankAccountService(new RestTemplate());
        bankAccountList = bankAccountService.getCustomerBankAccount(OnlineCustomer.getCustomer().getId());

        acnumber.setText(acnumber.getText()+bankAccountList.get(count).getAccountNumber());
        type.setText(type.getText()+bankAccountList.get(count).getType());
        balance.setText(balance.getText() + bankAccountList.get(count).getBalance());

        if (count+1 == bankAccountList.size()){
            next.setDisable(true);

        }
        back.setDisable(true);

    }
    @FXML
    void confirmed(ActionEvent event)  {
        if (confirm.isSelected()){
            next.setVisible(false);
            back.setVisible(false);


            amountpane.setVisible(true);
            new FadeIn(amountpane).play();
        }
        if (!confirm.isSelected()){
            next.setVisible(true);
            back.setVisible(true);

            new FadeOut(amountpane).play();

        }
    }
    public void tranfer(ActionEvent event){

        BankAccountService service = new BankAccountService(new RestTemplate());
        if(service.getBankAccountnumber(tfaccountnumber.getText()) != null &&
                !tfaccountnumber.getText().equals(bankAccountList.get(count).getAccountNumber()) && !tfaccountnumber.equals("")){


            wacnumber.setVisible(false);


            if(isNumeric(amount.getText()) && bankAccountList.get(count).getBalance() > Double.parseDouble(amount.getText()) ){
                wamount.setVisible(false);
                BankAccount tranferer = bankAccountList.get(count);

                tranferer.setBalance(tranferer.getBalance()-Double.parseDouble(amount.getText()));
                service.updateAccount(tranferer.getAccountNumber(),tranferer);

                BankAccount receiver = service.getBankAccountnumber(tfaccountnumber.getText());
                receiver.setBalance(receiver.getBalance()+Double.parseDouble(amount.getText()));

                service.updateAccount(receiver.getAccountNumber(),receiver);


                balance.setText("Balance                  :   " + tranferer.getBalance());
                winning.setVisible(true);
                tfaccountnumber.setText("");
                amount.setText("");

                confirm.setVisible(false);
                new FadeOut(amountpane).play();







            }
            else{
                wamount.setVisible(true);
            }
        }
        else{
            wacnumber.setVisible(true);
        }

    }



    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
