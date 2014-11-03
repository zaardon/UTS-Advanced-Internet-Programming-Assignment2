/*
 * The log in controller for the log in aspect for the web site
 */
package au.edu.uts.aip.detentiontracker.web;

import au.edu.uts.aip.detentiontracker.domain.*;
import au.edu.uts.aip.detentiontracker.domain.AccountType;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class LoginController implements Serializable {

    @EJB
    private DetentionTrackerBean detentionTrackerBean;
    private Login currentLogin = new Login();
    private Receipt currentReceipt = new Receipt();
    private final int MAX_DETENTIONS_FOR_FREE = 5;

    public Login getCurrentLogin() {
        return currentLogin;
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
     * Returns the number of non administrator accounts
     *
     * @return number of accounts
     */
    public int amountOfNonAdminAccounts() {
        return detentionTrackerBean.allLoginsThatAreNotAdmin().size();
    }

    /**
     * Checks if the current logged in account has a token and is not a FREE
     * account
     *
     * @return true or false
     */
    public boolean doesHaveTokenAndValidAccount() {
        loadLogin();
        return !(currentLogin.getToken() == null && !"Free".equals(currentLogin.getAccountType().toString()));
    }

    /**
     * Checks if the current logged in account does not exceed the maximum
     * amount of detentions a FREE account should have
     *
     * @return true or false
     */
    public boolean doesFreeAccountNotHaveMaxDetentions() {
        loadLogin();
        if (currentLogin.getAccountType() == AccountType.Free && currentLogin.getDetentions().size() < MAX_DETENTIONS_FOR_FREE) {
            return true;
        }
        return currentLogin.getAccountType() != AccountType.Free;
    }

    /**
     * Checks if the current logged in account is a Premium account with a valid
     * customer token
     *
     * @return true or false
     */
    public boolean isPremiumAccountWithToken() {
        loadLogin();
        return currentLogin.getAccountType() == AccountType.Premium && currentLogin.getToken() != null;
    }

    /**
     * Checks if the current logged in account is a Premium account
     *
     * @return true or false
     */
    public boolean isPremiumAccount() {
        loadLogin();
        return currentLogin.getAccountType() == AccountType.Premium;
    }

    /**
     * Returns the customer token of the current logged in user
     *
     * @return Customer token
     */
    public String returnCurrentToken() {
        loadLogin();
        return currentLogin.getToken();
    }

    /**
     * Returns the account type of the current logged in user
     *
     * @return Account type
     */
    public String returnCurrentAccount() {
        loadLogin();
        return currentLogin.getAccountType().toString();
    }

    /**
     * Sends the current log in object to the EJB
     *
     * @return Will either return the user to the log in page (if creation was
     * successful) OR will return user back to the register page with an error
     * message
     * @throws NoSuchAlgorithmException This error is thrown if the password
     * encryption fails
     */
    public String createUser() throws NoSuchAlgorithmException {
        if (!detentionTrackerBean.userExists(currentLogin.getUsername())) {
            // If new username does not already exists...

            detentionTrackerBean.createInitialLogin(currentLogin);
            return "login?faces-redirect=true";
        }
        // else if it does already exists...
        displayMessage("Username already exists OR you have entered incorrect characters (letters & numbers only!)");
        return null;
    }

    /**
     * Will log the user into the web site using container managed security
     *
     * @return Will take the user to the Welcome page or will return the user
     * back to the log in page with an error message
     */
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            //If the log in is successful..
            request.login(currentLogin.getUsername(), currentLogin.getPassword());
            return "welcome?faces-redirect=true";
        } catch (ServletException e) {
            //else...
            displayMessage("Username or password is incorrect!");
            return null;
        }
    }

    /**
     * Displays a message on a chosen web page
     *
     * @param message The message that is to be displayed to the user
     */
    private void displayMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(message));
    }

    /**
     * Logs the user out of the web site
     *
     * @return returns the user to the log in page
     * @throws ServletException if the servlet fails to log out
     */
    public String logout() throws ServletException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
            displayMessage("Unable to log out at this time.");
        }
        return "/login?faces-redirect=true";
    }

    /**
     * This method returns the most current expiry date for a user.
     *
     * @param receipts the list of receipts to iterate through
     * @return a string of either a date or a message
     */
    @Temporal(TemporalType.DATE)
    public String getCurrentExpiryDate(List<Receipt> receipts) {
        Date dawnOfTime = new Date(0, 0, 0);
        Date expiryDate = dawnOfTime;
        for (Receipt receipt : receipts) {
            Date currentDate = receipt.getDateOfExpiry();
            if (currentDate.compareTo(expiryDate) > 0) {
                expiryDate = receipt.getDateOfExpiry();
            }
        }
        if (expiryDate.compareTo(dawnOfTime) == 0) {
            return "No current expiry date";
        }
        return expiryDate.toString();
    }

    public DetentionTrackerBean getDetentionTrackerBean() {
        return detentionTrackerBean;
    }

    public void setDetentionTrackerBean(DetentionTrackerBean detentionTrackerBean) {
        this.detentionTrackerBean = detentionTrackerBean;
    }

    public Receipt getCurrentReceipt() {
        return currentReceipt;
    }

    public void setCurrentReceipt(Receipt currentReceipt) {
        this.currentReceipt = currentReceipt;
    }
}
