/*
 * The log in controller for the log in aspect for the web site
 */

package au.edu.uts.aip.detentiontracker.web;
import au.edu.uts.aip.detentiontracker.domain.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.enterprise.context.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class LoginController implements Serializable {

    @EJB
    private DetentionTrackerBean detentionTrackerBean;
    
    private Login currentLogin = new Login();
    private Receipt currentReceipt = new Receipt();
    
    // make a card object  
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
    public Login getCurrentLogin() {
        return currentLogin;
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
    
    
    /**
     * Creates a new a new user in the login table
     * @return Will either return the user to the log in page (if creation was successful)
     * OR will return user back to the register page with an error message
     * @throws NoSuchAlgorithmException This error is thrown if the password encryption fails
     */
    
    //first creation.
    public String createUser()  throws NoSuchAlgorithmException  {
        if(!detentionTrackerBean.userExists(currentLogin.getUsername())){
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
     * @return Will take the user to the Welcome page or will return the user back to
     * the log in page with an error message
     */
    public String login(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();       
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
     * @param message The message that is to be displayed to the user
     */
    private void displayMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(message));
    }
    
    /**
     * Logs the user out of the web site
     * @return returns the user to the log in page
     * @throws ServletException if the servlet fails to log out
     */
    public String logout() throws ServletException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        try{
            request.logout();
        }
        catch (ServletException e) {
            displayMessage("Unable to log out at this time.");
        }
        return "/login?faces-redirect=true"; 
    }

    public void makeToken()
    {
        ReceiptController rc = new ReceiptController();
//       String token = rc.makeCard();
String token = rc.makeCard(getNumber(), getExpiry_month(), getExpiry_year(), getCvc(), getName(), getAddress_line1(), getAddress_line2(), getAddress_city(), getPostCode(), getState());
       if(token == null)
       {
           System.out.print("mate it's fucked");
       }
       currentLogin.setToken(token);
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
