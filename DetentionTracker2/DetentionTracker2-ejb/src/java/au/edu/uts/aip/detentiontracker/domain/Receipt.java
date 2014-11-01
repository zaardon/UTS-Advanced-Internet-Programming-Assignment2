package au.edu.uts.aip.detentiontracker.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

/**
 * This is the JPA entity class that represents a receipt within the system
 */
@Entity
public class Receipt implements Serializable {

    private Login login;
    private int receiptID;
    private String description;
    private Date dateOfPurchase;
    private Date dateOfExpiry;
    private int amount;

    /**
     * A many to one relationship with an associated login.
     * @return 
     */
    @ManyToOne
    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    /**
     * An auto generated ID that represents the receipt.
     * @return an ID
     */
    @Id
    @GeneratedValue
    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.DATE)
    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    /**
     * Function that returns the PIN Payment's charge value from CENTS to DOLLARS.
     * Used by the receipt page
     * @return a conversion from cents to dollars
     */
    public double returnAmountInDollars() {
        return (double) amount / 100.00;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Temporal(TemporalType.DATE)
    public Date getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(Date dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    /**
     * This sets the Expiry Date of the receipt. This only generates the value when PIN Payment's provides
     * a successful response, in which the current date's month value is increase by an int value
     * @param dateOfPurchase Date of purchase provided by PIN Payments
     * @param monthsFromExpiry Int value of the months to add
     */
    public void setDateOfExpiryWithMonth(Date dateOfPurchase, int monthsFromExpiry) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateOfPurchase);
        cal.add(Calendar.MONTH, monthsFromExpiry);
        this.dateOfExpiry = cal.getTime();
    }
}
