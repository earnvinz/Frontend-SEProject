package frontend_se.Page;

import animatefx.animation.*;
import frontend_se.Model.BankAccount;
import frontend_se.Model.Customer;
import frontend_se.Service.BankAccountService;
import frontend_se.Service.CustomerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private List<BankAccount> list;
    private CustomerService customerService;
    private BankAccountService bankAccountService;


    @FXML
    private AnchorPane openpane;
    @FXML
    private AnchorPane closepane;
    @FXML
    private AnchorPane deposit_withdrawpane;

    @FXML
    private Button openbtn;

    @FXML
    private Button closebtn;

    @FXML
    private Button moneybtn;

    @FXML
    private Button logoutbtn;
    @FXML
    private Button btnclose;

    @FXML
    private Button submitdepobtn;

    @FXML
    private Label errordepowith;

    @FXML
    private Label alertopen;

    @FXML
    private MenuButton type;

    @FXML
    private TextField cardnumberOpen;

    @FXML
    private TextField firstdeposit,cardnumberclose;

    @FXML
    private CheckBox confirm;

    @FXML
    private Label finishclose;

    @FXML
    private Label alertclose;





    @FXML
    private TextField acnumber;

    @FXML
    private MenuButton depowithchoice;

    @FXML
    private TextField amount;



    @FXML
    void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 426, 470));
        stage.show();
        stage.setResizable(false);
        new FadeIn(root).play();
    }

    @FXML
    void openAccount(ActionEvent event) {
        deposit_withdrawpane.setVisible(false);
        closepane.setVisible(false);
        openpane.setVisible(true);
        new FadeInRightBig(openpane).play();

    }
    @FXML
    void CloseAccount(ActionEvent event) {
        deposit_withdrawpane.setVisible(false);
        openpane.setVisible(false);
        closepane.setVisible(true);
        new FadeInRightBig(closepane).play();
    }
    @FXML
    void depowithdraw(ActionEvent event) {
        closepane.setVisible(false);
        openpane.setVisible(false);
        deposit_withdrawpane.setVisible(true);
        new FadeInRightBig(deposit_withdrawpane).play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        bankAccountService = new BankAccountService(new RestTemplate());
        customerService = new CustomerService(new RestTemplate());

        alertopen.setVisible(false);

        new FadeInLeftBig(openbtn).play();
        new FadeInLeftBig(moneybtn).play();
        new FadeInLeftBig(closebtn).play();
        new FadeInLeftBig(logoutbtn).play();


    }

    @FXML
    public void setSaving(ActionEvent event){
        type.setText("Saving");
    }
    @FXML
    public void setChecking(ActionEvent event){
        type.setText("Checking");
    }
    @FXML
    void createBankAccount(ActionEvent event){
        if(cardnumberOpen.getText().equals("") || firstdeposit.getText().equals("")) {
            alertopen.setText("Invalid data");
            alertopen.setVisible(true);
        }
        else {
            if (customerService.getBycardNumber(cardnumberOpen.getText()) != null) {
                if (!cardnumberOpen.getText().equals("") && !firstdeposit.getText().equals("") && isNumeric(firstdeposit.getText()) &&
                        Double.parseDouble(firstdeposit.getText()) > 0 && !type.getText().equals("Type")) {

                    alertopen.setVisible(false);
                    int i = 1;
                    Customer customer = customerService.getBycardNumber(cardnumberOpen.getText());


                    list = bankAccountService.getCustomerBankAccount(customer.getId()); // 1

                    String accountNumber = customer.getId() + "00000" + (list.size() + i);


                    BankAccount newbank = new BankAccount(customer.getId(), accountNumber
                            , type.getText(), Double.parseDouble(firstdeposit.getText()));
                    System.out.println(newbank.toString());
                    bankAccountService.createAccount(newbank);

                    cardnumberOpen.setText("");
                    firstdeposit.setText("");

                    alertopen.setVisible(true);
                    alertopen.setText("Finished");
                    new FadeIn(alertopen).play();
                    i += 1;

                } else {
                    alertopen.setText("Invalid Data , please try again");
                    alertopen.setVisible(true);
                }

            } else {
                alertopen.setText("Not found Cardnumber in system");
                alertopen.setVisible(true);
            }
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

    @FXML
    void checkConfirm(ActionEvent event) {
        if(confirm.isSelected()){
            btnclose.setVisible(true);
            new FadeIn(btnclose).play();
        }
        else{
            new FadeOut(btnclose).play();
        }

    }

    @FXML
    void deleteAccount(ActionEvent event){
        if(!cardnumberclose.getText().equals("")) {
            Customer customer = customerService.getBycardNumber(cardnumberclose.getText());
            if (customer != null) {
                customerService.DeleteAccount(customer.getCardnumber(), customer);
                bankAccountService.DeleteByCustomerId(customer.getId());

                cardnumberclose.setText("");
                confirm.setSelected(false);
                alertclose.setVisible(false);
                finishclose.setVisible(true);
            } else {
                alertclose.setVisible(true);
            }
        }
        else{
            alertclose.setVisible(true);
        }

    }

    @FXML
    void depo_withDraw(ActionEvent event){
        if(!acnumber.getText().equals("") && !amount.getText().equals("")) {
            BankAccount bankAccount = bankAccountService.getBankAccountnumber(acnumber.getText());
            if(bankAccount != null){
                if(depowithchoice.getText().equals("Deposit")){

                    if(isNumeric(amount.getText())){
                        if(Double.parseDouble(amount.getText()) > 0){
                            bankAccount.setBalance(bankAccount.getBalance() + Double.parseDouble(amount.getText()));
                            bankAccountService.updateAccount(acnumber.getText(),bankAccount);
                            errordepowith.setText("Finished");
                            errordepowith.setVisible(true);
                        }
                        else{
                            errordepowith.setText("Invalid amount , please try again");// amount < 0
                            errordepowith.setVisible(true);
                        }

                    }
                    else{
                        errordepowith.setText("Invalid amount , please try again"); // amount is not number
                        errordepowith.setVisible(true);
                    }
                }
                else if(depowithchoice.getText().equals("Withdraw")){
                    System.out.println("withdraw pass");
                    if(isNumeric(amount.getText())){
                        System.out.println("isnumeric pass");
                        if(Double.parseDouble(amount.getText()) > 0) {

                            if (bankAccount.getBalance() > Double.parseDouble(amount.getText())) {

                                bankAccount.setBalance(bankAccount.getBalance() - Double.parseDouble(amount.getText()));
                                bankAccountService.updateAccount(acnumber.getText(),bankAccount);
                                errordepowith.setText("Finished");
                                errordepowith.setVisible(true);
                            }
                            else {
                                errordepowith.setText("Balance is not left");
                                errordepowith.setVisible(true); // bank acc < amount
                            }
                        }
                        else{
                            errordepowith.setText("Invalid amount , please try again");// amount < 0
                            errordepowith.setVisible(true);
                        }

                    }
                    else{
                        errordepowith.setText("Invalid amount , please try again");// amount is not number
                        errordepowith.setVisible(true);
                    }
                }

            }
            else{
                errordepowith.setText("Account number is not found , please try again");// account not found
                errordepowith.setVisible(true);
            }
        }
        else{
            errordepowith.setText("Invalid input , please try again");// "" ""
            errordepowith.setVisible(true);
        }

    }
    @FXML
    void setDeposit(ActionEvent event){
        depowithchoice.setText("Deposit");
        submitdepobtn.setDisable(false);
        acnumber.setDisable(false);
        amount.setDisable(false);
        new FadeInRightBig(submitdepobtn).play();
        new FadeInRightBig(acnumber).play();
        new FadeInRightBig(amount).play();
    }

    @FXML
    void setWithdraw(ActionEvent event){
        depowithchoice.setText("Withdraw");
        submitdepobtn.setDisable(false);
        acnumber.setDisable(false);
        amount.setDisable(false);
        new FadeInRightBig(submitdepobtn).play();
        new FadeInRightBig(acnumber).play();
        new FadeInRightBig(amount).play();
    }

}