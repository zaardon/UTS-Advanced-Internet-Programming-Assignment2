/*
 * The controller for the Detentions aspect of the web site
 */

package au.edu.uts.aip.detentiontracker.web;

import au.edu.uts.aip.detentiontracker.domain.*;
import java.io.*;
import java.util.*;
import javax.ejb.*;
import javax.enterprise.context.*;
import javax.faces.context.FacesContext;
import javax.faces.view.*;
import javax.inject.*;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class DetentionController implements Serializable {

    @EJB
    private DetentionTrackerBean detentionTrackerBean;
    
    private Detention currentDetention = new Detention();

    public Detention getCurrentDetention() {
        return currentDetention;
    }

    // THIS ONLY REQUIRES COMMENTING AND SOME NAME CHANGES 
        
     /**
     * Loads a detention by its unique ID
     * @param detentionID the unique detention id
     */
    public void loadDetention(int detentionID) {
        //change later after beans
        currentDetention = detentionTrackerBean.findDetention(detentionID);
    }
     
    /**
     * Creates a new detention record in the Detentions table
     * @return a redirect that takes the user back to the view detentions page
     */
    public String createDetention() {
       
       Login managedLogin= detentionTrackerBean.getLogin(getUser());
       
        detentionTrackerBean.addLogin(currentDetention, managedLogin);
        return "view?faces-redirect=true";
    }
    
    
    /**
     * Updates a chosen detention with new values
     * @return a redirect that takes the user back to the view detentions page
     */
    public String editDetention() {
        detentionTrackerBean.updateDetention(currentDetention);
        return "view?faces-redirect=true";
    }
    
    /**
     * Deletes a chosen record from the Detentions table
     * @param detentionID the unique detention id
     * @return a redirect that takes the user back to the view detentions page
     */
    public String removeDetention(int detentionID) {

        detentionTrackerBean.deleteDetentionFromLogin(detentionID, getUser());
        return "view?faces-redirect=true";
    }
    
    /**
     * Generates a complete list of current detentions from the Detentions table
     * @return an array list of detentions that is displayed on the web page
     */
    public List<Detention> findAllDetentions() {

            return  detentionTrackerBean.findAllDetetions(getUser());
    }
    
    /**
     * Used to determine how many detentions are currently recorded in the Detentions table
     * @return returns the amount of current detentions
     */
    public int sizeOfDetentionList(){

        // find the login in the db
        // add that to our current detention
        // then add that detention to our login ? yes
        return detentionTrackerBean.findAllDetetions(getUser()).size();
    } 
    
    public List<Object> findTotalCountOfStudentName()
    {  
        return detentionTrackerBean.findTotalCountOfStudentName(getUser());
    }
    private String getUser()
    {
         FacesContext context = FacesContext.getCurrentInstance();
        // find our contextual login
        return context.getExternalContext().getUserPrincipal().getName();
    }
}
