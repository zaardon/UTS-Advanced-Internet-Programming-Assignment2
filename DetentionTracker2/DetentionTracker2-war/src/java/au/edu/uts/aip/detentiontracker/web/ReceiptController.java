/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker.web;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.ENTITYDatatypeValidator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.core.GenericType;
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
    private Response response = new Response();
    
    
    public PINResponse getRequest()
    {
        
        return request;
    }
    public Response getResponse()
    {
        return response;
    }
    
    public String PAYSOMEBITCHES()
    {
        request.setAmount(400);
        request.setCurrency("USD");
        request.setDescription("what eves brah");
        request.setEmail("WHAT YOU SAY@faggot.com");
        request.setIpAddress("203.192.1.172");
        PINCard lolcard = new PINCard();
        //lolcard.setToken("ch_aM8lCZsusic-ehncUVjFFw");
        lolcard.setScheme("master");
        lolcard.setNumber("5520000000000000");
        lolcard.setExpiry_Month(5);
        lolcard.setExpiry_Year(2015);
        lolcard.setCVC(123);
        lolcard.setName("Roland Robot");
        lolcard.setAddress_Line1("42 Sevenoaks St");
        lolcard.setAddress_Line2("");
        lolcard.setAddress_City("Lathlain");
        lolcard.setAddressPostcode(6454);
        lolcard.setAddress_State("WA");
        lolcard.setAddress_Country("Australia");
        request.setCard(lolcard);
        
                
                pinPayments += "charges";
        Client client = null;
        
        try{
            System.out.println("my url is" + pinPayments);
            client = ClientBuilder.newClient();
            response = client.target(pinPayments)
                    .request()
                    .header("Authorization", "Basic MDVXVzFlMzVtRGJka1lONlhsQVhkdzpxd2VydHkxMjM=")
                    .post(Entity.json(request), Response.class );
                    
                    
            
            
             FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(response.toString()));
            
            
        } catch (ProcessingException | WebApplicationException | NullPointerException e)
        {
               Logger log = Logger.getLogger(this.getClass().getName());
            log.log(Level.SEVERE, "Could not communicate with swap web service", e);
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("OH SHIT SON YOU BROKE IT " + e.getLocalizedMessage().toString() + e.getMessage().toString()   ));
            
            return null;
    } finally {
            if(client != null)
                client.close();
        }
        
    return "PINTEST";

    } 
        
}
//    public String pay()
//    {
//        pinPayments += "charges";
//        Client client = null;
//        
//        try{
//            System.out.println("my url is" + pinPayments);
//            client = ClientBuilder.newClient();
//            response = client.target(pinPayments)
//                    .request()
//                    .header("Authorization", "Basic MDVXVzFlMzVtRGJka1lONlhsQVhkdzpxd2VydHkxMjM=")
//                    .get(Response.class);
//            
//            
//             FacesContext context = FacesContext.getCurrentInstance();
//            context.addMessage(null, new FacesMessage(response.toString()));
//            
//            
//        } catch (ProcessingException | WebApplicationException | NullPointerException e)
//        {
//            FacesContext context = FacesContext.getCurrentInstance();
//            context.addMessage(null, new FacesMessage("OH SHIT SON YOU BROKE IT " + e.toString()));
//            
//            return null;
//    } finally {
//            if(client != null)
//                client.close();
//        }
//        
//    return "PINTEST";
//
//    }
//}
