/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.onlinebanking.resources;

import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mycompany.onlinebanking.service.MessageService;
import com.mycompany.onlinebanking.model.Message;

/**
 * 24/07/2018
 * @author jagon
 */
@Path("/messages")
//@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
//@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
    
    /**
     * curl -v -X GET http://localhost:49000/api/messages/1
     * curl -v -H "Accept: application/json" -X GET http://localhost:49000/api/messages/1
     * @return 
    **/
    @GET
    @Path("/{messageId}")
    //@Produces({Media/{messageId}"Type.APPLICATION_XML,MediaType.APPLICATION_JSON})

    public Response getMessage(@PathParam("messageId") int id) {

       Gson gson = new Gson(); 
       MessageService ms = new MessageService();

       return Response.status(200).entity(gson.toJson(ms.getMessage(id))).build();
    }   
    
    /**
     * curl -v -X GET http://localhost:49000/api/messages/all
     * curl -v -H "Accept: application/json" -X GET http://localhost:49000/api/messages/all
     * @return 
    **/
    @GET
    @Path("/all")
    //@Produces({Media/{messageId}"Type.APPLICATION_XML,MediaType.APPLICATION_JSON})

    public Response getMessage() {

       Gson gson = new Gson(); 
       MessageService ms = new MessageService();

       return Response.status(200).entity(gson.toJson(ms.getAllMessages())).build();
    }  

   
        
    // curl -v -X PUT http://localhost:49000/api/messages/delmess/3    
    @PUT
    @Path("/delmess/{messageId}")
        
        public Response deleteMessage(@PathParam("messageId") int id) {

        Gson gson = new Gson(); 
        
        //Message m1 = gson.fromJson(body, Message.class);
        MessageService ms = new MessageService();
        
        ms.deleteMessage(id);

        //return Response.status(200).entity(gson.toJson(m1)).build();
        return Response.status(200).build();

    }
    
    // Method to change stuff in the database !!!!
    // curl -v -X PUT http://localhost:49000/api/messages/editmess/3/changed    
    @PUT
    @Path("/editmess/{messageId}/{newMessage}")
        
        public Response updateMessage(@PathParam("messageId") int id,
            @PathParam("newMessage") String newMessage) {

        Gson gson = new Gson(); 
        
        MessageService ms = new MessageService();
        
        ms.editMessage(id, newMessage);

        return Response.status(200).build();

    }
        
    // Method to add stuff to the database !!!!
    // curl -v -X PUT http://localhost:49000/api/messages/addmess/newmessage
    @PUT
    @Path("/addmess/{newData}")
        
    public Response addMessage(@PathParam("newData") String messageToAdd) {

        Gson gson = new Gson(); 
        
        MessageService ms = new MessageService();
        
        Message message = new Message(4, messageToAdd, "New Author");
        ms.addMessage(message.getId(), message.getMessage(), message.getAuthor());

        return Response.status(200).build();

    }
}
