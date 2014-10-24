/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker.web;

import java.io.*;
import java.util.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author james
 */
@XmlRootElement(name ="response")
public class PINResponse implements Serializable {
    
    private String token;
    private Boolean success;
    private int amount;
    private String currency;
    private String description;
    private String email;
    private String ip_address;
    private String create_at;
    private String status_message;
    private String error_message;
    private PINCard card;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public PINCard getCard() {
        return card;
    }

    public void setCard(PINCard card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "PINResponse{" + "token=" + token + ", success=" + success + ", amount=" + amount + ", currency=" + currency + ", description=" + description + ", email=" + email + ", ip_address=" + ip_address + ", create_at=" + create_at + ", status_message=" + status_message + ", error_message=" + error_message + ", card=" + card + '}';
    }

    
    
    
    
}
