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
    private int amount;
    private String token;
    
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getToken() {
        return token;
    }

    // this is where the variables for the receipt go when we finally know all of them.
    // getters and setters
    public void setToken(String token) {
        this.token = token;
    }
    

    
    
    
    
}
