/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.onlinebanking.service;

import com.mycompany.onlinebanking.model.Transaction;
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
public class TransactionService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Unit");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    List<Transaction> list = new ArrayList<>();
    int length;
    
    public TransactionService(){
        System.out.println("transaction service constr");
    }
    
    public List<Transaction> getAllTransactions() {
        return list;
    }
    
    public Transaction getTransaction(int id) {
        Transaction test = em.find(Transaction.class, id);  
            return test;
    }
    public int getLength(){
        return length;
    }
    
    public void deleteTransaction(int id) {
        Transaction test = em.find(Transaction.class, id);   
        if (test !=null) {
            tx.begin();
            em.remove(test);
            tx.commit();
            em.close();
        }
    }
    
    public void addTransaction(int id, String type, String description, double amount){
        Transaction test = em.find(Transaction.class, id);
        Transaction newTransaction = new Transaction(id, type, description, amount);
        if (test != null) {
            System.out.println("No transaction found");
        }
        tx.begin();
        em.merge(newTransaction);
        tx.commit();
        em.close();
    }

}
