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

import com.mycompany.onlinebanking.model.Message;

/**
 * 24/07/2018
 * @author jagon
 */
public class MessageService {
    //===========================================
    //=	Attributes
    //===========================================

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Unit");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    List<Message> list = new ArrayList<>();
    int length;
           
    public MessageService() {
 
        //Message m1 = new Message(1,"Hello world1", "Enda");
        //Message m2 = new Message(2,"Hello world2", "Paddy");
        System.out.println("message service constr");
       
        //list.add(m1);
        //list.add(m2);
        //length = list.size();
    }
        
    //This needs to change, first find the number of fields in the table (getLength)
    public List<Message> getAllMessages() {
        for(int i = 1; i <= 3; i++)
            list.add(em.find(Message.class, i));
        return list;
    }
    
    public Message getMessage(int id) {
        Message test = em.find(Message.class, id);  
            return test;
    }
    public int getLength(){
        //if != null
        return length;
    }
    
    public void deleteMessage(int id) {
        Message test = em.find(Message.class, id);   
        if (test != null) {
            tx.begin();
            em.remove(test);
            // Commit the current resource transaction
            //writing any unflushed changes to the database.
            tx.commit();
            
            em.close();
        }
    }
    
    public void editMessage(int id, String newMessage){
        Message test = em.find(Message.class, id);
        String author = test.getAuthor();
        Message updatedMessage = new Message(id, newMessage, author);
        if (test != null) {
            tx.begin();
            em.merge(updatedMessage);
            tx.commit();
            em.close();
        }
    }
    
    public void addMessage(int id, String message, String author){
        Message test = em.find(Message.class, id);
        Message newMessage = new Message(id, message, author);
        //Message test = new Message(id, message, author);
        if (test != null) {
            System.out.println("No user found");
        }
        tx.begin();
        em.merge(newMessage);
        tx.commit();
        em.close();
    }
}

