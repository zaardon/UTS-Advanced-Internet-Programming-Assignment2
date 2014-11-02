package au.edu.uts.aip.detentiontracker.web;

import au.edu.uts.aip.detentiontracker.domain.*;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import java.io.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * This is the controller that controls all card processing functionalities
 * within the system
 */
@Named
@RequestScoped
public class CardController implements Serializable {

    @Resource(name = "pinPayments") //Sets the resource value belonging to PIN Payments
    String pinPayments;

    @EJB
    private DetentionTrackerBean detentionTrackerBean;
    private Login currentLogin = new Login();
    private Response response = new Response();
    private PINCard reqCard = new PINCard();
    private CustomerToken customerToken = new CustomerToken();
    private PINCard currentCard = new PINCard();

    public boolean checkCard() {
        loadLogin();
        return currentLogin.getToken().isEmpty() != true;
    }

    /**
     * This method uses a FacesContext to find the current person who is logged
     * in. It then loads the current login object from the EJB
     */
    public void loadLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        String username = context.getExternalContext().getUserPrincipal().getName();
        currentLogin = detentionTrackerBean.getLogin(username);
    }

    /**
     * This updates the current logged in user with a customer token in the event they attempt to
     * submit their credit card information
     * @return null 
     */
    public String updateAccountWithCustomerToken() {
        makeCustomerToken();
        detentionTrackerBean.updateLogin(currentLogin);
        return null;
    }

    /**
     * This method creates the customer token with the provided credit card information from the XHTML page
     */
    public void makeCustomerToken() {
        //The initial card and customer token set-up
        currentCard.setAddress_Country("Australia");
        customerToken.setEmail(currentLogin.getEmail());
        customerToken.setCard(currentCard);
        pinPayments += "customers";
        Client client = null;
        try {
            //Attempts to create a customer token and adds it to the user if it succeeds
            client = ClientBuilder.newClient();
            response = client.target(pinPayments)
                    .request()
                    .header("Authorization", "Basic MDVXVzFlMzVtRGJka1lONlhsQVhkdzpxd2VydHkxMjM=")
                    .post(Entity.json(customerToken), Response.class);
            FacesContext context = FacesContext.getCurrentInstance();
            //A success message is shown on the page
            context.addMessage(null, new FacesMessage("Congratulations! Your Credit Card information has been stored!"));
            currentLogin.setToken(response.getResponse().get(0).getToken());
            
        } catch (ProcessingException | WebApplicationException | NullPointerException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            //If it fails, an error message is displayed on the page
            context.addMessage(null, new FacesMessage("Credit Card information was not acceptable. Please Retry!"));
            
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    public Login getCurrentLogin() {
        return currentLogin;
    }

    public Response getResponse() {
        return response;
    }

    public PINCard getReqCard() {
        return reqCard;
    }

    public PINCard getCurrentCard() {
        return currentCard;
    }
}
