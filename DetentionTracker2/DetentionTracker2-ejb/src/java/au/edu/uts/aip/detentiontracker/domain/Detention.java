package au.edu.uts.aip.detentiontracker.domain;

import au.edu.uts.aip.detentiontracker.domain.enums.DetentionType;
import au.edu.uts.aip.detentiontracker.domain.enums.DepartmentType;
import au.edu.uts.aip.detentiontracker.domain.enums.YearType;
import java.io.*;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * This is the JPA entity class that represents a detention within the system
 */
@Entity
public class Detention implements Serializable {

    private Login login;
    private int detentionID;
    private String firstName;
    private String lastName;
    private YearType yearType;
    private DetentionType detentionType;
    private DepartmentType departmentType;
    private String reason;

    @Id
    @GeneratedValue
    public int getDetentionID() {
        return detentionID;
    }

    public void setDetentionID(int detentionID) {
        this.detentionID = detentionID;
    }

    /**
     * Only allows letters and single white spaces/apostrophes (in the case of
     * double names) for a first name
     *
     * @return a first name
     */
    @Pattern(regexp = "[A-Za-z]+[A-Za-z ']*")
    @Size(min = 1)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Only allows letters and single white spaces/apostrophes (in the case of
     * double names) for a last name
     *
     * @return a last name
     */
    @Pattern(regexp = "[A-Za-z]+[A-Za-z ']*")
    @Size(min = 1)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Enumerated(EnumType.STRING)
    public YearType getYearType() {
        return yearType;
    }

    public void setYearType(YearType yearType) {
        this.yearType = yearType;
    }

    @Enumerated(EnumType.STRING)
    public DetentionType getDetentionType() {
        return detentionType;
    }

    public void setDetentionType(DetentionType detentionType) {
        this.detentionType = detentionType;
    }

    @Enumerated(EnumType.STRING)
    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(DepartmentType departmentType) {
        this.departmentType = departmentType;
    }

    /**
     * Only allows numbers, letters, dashes, apostrophes, colons and single
     * white spaces for a reason
     *
     * @return a reason
     */
    @Pattern(regexp = "[A-Za-z0-9]+[A-Za-z 0-9-':()]*")
    @Size(min = 1, max = 40)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * A many to one relationship that allows this detention to belong to a
     * Login object
     *
     * @return a login
     */
    @ManyToOne
    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
