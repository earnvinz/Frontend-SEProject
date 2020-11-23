package frontend_se.Service;

import frontend_se.Model.BankAccount;
import frontend_se.Model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class CustomerService {
    private RestTemplate restTemplate;

    public CustomerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createCustomer(Customer customer){
        String url = "http://localhost:8090/api/customer/";
        restTemplate.postForObject(url,customer,Customer.class);
    }

    public void updateOneCustomer(int id,Customer customer){
        String url = "http://localhost:8090/api/customer/"+id;
        restTemplate.put(url,customer);
    }





    public List<Customer> getAllcustomer(){
        String url = "http://localhost:8090/api/customer/";
        ResponseEntity<Customer[]> response =
                restTemplate.getForEntity(url, Customer[].class);
        Customer[] array = response.getBody();
        return Arrays.asList(array);
    }

    public Customer getoneCustomer(int id){
        String url = "http://localhost:8090/api/customer/"+id;
        try{
        ResponseEntity<Customer> response =
                restTemplate.getForEntity(url, Customer.class);
        Customer customer = response.getBody();
        return customer;
        }
        catch (NoSuchElementException e){
            return null;
        }


    }
    public Customer getBycardNumber(String cardnumber){
        String url = "http://localhost:8090/api/customer/Customer/cardnumber/"+cardnumber;
        try {
            ResponseEntity<Customer> response = restTemplate.getForEntity(url,Customer.class);
            Customer customer = response.getBody();
            return customer;
        }
        catch (NoSuchElementException e){
            return null;
        }
    }
    public Customer DeleteAccount(String cardnumber,Customer customer){

        String url = "http://localhost:8090/api/customer/Customer/cardnumber/"+cardnumber;
        try {
            ResponseEntity<Customer> response = restTemplate.getForEntity(url,Customer.class);
            Customer customer1 = response.getBody();
            restTemplate.delete(url,customer1,Customer.class);
            return customer1;
        }
        catch (NoSuchElementException e){
            return null;
        }

    }
}
