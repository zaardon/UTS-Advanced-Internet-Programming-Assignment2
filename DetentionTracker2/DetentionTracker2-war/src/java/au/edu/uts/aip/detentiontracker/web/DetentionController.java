package au.edu.uts.aip.detentiontracker.web;

/*
 * The controller for the Detentions aspect of the web site
 */
import au.edu.uts.aip.detentiontracker.domain.*;
import java.io.*;
import java.util.*;
import javax.ejb.*;
import javax.enterprise.context.*;
import javax.faces.context.FacesContext;
import javax.inject.*;

@Named
@RequestScoped
public class DetentionController implements Serializable {

    @EJB
    private DetentionTrackerBean detentionTrackerBean;
    private Detention currentDetention = new Detention();

    public Detention getCurrentDetention() {
        return currentDetention;
    }

    /**
     * Loads a detention by its unique ID
     *
     * @param detentionID the unique detention id
     */
    public void loadDetention(int detentionID) {
        currentDetention = detentionTrackerBean.findDetentionOnID(detentionID);
    }

    /**
     * Creates a new detention record in the Detentions table
     *
     * @return a redirect that takes the user back to the view detentions page
     */
    public String createDetention() {
        Login managedLogin = detentionTrackerBean.getLogin(getUser());
        detentionTrackerBean.addDetentionToLogin(currentDetention, managedLogin);
        return "view?faces-redirect=true";
    }

    /**
     * Updates a chosen detention with new values
     *
     * @return a redirect that takes the user back to the view detentions page
     */
    public String editDetention() {
        detentionTrackerBean.updateDetention(currentDetention);
        return "view?faces-redirect=true";
    }

    /**
     * Deletes a chosen record from the Detentions table
     *
     * @param detentionID the unique detention id
     * @return a redirect that takes the user back to the view detentions page
     */
    public String removeDetention(int detentionID) {

        detentionTrackerBean.deleteDetentionFromLogin(detentionID, getUser());
        return "view?faces-redirect=true";
    }

    /**
     * Generates a complete list of current detentions from the Detentions table
     *
     * @return an array list of detentions that is displayed on the web page
     */
    public List<Detention> findAllDetentions() {
        return detentionTrackerBean.findAllDetetionsOnUsername(getUser());
    }

    /**
     * Used to determine how many detentions are currently recorded in the
     * Detentions table
     *
     * @return returns the amount of current detentions
     */
    public int sizeOfDetentionList() {
        return detentionTrackerBean.findAllDetetionsOnUsername(getUser()).size();
    }

    /**
     * Used to provide a list of generic objects that represent students and the
     * amount of detentions they currently have within the system.
     *
     * @return a list of students and their detention COUNT
     */
    public List<Object> findTotalCountOfStudentName() {
        return detentionTrackerBean.findCountOfStudentsExistingDetentionsOnUsername(getUser());
    }

    /**
     * Returns the FacesContext of the current username
     *
     * @return A FacesContext
     */
    private String getUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getUserPrincipal().getName();
    }
}
