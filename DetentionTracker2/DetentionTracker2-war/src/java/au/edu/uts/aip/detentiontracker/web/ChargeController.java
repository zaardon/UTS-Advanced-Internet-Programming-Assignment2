package au.edu.uts.aip.detentiontracker.web;

/**
 * This is the Charge Controller class. This handles the charging functions of the system
 */
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
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
public class ChargeController implements Serializable {

    @Resource(name = "pinPayments")
    String pinPayments;

    @EJB
    private DetentionTrackerBean detentionTrackerBean;
    private Response response = new Response();
    private PINTokenResponse request = new PINTokenResponse();
    private Login customerLogin = new Login();
    private Receipt customerReceipt = new Receipt();

    public PINTokenResponse getRequest() {
        return request;
    }

    public Response getResponse() {
        return response;
    }

    /**
     * This initiates a charge towards a user's account
     * @param username
     * @param monthsToCharge
     * @return Page redirect
     */
    public String chargeLogin(String username, int monthsToCharge) {
        // in the list you click on the button it passes the user as a value.        
        customerLogin = detentionTrackerBean.getLogin(username);
        chargeUser(monthsToCharge);
        return "admin?faces-redirect=true";
    }

    /**
     * 
     */
    public List<Login> allLogins() {
        return detentionTrackerBean.allLogins();
    }

    public List<Login> allLoginsByPayingAccountType() {
        return detentionTrackerBean.allLoginsByPayingAccountType();
    }

    public int amountOfSubscriptionUsers() {
        return detentionTrackerBean.allLoginsByPayingAccountType().size();
    }

    public boolean hasSubscriptionEnded(String username) {
        Date currentDate = new Date();
        List<Receipt> receipts = detentionTrackerBean.findAllReceiptsForLogin(username);

        for (int i = 0; i < receipts.size(); i++) {
            if (receipts.get(i).getDateOfExpiry().compareTo(currentDate) > 0) {
                return false;
            }
        }
        return true;

    }

    private void chargeUser(int monthsToCharge) {

        //HAS TO USE A SPECIAL TOKEN CARD RESPONSE
        if ("Standard".equals(customerLogin.getAccountType().toString())) {
            request.setAmount(2000 * monthsToCharge);
        }
        if ("Premium".equals(customerLogin.getAccountType().toString())) {
            request.setAmount(4000 * monthsToCharge);
        }

        request.setCurrency("AUD");
        request.setDescription("Detention tracker monthly payment for: " + monthsToCharge + " month(s)");
        request.setEmail(customerLogin.getEmail());
        request.setCustomer_token(customerLogin.getToken());
        request.setIpAddress("10.11.12.13");
//        PINCard custCard = new PINCard();
//        custCard.setToken(customerLogin.getToken());
//        request.setCard(custCard);

        pinPayments += "charges";
        Client client = null;

        try {
            System.out.println("my url is" + pinPayments);
            client = ClientBuilder.newClient();
            response = client.target(pinPayments)
                    .request()
                    .header("Authorization", "Basic MDVXVzFlMzVtRGJka1lONlhsQVhkdzpxd2VydHkxMjM=")
                    .post(Entity.json(request), Response.class);

            // does the calls to receipt and login objects 
            customerReceipt.setAmount(request.getAmount());
            customerReceipt.setDateOfPurchase(new Date());
            customerReceipt.setDescription(request.getDescription());
            customerReceipt.setDateOfExpiryWithMonth(new Date(), monthsToCharge);

            detentionTrackerBean.addLoginToReceipt(customerReceipt, customerLogin);

        } catch (ProcessingException | WebApplicationException | NullPointerException e) {
            customerReceipt.setAmount(0);
            customerReceipt.setDateOfPurchase(new Date());
            customerReceipt.setDescription("ERROR: Charge failed");
            Date failedExpiry = null;
            customerReceipt.setDateOfExpiryWithMonth(failedExpiry, 0);
            detentionTrackerBean.addLoginToReceipt(customerReceipt, customerLogin);

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("OH SHIT SON YOU BROKE IT " + e.getLocalizedMessage().toString() + e.getMessage().toString()));

        } finally {
            if (client != null) {
                client.close();
            }
        }

    }
}
