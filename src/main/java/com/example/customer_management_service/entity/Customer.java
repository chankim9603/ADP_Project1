package com.example.customer_management_service.entity;


import javax.persistence.Entity;
@Entity
@Table(name = "customer")
public class Customer {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    

    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setID(long id) {
        this.id = id;
    }

    public long getID() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}