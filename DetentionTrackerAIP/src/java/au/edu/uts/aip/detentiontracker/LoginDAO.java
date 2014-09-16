/*
 *The Login table Data Access Object
 */
package au.edu.uts.aip.detentiontracker;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LoginDAO implements Serializable {  
    private static final String JNDI_NAME = "jdbc/detentiontracker";
    private static final String ALL_USERS = "select * from logins ";
    private static final String BY_USERNAME = "WHERE UserName = ?";
    private static final String INSERT_NEW_USER = "INSERT INTO logins (UserName, Password)" +
                                "Values (?, ?)";      
        
    /**
     * Creates a new user in the login table
     * @param login the DTO login object
     * @throws NoSuchAlgorithmException if the encryption algorithm fails
     */
    public static void createUser(LoginDTO login) throws NoSuchAlgorithmException {
       try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            Connection conn = ds.getConnection();
            //Inserts a new user with a prepared statement...
            PreparedStatement ps = conn.prepareStatement(INSERT_NEW_USER);
            ps.setString(1, login.getUsername());
            //Encrypts the password using has256 encoding...
            ps.setString(2, EncryptionUtility.hash256(login.getPassword()));
            ps.executeUpdate();
        } catch (NamingException | SQLException e) {
            System.out.println(e);
        }           
    }    

    /**
     * Searches the login table for a username.
     * @param username the user name that is to be searched in the table
     * @return true or false on whether the user exists or not
     */
    public static boolean userExists(String username){
        try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                //Searches all usernames in the logins table by username with a prepared statement...
                PreparedStatement ps = conn.prepareStatement(ALL_USERS + BY_USERNAME)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        //if found return true...
                        return true;
                    } 
                }
            }
        } catch (NamingException | SQLException e) {
            System.out.println(e);
        }
        //If not found return false...
        return false;
    }
}