/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker.web;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author james
 */
@Named
@RequestScoped
public class ReceiptController {
    
    @Resource(name = "pinPayments")
    String pinPayments;
    
    
    
    private PINResponse request = new PINResponse();
    private Response response ;
    
    public PINResponse getRequest()
    {
        return request;
    }
    public Response getResponse()
    {
        return response;
    }
    
    public String pay()
    {
        pinPayments += "charges";
        Client client = null;
        
        try{
            System.out.println("my url is" + pinPayments);
            client = ClientBuilder.newClient();
            response = client.target(pinPayments)
                    .request()
                    .header("Authorization", "Basic MDVXVzFlMzVtRGJka1lONlhsQVhkdzpxd2VydHkxMjM=")
                    .get(Response.class);
            
            
            
             FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(response.toString()));
            
        } catch (ProcessingException | WebApplicationException e)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(response.toString()));
            
            return null;
    } finally {
            if(client != null)
                client.close();
        }
        
    return "PINTEST";

    }
}
