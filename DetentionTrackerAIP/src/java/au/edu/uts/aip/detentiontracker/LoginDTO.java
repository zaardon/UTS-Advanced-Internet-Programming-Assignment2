/*
 * The Login Data Transfer Object
 */

package au.edu.uts.aip.detentiontracker;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Alex
 */
public class LoginDTO {
    
    private String username;
    private String password;
    
    /**
    * Only allows letters and numbers for a username
    * @return a username
    */
    @Pattern(regexp="[a-zA-Z_0-9]*")
    @Size(min = 1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
    * Only allows letters and numbers for a password
    * @return a password
    */
    @Pattern(regexp="[a-zA-Z_0-9]*")
    @Size(min = 1)
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    
}
