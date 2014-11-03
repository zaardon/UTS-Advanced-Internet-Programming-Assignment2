package au.edu.uts.aip.detentiontracker.domain;

import java.io.*;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.*;

/**
 * This is the JPA entity class that represents a user within the system A Login
 * object has both a list of detentions and receipts associated with it
 */
@Entity
public class Login implements Serializable {

    private String username;
    private String password;
    private AccountType accountType;
    private String email;
    private String token;
    private List<Detention> detentions = new ArrayList<>(); //These are the relationship mappings for the object's detentions
    private List<Receipt> receipts = new ArrayList<>(); //These are the relationship mappings for the object's receipts

    /**
     * Only allows letters and numbers for a username
     *
     * @return a username
     */
    @Id
    @NotNull
    @Pattern(regexp = "[a-zA-Z_0-9]*", message = "Username uses incorrect characters")
    @Size(min = 1, message = "This field cannot be blank")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Only allows letters and numbers for a password
     *
     * @return a password
     */
    @NotNull
    @Pattern(regexp = "[a-zA-Z_0-9]*", message = "Password uses incorrect character")
    @Size(min = 1, message = "This field cannot be blank")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * An enum value that is used for the account type
     *
     * @return an account type
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "ACCOUNTTYPE")
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    /**
     * The email of the user. Must only contain a letter/numbers, and must
     * consist of an '@' and '.com' part.
     *
     * @return an email
     */
    @NotNull
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    @Size(min = 1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * A one to many relationship with the object's various detentions
     *
     * @return list of detentions
     */
    @OneToMany(mappedBy = "login", cascade = CascadeType.ALL)
    public List<Detention> getDetentions() {
        return detentions;
    }

    public void setDetentions(List<Detention> detentions) {
        this.detentions = detentions;
    }

    /**
     * A one to many relationship with the object's various receipts
     *
     * @return list of receipts
     */
    @OneToMany(mappedBy = "login", cascade = CascadeType.ALL)
    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    /**
     * Token of the user. Only generated when PIN Payments provides a valid
     * token when credit card details are provided
     *
     * @return a customer token
     */
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
