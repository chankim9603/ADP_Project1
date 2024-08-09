package com.example.customer_management_service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
	CustomerRepository repo;

    ArrayList<Customer> customers = new ArrayList<>();

    public CustomerController(){
        customers.add(new Customer(1, "Harry Potter", "hpotter@gmail.com", "harry"));
        customers.add(new Customer(2, "Sheldon Cooper", "scooper2@gmail.com", "sheldon"));
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("Welcome to the Customer Management!");
    }
    // Get all customers.
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(
        @RequestHeader(value = "security", required = true) String security
    ){
        List<Customer> customers = repo.findAll();
        return ResponseEntity.ok(customers);
    }
    // Get a customer by id.
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(
        @PathVariable("id") int id,
        @RequestHeader(value = "security", required = true) String security
    ){
        return repo.findById(id)
                   .map(customer -> ResponseEntity.ok().body(customer))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable("name") String name) {
        return repo.findByName(name)
                   .map(customer -> ResponseEntity.ok().body(customer))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
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


    // Update a customer.

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(
        @RequestBody Customer newCustomer, 
        @PathVariable("customerId") int customerId,
        @RequestHeader(value = "security", required = true) String security
    ){
        return repo.findById(customerId)
                   .map(customer -> {
                       customer.setName(newCustomer.getName());
                       customer.setEmail(newCustomer.getEmail());
                       customer.setPassword(newCustomer.getPassword());
                       Customer updatedCustomer = repo.save(customer);
                       return ResponseEntity.ok(updatedCustomer);
                   })
                   .orElseGet(() -> {
                       Customer savedCustomer = repo.save(newCustomer);
                       return ResponseEntity.ok(savedCustomer);
                   });
    }

    // delete a customer

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(
        @PathVariable int id,
        @RequestHeader(value = "security", required = true) String security
    ){
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // insert a customer

    @PostMapping
    public ResponseEntity<Customer> newCustomer(
        @RequestBody Customer newCustomer
    ){
        Customer savedCustomer = repo.save(newCustomer);
        return ResponseEntity
            .status(201)
            .body(savedCustomer);
    }
    



    




}
