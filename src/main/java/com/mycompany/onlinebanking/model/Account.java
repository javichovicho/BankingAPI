/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.onlinebanking.model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 26/07/2018
 * @author jagon
 */
@Entity
@Table(name = "ACCOUNT")
@XmlRootElement
public class Account implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", columnDefinition="INTEGER(10) default '1'")
    private int id;
    private String type;
    private int number;
    private double balance;
    private ArrayList<Transaction> transactions;
    
    public Account(){
        transactions = new ArrayList<>();
    }
    
    public Account(int id, String type, int number, double balance){
        this.id = id;
        this.type = type;
        this.number = number;
        this.balance = balance;
        transactions = new ArrayList<>();
    }
    
    public Account(int id, String type, int number){
        this.id = id;
        this.type = type;
        this.number = number;
        transactions = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

}
