package au.edu.uts.aip.detentiontracker.web;

import au.edu.uts.aip.detentiontracker.domain.*;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * This is the Receipt Controller for the system.
 */
@Named
@RequestScoped
public class ReceiptController {

    @EJB
    private DetentionTrackerBean detentionTrackerBean;

    /**
     * This provides all receipts that are stored within the system
     *
     * @return list of receipts
     */
    public List<Receipt> findAllReceipts() {
        return detentionTrackerBean.findAllReceipts();
    }

    /**
     * This provides the current size of the list of receipts
     *
     * @return int of receipt size
     */
    public int sizeOfReceiptList() {
        return detentionTrackerBean.findAllReceipts().size();
    }
}
