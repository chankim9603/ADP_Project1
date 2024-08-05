package com.example;

public class Customer {

    private long id;
    private String name;
    private String email;    

    public Customer() {
    }
    
    public Customer(long id, String name, String email) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
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

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return this.email;
    }
}