/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.onlinebanking.resources;

import com.google.gson.Gson;
import com.mycompany.onlinebanking.model.Customer;
import com.mycompany.onlinebanking.service.CustomerService;
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
@Path("/customers")
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class CustomerResource {
    // curl -v -X GET http://localhost:49000/api/customers/1
    @GET
    @Path("/{customerId}")
    public Response getCustomer(@PathParam("customerId") int id) {

       Gson gson = new Gson(); 
       CustomerService cs = new CustomerService();

       return Response.status(200).entity(gson.toJson(cs.getCustomer(id))).build();
    }
    // curl -v -X PUT http://localhost:49000/api/customers/delcus/2    
    @PUT
    @Path("/delcus/{customerId}")    
    public Response deleteCustomer(@PathParam("customerId") int id) {

        //Gson gson = new Gson(); 
        
        CustomerService cs = new CustomerService();
        
        cs.deleteCustomer(id);

        //return Response.status(200).entity(gson.toJson(m1)).build();
        return Response.status(200).build();
    }
    // curl -v -X PUT http://localhost:49000/api/customers/editcust/2/4321    
    @PUT
    @Path("/editcust/{customerId}/{newPin}")
        
    public Response updateCustomer(@PathParam("customerId") int id,
        @PathParam("newPin") int pin) {

        Gson gson = new Gson(); 
        
        CustomerService cs = new CustomerService();
        
        cs.editCustomer(id, pin);

        return Response.status(200).build();
    }
    // curl -v -X PUT http://localhost:49000/api/customers/addNewCustomer/Javier/1234
    @PUT
    @Path("/addNewCustomer/{name}/{pin}")
    public Response addCustomer(@PathParam("name") String name,
            @PathParam("pin") int pin) {

        Gson gson = new Gson(); 
        
        CustomerService cs = new CustomerService();
        
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPin(pin);
        
        cs.addCustomer(customer.getId(), customer.getName(), customer.getPin());

        return Response.status(200).entity(gson.toJson("Customer created")).build();

    }

}
