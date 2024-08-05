package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class CustomerAPI {
    

    
    List<Customer> customers = new ArrayList<>();

    public CustomerAPI() {
        Customer chan = new Customer(1L, "Chan Woo Kim", "chankim9603@gmail.com");
        Customer lee = new Customer(2L, "Lee", "lee0305@gmail.com");
        customers.add(chan);
        customers.add(lee);
    }
    
    @GetMapping("/customers")
    public String getAllCustomers() {
        String response = "{";
        for (int i = 0; i < customers.size(); i ++) {
            if (i == customers.size() - 1) {
                response += customers.get(i).getID() + ", " + customers.get(i).getName() + ", " + customers.get(i).getEmail();
            } else {
                response += customers.get(i).getID() + ", " + customers.get(i).getName() + ", " + customers.get(i).getEmail() + "; ";
            }
        }
        response += "}";
        return response;
    }

    @GetMapping("/customers/{id}") 
    public String getCustomerByID(@PathVariable Long id) {
        String response = "{";
        for (int i = 0; i < customers.size(); i ++) {
            Customer customer = customers.get(i);
            if (customer.getID() == id) {
                response += customer.getID() + ", " + customer.getName() + ", " + customer.getEmail() + "}";
                return response;
            }
        }        
        return "Customer not found";
    }

}
