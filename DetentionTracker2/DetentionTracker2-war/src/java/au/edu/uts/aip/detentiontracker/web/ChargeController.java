package au.edu.uts.aip.detentiontracker.web;

/**
 * This is the Charge Controller class. This handles the charging functions of the system
 */
import au.edu.uts.aip.detentiontracker.domain.PINTokenResponse;
import au.edu.uts.aip.detentiontracker.domain.Response;
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
    //Sets the resource value belonging to PIN Payments
    @Resource(name = "pinPayments")
    String pinPayments;
    
    //Sets the resource value for the Auth Key
    @Resource(name = "authKey")
    String auth;

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
        return null;
    }

    /**
     * Used by the accounts information page to display all logins that exist within the system
     * - Excluding admins
     * @return a list of logins
     */
    public List<Login> allLoginsThatAreNotAdmin() {
        return detentionTrackerBean.allLoginsThatAreNotAdmin();
    }

    /**
     * Provides a list of paying users to an XHTML page
     * @return a list of users
     */
    public List<Login> allLoginsByPayingAccountType() {
        return detentionTrackerBean.allLoginsByPayingAccountType();
    }

    /**
     * Used by an XHTML page to determine if there are subscription based users to display
     * @return a list of users
     */
    public int amountOfSubscriptionUsers() {
        return detentionTrackerBean.allLoginsByPayingAccountType().size();
    }

    /**
     * Checks whether a subscription has ended for a user. Prevents a web page from charging a 
     * user while they have a valid subscription within the system
     * @param username
     * @return true or false
     */
    public boolean hasSubscriptionEnded(String username) {
        Date currentDate = new Date();
        List<Receipt> receipts = detentionTrackerBean.findAllReceiptsForLogin(username);

        for (Receipt receipt : receipts) {
            if (receipt.getDateOfExpiry().compareTo(currentDate) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Charges a user's account, according to the amount of months to charge AND their
     * account type. It uses a PINTokenResponse to create and send a request to PIN Payments.
     * @param monthsToCharge 
     */
    private void chargeUser(int monthsToCharge) {
        //If the user has a 'standard' account...
        if ("Standard".equals(customerLogin.getAccountType().toString())) {
            request.setAmount(2000 * monthsToCharge);
        }
        //If the user has a 'premium' account...
        if ("Premium".equals(customerLogin.getAccountType().toString())) {
            request.setAmount(4000 * monthsToCharge);
        }
        request.setCurrency("AUD");
        request.setDescription("Detention tracker monthly payment for: " + monthsToCharge + " month(s)");
        request.setEmail(customerLogin.getEmail());
        request.setCustomer_token(customerLogin.getToken());
        request.setIpAddress("10.11.12.13"); //Generic IP address for the sake of processing
        pinPayments += "charges"; //Adds 'charges' to the uri address
        Client client = null;
        try {
            client = ClientBuilder.newClient();
            response = client.target(pinPayments)
                    .request()
                    .header("Authorization", auth)
                    .post(Entity.json(request), Response.class);

            //Creates a receipt to attach to the user's account
            customerReceipt.setAmount(request.getAmount());
            customerReceipt.setDateOfPurchase(new Date());
            customerReceipt.setDescription(request.getDescription());
            customerReceipt.setDateOfExpiryWithMonth(new Date(), monthsToCharge);
            detentionTrackerBean.addReceiptToLogin(customerReceipt, customerLogin);       
        } 
        catch (ProcessingException | WebApplicationException | NullPointerException e) {
            //In the event if fails with the transaction to PIN Payments, create an 'error' receipt for the user and attach it
            customerReceipt.setAmount(0);
            customerReceipt.setDateOfPurchase(new Date());
            customerReceipt.setDescription("ERROR: Charge failed");
            Date failedExpiry = new Date(0,0,0);
            customerReceipt.setDateOfExpiryWithMonth(failedExpiry, 0);
            detentionTrackerBean.addReceiptToLogin(customerReceipt, customerLogin);

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Charge could not be created by PIN Payments: " + e.getMessage()));
        } 
        finally {
            if (client != null) {
                client.close();
            }
        }
    }
}
