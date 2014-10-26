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
private boolean success;
private int amount;
private String currency;
private String description;
private String email;
private String ipAddress;
private String createdAt;
private String statusMessage;
private String errorMessage;
private PINCard card;
private List<String> transfer = new ArrayList<String>();
private int amountRefunded;
private int totalFees;
private int merchantEntitlement;
private boolean refundPending;
private boolean authorisationExpired;
private boolean captured;
private String settlementCurrency;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public PINCard getCard() {
        return card;
    }

    public void setCard(PINCard card) {
        this.card = card;
    }

    public List<String> getTransfer() {
        return transfer;
    }

    public void setTransfer(List<String> transfer) {
        this.transfer = transfer;
    }

    public int getAmountRefunded() {
        return amountRefunded;
    }

    public void setAmountRefunded(int amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    public int getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(int totalFees) {
        this.totalFees = totalFees;
    }

    public int getMerchantEntitlement() {
        return merchantEntitlement;
    }

    public void setMerchantEntitlement(int merchantEntitlement) {
        this.merchantEntitlement = merchantEntitlement;
    }

    public boolean isRefundPending() {
        return refundPending;
    }

    public void setRefundPending(boolean refundPending) {
        this.refundPending = refundPending;
    }

    public boolean isAuthorisationExpired() {
        return authorisationExpired;
    }

    public void setAuthorisationExpired(boolean authorisationExpired) {
        this.authorisationExpired = authorisationExpired;
    }

    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public String getSettlementCurrency() {
        return settlementCurrency;
    }

    public void setSettlementCurrency(String settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
    }
 


    @Override
    public String toString() {
        return "PINResponse{" + "token=" + token + ", success=" + success + ", amount=" + amount + ", currency=" + currency + ", description=" + description + ", email=" + email + ", ipAddress=" + ipAddress + ", createdAt=" + createdAt + ", statusMessage=" + statusMessage + ", errorMessage=" + errorMessage + ", card=" + card + ", transfer=" + transfer + ", amountRefunded=" + amountRefunded + ", totalFees=" + totalFees + ", merchantEntitlement=" + merchantEntitlement + ", refundPending=" + refundPending + ", authorisationExpired=" + authorisationExpired + ", captured=" + captured + ", settlementCurrency=" + settlementCurrency + '}';
    }



    
    
    
    
}
