package com.example.customer_management_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
	CustomerRepository repo;

    ArrayList<Customer> customers = new ArrayList<>();

    public CustomerController(){
        customers.add(new Customer(1, "Harry Potter", "hpotter@gmail.com"));
        customers.add(new Customer(2, "Sheldon Cooper", "scooper2@gmail.com"));
    }

    @GetMapping
    public String welcome(){
        return "Welcome to the Customer Management!";
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return repo.findAll();

    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerbyID(@PathVariable("id") int id){
        for(Customer customer: customers){
            if(customer.getId() == id){
                return customer;
            }
        }
        return null;
        
        // return customers.get(id);

        
    }





}
