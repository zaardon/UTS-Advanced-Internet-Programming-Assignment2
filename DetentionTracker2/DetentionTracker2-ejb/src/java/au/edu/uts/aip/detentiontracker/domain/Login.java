/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker.domain;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    private String email;
    private String token;
    private List<Detention> detentions = new ArrayList<>();


    private List<Receipt> receipts = new ArrayList<>();
    
  
    
    @Id
    @NotNull
    @Pattern(regexp="[a-zA-Z_0-9]*")
    @Size(min = 1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @NotNull
    @Pattern(regexp="[a-zA-Z_0-9]*")
    @Size(min = 1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Enumerated(EnumType.STRING)
    @Column(name="ACCOUNTTYPE")
    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    
    @NotNull
    @Size(min = 1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        public String getToken() {
        return token;
    }

    // this is where the variables for the receipt go when we finally know all of them.
    // getters and setters
    public void setToken(String token) {
        this.token = token;
    }
    
}
