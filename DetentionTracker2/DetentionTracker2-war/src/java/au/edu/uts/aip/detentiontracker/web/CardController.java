/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker.web;

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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;



@Named
@RequestScoped
public class CardController implements Serializable {
    
    
    @Resource(name = "pinPayments")
    String pinPayments;
    
     @EJB
    private DetentionTrackerBean detentionTrackerBean;
    
    private Login currentLogin = new Login();
    private Response response = new Response();
    private PINCard reqCard = new PINCard();
    
    // for  the card 
    private String address_line1;
    private String address_line2;
    private String  address_city;
    private String  state;
    private int  postCode;
    private String  name;
    private String  number;
    private int  expiry_month;
    private int  expiry_year;
    private int  cvc;
    
    public boolean checkCard()
    {
        loadLogin();
        return currentLogin.getToken().isEmpty() != true;
    }


    public void loadLogin()
    {
          FacesContext context = FacesContext.getCurrentInstance();
        // find our contextual login
        String username = context.getExternalContext().getUserPrincipal().getName();
        currentLogin = detentionTrackerBean.getLogin(username);
    }
    
    public String updateAccount()
    {
        try{
        makeToken();
        detentionTrackerBean.updateLogin(currentLogin);
        return "view?faces-redirect=true";
        }
        catch (NullPointerException e)
        {
            System.out.println(e);
        }
        return null;
    }
    
    
    public void makeToken()
    {
       
        //lolcard.setToken("ch_aM8lCZsusic-ehncUVjFFw");
        
        reqCard.setNumber(getNumber());
        reqCard.setExpiry_Month(getExpiry_month());
        reqCard.setExpiry_Year(getExpiry_year());
        reqCard.setCVC(getCvc());
        reqCard.setName(getName());
        reqCard.setAddress_Line1(getAddress_line1());
        reqCard.setAddress_Line2(getAddress_line2());
        reqCard.setAddress_City(getAddress_city());
        reqCard.setAddressPostcode(getPostCode());
        reqCard.setAddress_State(getState());
        reqCard.setAddress_Country("Australia");
        
        
        pinPayments += "cards";
        Client client = null;
        
        try{
            System.out.println("my url is" + pinPayments);
            client = ClientBuilder.newClient();
            response = client.target(pinPayments)
                    .request()
                    .header("Authorization", "Basic MDVXVzFlMzVtRGJka1lONlhsQVhkdzpxd2VydHkxMjM=")
                    .post(Entity.json(reqCard), Response.class );
                    
                    
            
            
             FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("token is this mate : " + response.getResponse().get(0).getToken()));
            
            currentLogin.setToken(response.getResponse().get(0).getToken());
            
        } catch (ProcessingException | WebApplicationException | NullPointerException e)
        {
             
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("OH SHIT SON YOU BROKE IT " + e.getLocalizedMessage().toString() + e.getMessage().toString()   ));
            
          
    } finally {
            if(client != null)
                client.close();
        }
    }
    
    
    
    
    
    
    
    
    
    
    // gets and sets
    
    public Login getCurrentLogin() 
    {
        return currentLogin;
    }
    
    public Response getResponse()
    {
        return response;
    }
    
    public PINCard getReqCard()
    {
        return reqCard;
    }
    
    public String getAddress_line1() {
        return address_line1;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getAddress_line2() {
        return address_line2;
    }

    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getExpiry_month() {
        return expiry_month;
    }

    public void setExpiry_month(int expiry_month) {
        this.expiry_month = expiry_month;
    }

    public int getExpiry_year() {
        return expiry_year;
    }

    public void setExpiry_year(int expiry_year) {
        this.expiry_year = expiry_year;
    }

  

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }
    
}
