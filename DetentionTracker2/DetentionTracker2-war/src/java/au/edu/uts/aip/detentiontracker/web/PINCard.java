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
private String number;
private String CVC;
private String expiry_month;
private String expiry_year;
private String name;
private String address_line1;
private String address_line2;
private String address_city;
private String addressPostcode;
private String address_state;
private String address_country;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getexpiry_month() {
        return expiry_month;
    }

    public void setexpiry_month(String expiry_month) {
        this.expiry_month = expiry_month;
    }

    public String getexpiry_year() {
        return expiry_year;
    }

    public void setexpiry_year(String expiry_year) {
        this.expiry_year = expiry_year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getaddress_line1() {
        return address_line1;
    }

    public void setaddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getaddress_line2() {
        return address_line2;
    }

    public void setaddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public String getaddress_city() {
        return address_city;
    }

    public void setaddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getAddressPostcode() {
        return addressPostcode;
    }

    public void setAddressPostcode(String addressPostcode) {
        this.addressPostcode = addressPostcode;
    }

    public String getaddress_state() {
        return address_state;
    }

    public void setaddress_state(String address_state) {
        this.address_state = address_state;
    }

    public String getaddress_country() {
        return address_country;
    }

    public void setaddress_country(String address_country) {
        this.address_country = address_country;
    }

    @Override
    public String toString() {
        return "PINCard{" + "token=" + token + ", scheme=" + scheme + ", displayNumber=" + number + ", expiryMonth=" + expiry_month + ", expiryYear=" + expiry_year + ", name=" + name + ", addressLine1=" + address_line1 + ", addressLine2=" + address_line2 + ", addressCity=" + address_city + ", addressPostcode=" + addressPostcode + ", addressState=" + address_state + ", addressCountry=" + address_country + '}';
    }

    
    
    
}
