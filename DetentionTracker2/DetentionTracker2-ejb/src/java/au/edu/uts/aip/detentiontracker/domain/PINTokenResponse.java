package au.edu.uts.aip.detentiontracker.domain;

import java.io.*;
import java.util.*;
import javax.xml.bind.annotation.*;

/**
 * This is the PIN Token Response JSON object used by the PIN Payments
 * processing system. This is boiler plate code, with the exact specifications
 * set by the PIN Payments API
 */
@XmlRootElement(name = "response")
public class PINTokenResponse implements Serializable {

    private boolean success;
    private int amount;
    private String currency;
    private String description;
    private String email;
    private String ipAddress;
    private String createdAt;
    private String statusMessage;
    private String errorMessage;
    private String customer_token;
    private List<String> transfer = new ArrayList<>();
    private int amountRefunded;
    private int totalFees;
    private int merchantEntitlement;
    private boolean refundPending;
    private boolean authorisationExpired;
    private boolean captured;
    private String settlementCurrency;

    public String getCustomer_token() {
        return customer_token;
    }

    public void setCustomer_token(String customer_token) {
        this.customer_token = customer_token;
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
        return "PINTokenResponse{" + ", success=" + success + ", amount=" + amount + ", currency=" + currency + ", description=" + description + ", email=" + email + ", ipAddress=" + ipAddress + ", createdAt=" + createdAt + ", statusMessage=" + statusMessage + ", errorMessage=" + errorMessage + ", cardToken=" + customer_token + ", transfer=" + transfer + ", amountRefunded=" + amountRefunded + ", totalFees=" + totalFees + ", merchantEntitlement=" + merchantEntitlement + ", refundPending=" + refundPending + ", authorisationExpired=" + authorisationExpired + ", captured=" + captured + ", settlementCurrency=" + settlementCurrency + '}';
    }
}
