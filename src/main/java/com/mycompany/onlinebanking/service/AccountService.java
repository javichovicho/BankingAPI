/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.onlinebanking.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.mycompany.onlinebanking.model.Account;
/**
 * 26/07/2018
 * @author jagon
 */
public class AccountService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Unit");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    List<Account> list = new ArrayList<>();
    int length;
    
    public AccountService(){
        System.out.println("account service constr");
    }
    
    public List<Account> getAllAccounts() {
        return list;
    }
    
    public Account getAccount(int id) {
        Account test = em.find(Account.class, id);  
            return test;
    }
    public int getLength(){
        return length;
    }
    
    public void deleteAccount(int id) {
        Account test = em.find(Account.class, id);   
        if (test !=null) {
            tx.begin();
            em.remove(test);
            tx.commit();
            em.close();
        }
    }
    
    public void editAccount(int id, double newBalance){
        Account test = em.find(Account.class, id);
        String type = test.getType();
        int number = test.getNumber();
        Account updatedAccount = new Account(id, type, number, newBalance);
        if (test != null) {
            tx.begin();
            em.merge(updatedAccount);
            tx.commit();
            em.close();
        }
    }
    
    public void addAccount(int id, String type, int number, double balance){
        Account test = em.find(Account.class, id);
        Account newAccount = new Account(id, type, number, balance);
        if (test != null) {
            System.out.println("No user found");
        }
        tx.begin();
        em.merge(newAccount);
        tx.commit();
        em.close();
    }

}
