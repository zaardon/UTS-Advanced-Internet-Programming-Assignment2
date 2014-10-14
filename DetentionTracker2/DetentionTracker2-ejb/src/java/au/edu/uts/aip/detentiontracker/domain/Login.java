/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker.domain;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 *
 * @author james
 */
@Entity
public class Login implements Serializable{
    
    private String username;
    private String password;
    private AccountType accountType;
    
    private List<Detention> detentions = new ArrayList<>();


    private List<Receipt> receipts = new ArrayList<>();
    
    // a list of detentions
    // a list of receipts 
    
    @Id
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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    
    @OneToMany(mappedBy = "login", cascade = CascadeType.ALL)
    public List<Detention> getDetentions() {
        return detentions;
    }

    public void setDetentions(List<Detention> detentions) {
       this.detentions = detentions;
    }
    
   @OneToMany(mappedBy = "login", cascade = CascadeType.ALL)
    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }
    
}
