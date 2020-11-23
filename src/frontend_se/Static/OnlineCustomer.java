package frontend_se.Static;

import frontend_se.Model.Customer;

public class OnlineCustomer {
    private static Customer customer;

    public static Customer getCustomer() {
        return customer;
    }

    public static void setCustomer(Customer customer) {
        OnlineCustomer.customer = customer;
    }
}
