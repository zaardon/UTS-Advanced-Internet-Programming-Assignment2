/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;
import javax.validation.constraints.*;
/**
 *
 * @author james
 */
@Entity
public class Receipt implements Serializable{
    
    
    private Login login;
    private int receiptID;
    private String description;
    private Date dateOfPurchase;
    private Date dateOfExpiry;
    private int amount;

    
    
    @ManyToOne
    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

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

    public double returnAmountInDollars()
    {
        return (double)amount/100.00;
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

       public void setDateOfExpiryWithMonth(Date dateOfExpiry, int monthsFromExpiry) {
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateOfExpiry);
        cal.add(Calendar.MONTH, monthsFromExpiry);

        this.dateOfExpiry = cal.getTime();
    }

    
    
    
}
