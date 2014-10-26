/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker.web;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author james
 */
@XmlRootElement(name = "card")
public class PINCard  implements Serializable{
    
private String token;
private String scheme;
private String displayNumber;
private String CVC;
private int expiryMonth;
private int expiryYear;
private String name;
private String addressLine1;
private String addressLine2;
private String addressCity;
private String addressPostcode;
private String addressState;
private String addressCountry;

    public String getCVC() {
        return CVC;
    }

    public void setCVC(String CVC) {
        this.CVC = CVC;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getDisplayNumber() {
        return displayNumber;
    }

    public void setDisplayNumber(String displayNumber) {
        this.displayNumber = displayNumber;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressPostcode() {
        return addressPostcode;
    }

    public void setAddressPostcode(String addressPostcode) {
        this.addressPostcode = addressPostcode;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    @Override
    public String toString() {
        return "PINCard{" + "token=" + token + ", scheme=" + scheme + ", displayNumber=" + displayNumber + ", expiryMonth=" + expiryMonth + ", expiryYear=" + expiryYear + ", name=" + name + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", addressCity=" + addressCity + ", addressPostcode=" + addressPostcode + ", addressState=" + addressState + ", addressCountry=" + addressCountry + '}';
    }

    
    
    
}
