/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker.web;

/**
 *
 * @author james
 */
import au.edu.uts.aip.detentiontracker.domain.*;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import java.io.*;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;



@Named
@RequestScoped
public class ChargeController implements Serializable {
    
    
    @Resource(name = "pinPayments")
    String pinPayments;
    
     @EJB
    private DetentionTrackerBean detentionTrackerBean;
     
     private Response response = new Response();
     private PINResponse request = new PINResponse();
      private Login customerLogin = new Login();
      private Receipt customerReceipt = new Receipt();
      
      
     
     public PINResponse getRequest()
    {
        
        return request;
    }
    public Response getResponse()
    {
        return response;
    }
    // from the button 
    public String chargeLogin(String username)
    {
        // in the list you click on the button it passes the user as a value.        
        customerLogin = detentionTrackerBean.getLogin(username);
        chargeUser();
        return "admin?faces-redirect=true"; 
    }
    
    public List<Login> allLogins()
    {
       return  detentionTrackerBean.allthelogins();
    }
    
    
    
    
    
    private void chargeUser()
    {
        if ("Standard".equals(customerLogin.getAccountType().toString()))
        {
            request.setAmount(2000);
        }
        if ("Premium".equals(customerLogin.getAccountType().toString()))
        {
            request.setAmount(4000);
        }
        
        request.setCurrency("AUD");
        request.setDescription("Detention tracker monthly payment");
        request.setEmail(customerLogin.getEmail());
request.setToken(customerLogin.getToken());
        request.setIpAddress("10.11.12.13");
//        PINCard custCard = new PINCard();
//        custCard.setToken(customerLogin.getToken());
//        request.setCard(custCard);
        
       
        
                
                pinPayments += "charges";
        Client client = null;
        
        try{
            System.out.println("my url is" + pinPayments);
            client = ClientBuilder.newClient();
            response = client.target(pinPayments)
                    .request()
                    .header("Authorization", "Basic MDVXVzFlMzVtRGJka1lONlhsQVhkdzpxd2VydHkxMjM=")
                    .post(Entity.json(request), Response.class );
                    
                    
            
            // does the calls to receipt and login objects 
             
          
            
        } catch (ProcessingException | WebApplicationException | NullPointerException e)
        {
            
             FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("OH SHIT SON YOU BROKE IT " + e.getLocalizedMessage().toString() + e.getMessage().toString()   ));
            
            
    } finally {
            if(client != null)
                client.close();
        }
        
    

    } 
}
