/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker.web;

/**
 *
 * @author james
 */
public class PINCard {
    
    private String token;
    String scheme;
    String display_number;
    String expiry_month;
    String name;
    String address_line1;
    String address_line2;
    String address_city;
    String address_postcode;
    String address_state;
    String address_country;

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

    public String getDisplay_number() {
        return display_number;
    }

    public void setDisplay_number(String display_number) {
        this.display_number = display_number;
    }

    public String getExpiry_month() {
        return expiry_month;
    }

    public void setExpiry_month(String expiry_month) {
        this.expiry_month = expiry_month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress_line1() {
        return address_line1;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getAddress_line2() {
        return address_line2;
    }

    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public String getAddress_city() {
        return address_city;
    }

    public void setAddress_city(String address_city) {
        this.address_city = address_city;
    }

    public String getAddress_postcode() {
        return address_postcode;
    }

    public void setAddress_postcode(String address_postcode) {
        this.address_postcode = address_postcode;
    }

    public String getAddress_state() {
        return address_state;
    }

    public void setAddress_state(String address_state) {
        this.address_state = address_state;
    }

    public String getAddress_country() {
        return address_country;
    }

    public void setAddress_country(String address_country) {
        this.address_country = address_country;
    }

    @Override
    public String toString() {
        return "PINCard{" + "token=" + token + ", scheme=" + scheme + ", display_number=" + display_number + ", expiry_month=" + expiry_month + ", name=" + name + ", address_line1=" + address_line1 + ", address_line2=" + address_line2 + ", address_city=" + address_city + ", address_postcode=" + address_postcode + ", address_state=" + address_state + ", address_country=" + address_country + '}';
    }
    
    
}
