/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.onlinebanking.service;

import com.mycompany.onlinebanking.model.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 28/07/2018
 * @author jagon
 */
public class CustomerService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Unit");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    List<Customer> list = new ArrayList<>();
    int length;
    
    public CustomerService(){
        System.out.println("customer service constr");
    }
    
    public List<Customer> getAllCustomers() {
        return list;
    }
    
    public Customer getCustomer(int id) {
        Customer test = em.find(Customer.class, id);  
            return test;
    }
    public int getLength(){
        return length;
    }
    
    public void deleteCustomer(int id) {
        Customer test = em.find(Customer.class, id);   
        if (test !=null) {
            tx.begin();
            em.remove(test);
            tx.commit();
            em.close();
        }
    }
    
    public void editCustomer(int id, int newPin){
        Customer test = em.find(Customer.class, id);
        String name = test.getName();
        //int pin = test.getPin();
        Customer updatedCustomer = new Customer(id, name, newPin);
        if (test != null) {
            tx.begin();
            em.merge(updatedCustomer);
            tx.commit();
            em.close();
        }
    }
    
    public void addCustomer(int id, String name, int pin){
        Customer test = em.find(Customer.class, id);
        Customer newCustomer = new Customer(id, name, pin);
        if (test != null) {
            System.out.println("No user found");
        }
        tx.begin();
        em.merge(newCustomer);
        tx.commit();
        em.close();
    }

}
