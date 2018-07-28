/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.onlinebanking.resources;

import com.google.gson.Gson;
import com.mycompany.onlinebanking.model.Account;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mycompany.onlinebanking.service.AccountService;
/**
 * 26/07/2018
 * @author jagon
 */
@Path("/accounts")
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class AccountResource {
    
    // curl -v -X GET http://localhost:49000/api/accounts/1
    @GET
    @Path("/{accountId}")
    public Response getAccount(@PathParam("accountId") int id) {

       Gson gson = new Gson(); 
       AccountService as = new AccountService();

       return Response.status(200).entity(gson.toJson(as.getAccount(id))).build();
    }
    // curl -v -X PUT http://localhost:49000/api/accounts/delacc/3    
    @PUT
    @Path("/delacc/{accountId}")    
    public Response deleteAccount(@PathParam("accountId") int id) {

        //Gson gson = new Gson(); 
        
        //Message m1 = gson.fromJson(body, Message.class);
        AccountService as = new AccountService();
        
        as.deleteAccount(id);

        //return Response.status(200).entity(gson.toJson(m1)).build();
        return Response.status(200).build();
    }
    
    // Method to change stuff in the database !!!!
    // curl -v -X PUT http://localhost:49000/api/accounts/editaccount/2/25.50    
    @PUT
    @Path("/editaccount/{accountId}/{newBalance}")
        
        public Response updateAccount(@PathParam("accountId") int id,
            @PathParam("newBalance") double newBalance) {

        Gson gson = new Gson(); 
        
        AccountService as = new AccountService();
        
        as.editAccount(id, newBalance);

        return Response.status(200).build();

    }
    
    // Method to add stuff to the database !!!!
    // curl -v -X PUT http://localhost:49000/api/accounts/addNewAccount/Savings
    @PUT
    @Path("/addNewAccount/{type}")
        
    public Response addMessage(@PathParam("type") String type) {

        Gson gson = new Gson(); 
        
        AccountService as = new AccountService();
        
        Account account = new Account();
        account.setType(type);
        
        int num = 0;
        String flo = "";
        for(int i = 0; i < 9; i++)
            flo += (int)(Math.random()*10);
        num = Integer.parseInt(flo);
        account.setNumber(num);
        account.setType(type);
        account.setBalance(0.0);
        
        as.addAccount(account.getId(), account.getType(), account.getNumber(), account.getBalance());

        return Response.status(200).build();

    }
    
}
