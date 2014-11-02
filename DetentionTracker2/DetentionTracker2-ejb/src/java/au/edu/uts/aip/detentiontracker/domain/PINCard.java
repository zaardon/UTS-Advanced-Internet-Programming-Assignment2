package au.edu.uts.aip.detentiontracker.domain;

import java.io.Serializable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the PIN Card object used by PIN Payments.This is boiler plate code to
 * match the requirements of PIN Payments
 */
@XmlRootElement(name = "card")
public class PINCard implements Serializable {

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

    /**
     * The card number can only contain numbers
     *
     * @return a card number
     */
    @Pattern(regexp = "[0-9]+[0-9]*", message = "Please enter a valid card number")
    @Size(min = 1, message = "Card number cannot be blank")
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

    /**
     * The customers name must only contain letters, spaces or apostrophes
     *
     * @return a name
     */
    @Pattern(regexp = "[A-Za-z]+[A-Za-z ']*", message = "Please enter a valid name")
    @Size(min = 1, message = "Name cannot be blank")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * The address must only contain letters, numbers, apostrophes, dashes,
     * spaces or slashes
     *
     * @return an address
     */
    @Pattern(regexp = "[A-Za-z0-9]+[A-Za-z 0-9-'/]*", message = "Please enter a valid address")
    @Size(min = 1, max = 40, message = "Address cannot be blank")
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

    /**
     * City must only contain letters or spaces
     *
     * @return a city
     */
    @Pattern(regexp = "[A-Za-z0-9]+[A-Za-z ]*", message = "Please enter a valid city")
    @Size(max = 20, message = "City cannot be blank")
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
