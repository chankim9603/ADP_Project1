package com.example.customer_management_service;

public class Customer {

    int id;
    String name;
    String email;

    public Customer(){}

    public Customer(int id, String name, String email){
        super();
        this.id = id;
        this.name = name;
        this.email = email;
    }
    //getters and setters.

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    

}
