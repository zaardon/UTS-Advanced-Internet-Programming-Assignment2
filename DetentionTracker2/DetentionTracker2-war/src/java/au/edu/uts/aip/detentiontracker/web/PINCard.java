
package au.edu.uts.aip.detentiontracker.web;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the PIN Card object used by PIN Payments. It is used to create a Customer Token so 
 * that a user may be charged for the service.
 */
@XmlRootElement(name = "card")
public class PINCard  implements Serializable{
private String token;
private String scheme;
private String number;
private int CVC;
private int expiry_month;
private int expiry_year;
private String name;
private String address_line1;
private String address_line2;
private String address_city;
private int addressPostcode;
private String address_state;
private String address_country;

    public int getCVC() {
        return CVC;
    }

    public void setCVC(int CVC) {
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

    public int getExpiry_Month() {
        return expiry_month;
    }

    public void setExpiry_Month(int expiry_month) {
        this.expiry_month = expiry_month;
    }

    public int getExpiry_Year() {
        return expiry_year;
    }

    public void setExpiry_Year(int expiry_year) {
        this.expiry_year = expiry_year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress_Line1() {
        return address_line1;
    }

    public void setAddress_Line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getAddress_Line2() {
        return address_line2;
    }

    public void setAddress_Line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public String getAddress_City() {
        return address_city;
    }

    public void setAddress_City(String address_city) {
        this.address_city = address_city;
    }

    public int getAddressPostcode() {
        return addressPostcode;
    }

    public void setAddressPostcode(int addressPostcode) {
        this.addressPostcode = addressPostcode;
    }

    public String getAddress_State() {
        return address_state;
    }

    public void setAddress_State(String address_state) {
        this.address_state = address_state;
    }

    public String getAddress_Country() {
        return address_country;
    }

    public void setAddress_Country(String address_country) {
        this.address_country = address_country;
    }

    @Override
    public String toString() {
        return "PINCard{" + "token=" + token + ", scheme=" + scheme + ", displayNumber=" + number + ", expiryMonth=" + expiry_month + ", expiryYear=" + expiry_year + ", name=" + name + ", addressLine1=" + address_line1 + ", addressLine2=" + address_line2 + ", addressCity=" + address_city + ", addressPostcode=" + addressPostcode + ", addressState=" + address_state + ", addressCountry=" + address_country + '}';
    }

    
    
    
}
