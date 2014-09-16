/*
 * The controller for the Detentions aspect of the web site
 */

package au.edu.uts.aip.detentiontracker;

import java.io.*;
import java.util.ArrayList;
import javax.enterprise.context.*;
import javax.inject.*;

@Named
@RequestScoped
public class DetentionController implements Serializable {

    private DetentionDTO detention = new DetentionDTO();

    public DetentionDTO getDetention() {
        return detention;
    }
    
     /**
     * Loads a detention by its unique ID
     * @param detentionID the unique detention id
     */
    public void loadDetention(int detentionID) {
        detention = new DetentionDAO().readDetention(detentionID);
    }
     
    /**
     * Creates a new detention record in the Detentions table
     * @return a redirect that takes the user back to the view detentions page
     */
    public String createDetention() {
        DetentionDAO.createDetention(detention);
        return "view?faces-redirect=true";
    }
    
    /**
     * Updates a chosen detention with new values
     * @return a redirect that takes the user back to the view detentions page
     */
    public String editDetention() {
        DetentionDAO.updateDetention(detention);
        return "view?faces-redirect=true";
    }
    
    /**
     * Deletes a chosen record from the Detentions table
     * @param detentionID the unique detention id
     * @return a redirect that takes the user back to the view detentions page
     */
    public String removeDetention(int detentionID) {
        DetentionDAO.removeDetention(detentionID);
        return "view?faces-redirect=true";
    }
    
    /**
     * Generates a complete list of current detentions from the Detentions table
     * @return an array list of detentions that is displayed on the web page
     */
    public ArrayList<DetentionDTO> getAllDetentions() {
            return new DetentionDAO().findAllDetentions();
    }
    
    /**
     * Used to determine how many detentions are currently recorded in the Detentions table
     * @return returns the amount of current detentions
     */
    public int sizeOfDetentionList(){
        return getAllDetentions().size();
    } 
}
