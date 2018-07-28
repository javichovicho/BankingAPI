/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.onlinebanking.resources;

import com.google.gson.Gson;
import com.mycompany.onlinebanking.model.Transaction;
import com.mycompany.onlinebanking.service.TransactionService;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 28/07/2018
 * @author jagon
 */
@Path("/transactions")
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class TransactionResource {
    // curl -v -X GET http://localhost:49000/api/transactions/1
    @GET
    @Path("/{transactionId}")
    public Response getTransaction(@PathParam("transactionId") int id) {

       Gson gson = new Gson(); 
       TransactionService ts = new TransactionService();

       return Response.status(200).entity(gson.toJson(ts.getTransaction(id))).build();
    }
    // curl -v -X PUT http://localhost:49000/api/transactions/deltran/2    
    @PUT
    @Path("/deltran/{transactionId}")    
    public Response deleteTransaction(@PathParam("transactionId") int id) {

        //Gson gson = new Gson(); 
        
        TransactionService ts = new TransactionService();
        
        ts.deleteTransaction(id);

        //return Response.status(200).entity(gson.toJson(m1)).build();
        return Response.status(200).build();
    }
    // curl -v -X PUT http://localhost:49000/api/transactions/addNewTransaction/Transfer/transferfunds/55.25
    @PUT
    @Path("/addNewTransaction/{type}/{description}/{amount}")
    public Response addCustomer(@PathParam("type") String type,
            @PathParam("description") String description, @PathParam("amount") double amount) {
        Gson gson = new Gson(); 
        
        TransactionService ts = new TransactionService();
        
        Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setAmount(amount);
        
        ts.addTransaction(transaction.getId(), transaction.getType(), transaction.getDescription(),
                transaction.getAmount());

        return Response.status(200).entity(gson.toJson("Transaction added")).build();
    }

}
