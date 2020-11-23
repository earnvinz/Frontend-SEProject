package frontend_se.Service;


import frontend_se.Model.BankAccount;
import frontend_se.Model.Customer;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class BankAccountService {

    private RestTemplate restTemplate;

    public BankAccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    public List<BankAccount> getCustomerBankAccount(int customerId) {
        String url = "http://localhost:8091/api/bankaccount/customer/" + customerId;  // "http://localhost:8091/api/bankaccount/customer/" +customerId;

        ResponseEntity<BankAccount[]> response =
                restTemplate.getForEntity(url, BankAccount[].class);

        BankAccount[] accounts = response.getBody();

        return Arrays.asList(accounts);
    }

    public BankAccount getBankAccountnumber(String accountNumber){

        String url = "http://localhost:8091/api/bankaccount/accountnumber/"+accountNumber;
        try {
            ResponseEntity<BankAccount> response = restTemplate.getForEntity(url,BankAccount.class);

            BankAccount bankAccount = response.getBody();
            return bankAccount;
        }
        catch (NoSuchElementException e){
            return null;
        }

    }

    public void updateAccount(String accountNumber, BankAccount BankAccount){
        String url = "http://localhost:8091/api/bankaccount/accountnumber/"+accountNumber;
        restTemplate.put(url,BankAccount);

    }

    public void createAccount(BankAccount bankAccount){
        String url = "http://localhost:8091/api/bankaccount/";
        restTemplate.postForObject(url,bankAccount,BankAccount.class);
    }


}

