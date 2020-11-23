package frontend_se.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Customer {

    private int id;

    private String fname;
    private String lname;
    private String cardnumber;
    private String pin;

    public Customer(int id, String fname, String lname,String cardnumber, String pin) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.cardnumber = cardnumber;
        this.pin = pin;
    }
    public Customer(String fname,String lname,String cardnumber,String pin){
        this.fname = fname;
        this.lname = lname;
        this.cardnumber = cardnumber;
        this.pin = pin;
    }

    public Customer() {

    }

    public Customer(String eieiei, String auaua, String s) {
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", cardnumber='" + cardnumber + '\'' +
                ", pin='" + pin + '\'' +
                '}';
    }
}
