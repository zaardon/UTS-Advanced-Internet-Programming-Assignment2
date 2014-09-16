/*
 * The Detention Transfer Object
 */

package au.edu.uts.aip.detentiontracker;

import javax.validation.constraints.*;

/**
 *
 * @author Alex
 */
public class DetentionDTO {
    
    private int id;
    private String fname;
    private String lname;
    private int year;
    private String type;
    private String dept;
    private String reason;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    /**
    * Only allows letters and single white spaces/apostrophes (in the case of double names) for a first name
    * @return a first name
    */
    @Pattern(regexp="[A-Za-z]+[A-Za-z ']*")
    @Size(min = 1)
    public String getFName() {
        return fname;
    }

    public void setFName(String fname) {
        this.fname = fname;
    }

    /**
    * Only allows letters and single white spaces/apostrophes (in the case of double names) for a last name
    * @return a last name
    */
    @Pattern(regexp="[A-Za-z]+[A-Za-z ']*")
    @Size(min = 1)
    public String getLName() {
        return lname;
    }

    public void setLName(String lname) {
        this.lname = lname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    /**
    * Only allows numbers, letters, dashes, apostrophes, colons and single white spaces for a reason
    * @return a reason
    */
    @Pattern(regexp="[A-Za-z0-9]+[A-Za-z 0-9-':()]*")
    @Size(min = 1, max = 40)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
