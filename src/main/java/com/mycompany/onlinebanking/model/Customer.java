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
 * 28/07/2018
 * @author jagon
 */
@Entity
@Table(name = "CUSTOMER")
@XmlRootElement
public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", columnDefinition="INTEGER(10) default '1'")
    private int id;
    private String name;
    private int pin;
    private ArrayList<Account> accounts;

    public Customer() {
        accounts = new ArrayList<>();
    }

    public Customer(int id, String name, int pin) {
        this.id = id;
        this.name = name;
        this.pin = pin;
        accounts = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPin() {
        return pin;
    }
    
    public void addAccount(Account account){
        accounts.add(account);
    }

}
