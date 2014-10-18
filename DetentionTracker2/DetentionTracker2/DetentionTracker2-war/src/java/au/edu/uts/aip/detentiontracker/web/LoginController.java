/*
 * The log in controller for the log in aspect for the web site
 */

package au.edu.uts.aip.detentiontracker.web;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import javax.enterprise.context.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class LoginController implements Serializable {

    private String username;
    private String password;
    private LoginDTO login = new LoginDTO();
    private Logger log = Logger.getLogger(this.getClass().getName());

    public LoginDTO getLogin(){
        return login;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     
    /**
     * Creates a new a new user in the login table
     * @return Will either return the user to the log in page (if creation was successful)
     * OR will return user back to the register page with an error message
     * @throws NoSuchAlgorithmException This error is thrown if the password encryption fails
     */
    public String createUser()  throws NoSuchAlgorithmException  {
        if(!LoginDAO.userExists(login.getUsername())){
            //If new username does not already exists...
            LoginDAO.createUser(login);
            return "login?faces-redirect=true";
        }
        //else if it does already exists...
        log.info("Unable to create user account");
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
            request.login(username, password);
            return "welcome?faces-redirect=true";         
        } catch (ServletException e) {
            //else...
            log.info(e.toString());
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
            log.info(e.toString());
            displayMessage("Unable to log out at this time.");
        }
        return "login?faces-redirect=true";
    }
    
}
