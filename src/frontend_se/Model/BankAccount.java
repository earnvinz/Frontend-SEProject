package frontend_se.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.net.HttpURLConnection;
@JsonIgnoreProperties (ignoreUnknown = true) /*จัดการ bank account ที่รับเข้ามา */

public class BankAccount {

    private int id;
    private int customerId;
    private String accountNumber;
    private String type;
    private double balance;


    public BankAccount(int customerId,String accountNumber,String type,double balance){
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.type = type;
        this.balance = balance;

    }
    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", accountNumber='" + accountNumber + '\'' +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                '}';
    }

    public BankAccount() {

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}