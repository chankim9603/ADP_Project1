package com.example.customer_management_service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    // Get all customers.
    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return repo.findAll();

    }
    // Get a customer by id.
    @GetMapping("/customers/{id}")
    public Optional<Customer> getCustomerbyID(@PathVariable("id") int id){
        return repo.findById(id);
        // type mismatch, so changed to <optional>
        // for(Customer customer: customers){
        //     if(customer.getId() == id){
        //         return customer;
        //     }
        // }
        // return null;
        
    }

    // Update a customer.

    
	// public ResponseEntity<?> updateCustomer(
	// 		@RequestBody Customer newCustomer,
	// 		@PathVariable("customerId") long customerId) 
	// {
	// 	if (newCustomer.getId() != customerId || newCustomer.getName() == null || newCustomer.getEmail() == null) {
	// 		return ResponseEntity.badRequest().build();
	// 	}
	// 	newCustomer = repo.save(newCustomer);
	// 	return ResponseEntity.ok().build();
	// }

    @PutMapping("/{customerId}")
    public Customer updateCustomer(@RequestBody Customer newCustomer, 
    @PathVariable("customerId") int customerId){

        return repo.findById(customerId)
            .map(customer -> {
                customer.setName(newCustomer.getName());
                customer.setEmail(newCustomer.getEmail());
                return repo.save(customer);
            })
            .orElseGet(() -> {
                return repo.save(newCustomer);
            });

    }

    



    




}
