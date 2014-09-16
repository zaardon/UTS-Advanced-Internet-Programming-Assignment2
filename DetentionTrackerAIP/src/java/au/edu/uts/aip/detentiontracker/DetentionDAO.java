/*
 *The Detention Data Access Object for the Detention Table.
 */
package au.edu.uts.aip.detentiontracker;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DetentionDAO implements Serializable {
    private static final String JNDI_NAME = "jdbc/detentiontracker";
    private static final String SELECT_DETENTIONS =
        "select DetentionID, FirstName, LastName, YearGroup, DetentionType, Department, Reason " +
         "from detentions ";
    private static final String BY_ID = "WHERE DetentionID = ?";
    private static final String DELETE_DETENTION = "DELETE FROM detentions ";
    private static final String UPDATE_DETENTION = "UPDATE detentions SET FirstName = ?, LastName = ?, " +
        "YearGroup = ?, DetentionType = ?, Department = ?, Reason = ?"; 
    private static final String INSERT_NEW_DETENTION = "INSERT INTO detentions "+
        "(DetentionID, FirstName, LastName, YearGroup, DetentionType, Department, Reason)" +
        " VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    //Creates a unique id based on the amount of current detentions
    private static int idGenerator = new DetentionDAO().generateDetentionID();
    
    /**
     * Generates the unique ID of a detention
     * @return returns an ID
     */
    private static synchronized int getNextUsedIDNum() {
        idGenerator++;
        return idGenerator;
    }

    /**
     * Finds the last used detention ID number
     * @return the last used detention ID number
     */
    private int generateDetentionID()
    {
        return findHighestIDNumber(findAllDetentions());      
    }
    
    /**
     * Creates a new detention
     * @param detention the detention DTO that is to be added to the Detentions table
     */
    public static void createDetention(DetentionDTO detention){
        detention.setId(getNextUsedIDNum());
        try{
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            Connection conn = ds.getConnection();
            //an insertion query that uses prepared statements...
            PreparedStatement ps = conn.prepareStatement(INSERT_NEW_DETENTION);
            ps.setInt(1, detention.getId());
            ps.setString(2, detention.getFName());
            ps.setString(3, detention.getLName());
            ps.setInt(4, detention.getYear());
            ps.setString(5, detention.getType());
            ps.setString(6, detention.getDept());
            ps.setString(7, detention.getReason());
            ps.executeUpdate();       
        }
        catch(SQLException | NamingException e){
            System.out.println(e);
        }
    }     
    
    /**
     * Reads a Detention row based on its unique ID
     * @param detentionID that is used to search inside the Detentions table
     * @return a ResultSet or NULL depending if the search was successful or not.
     */
    public DetentionDTO readDetention(int detentionID) {
        try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            //A select query that uses prepared statements...
            try (Connection conn = ds.getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_DETENTIONS + BY_ID)) {
                ps.setInt(1, detentionID);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        //Detention found...
                        return createDetentionDTO(rs);
                    } 
                }
            }
        } catch (NamingException | SQLException e) {
            System.out.println(e);
        }
        //Detention not found...
        return null;
    }
   
    /**
     * Returns all the current detention records inside the Detentions table
     * @return 
     */
    public ArrayList<DetentionDTO> findAllDetentions(){ // throws DataStoreException 
        ArrayList<DetentionDTO> results = new ArrayList<>();
        try {
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SELECT_DETENTIONS)) {
                while (rs.next()) {
                    //For each detention item found, add it to the array list...
                    results.add(createDetentionDTO(rs));
                }
            }
        } catch (NamingException | SQLException e) {
            System.out.println(e);
        }
        return results;
    }
    
    /**
     * Updates a detention record
     * @param detention the detention DTO that is updated
     */
    public static void updateDetention(DetentionDTO detention){                
        try{
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            Connection conn = ds.getConnection();
            //An update query that uses prepared statements and searches by a unique ID...
            PreparedStatement ps = conn.prepareStatement(UPDATE_DETENTION + BY_ID);
            ps.setString(1, detention.getFName());
            ps.setString(2, detention.getLName());
            ps.setInt(3, detention.getYear());
            ps.setString(4, detention.getType());
            ps.setString(5, detention.getDept());
            ps.setString(6, detention.getReason());
            ps.setInt(7, detention.getId());
            ps.executeUpdate();    
        }
        catch(SQLException | NamingException e){
            System.out.println(e);
        }
    }

    /**
     * Removes a detention record based on a unique detention ID
     * @param detentionID the unique id that the detention posses
     */
    public static void removeDetention(int detentionID){      
        try{
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            Connection conn = ds.getConnection();
            //A delete query that uses prepared statements and searches by a unique id...
            PreparedStatement ps = conn.prepareStatement(DELETE_DETENTION + BY_ID);
            ps.setInt(1, detentionID);
            ps.executeUpdate();
        }
        catch(SQLException | NamingException e){
            System.out.println(e);
        }
    }
    
    /**
     * Creates a Detention DTO based on the result set data it is given
     * @param rs the result set generated by SQL
     * @return a Detention DTO 
     * @throws SQLException if the SQL fails
     */
    private DetentionDTO createDetentionDTO(ResultSet rs) throws SQLException {
        DetentionDTO result = new DetentionDTO();
        result.setId(rs.getInt("DetentionID"));
        result.setFName(rs.getString("FirstName"));
        result.setLName(rs.getString("LastName"));
        result.setYear(rs.getInt("YearGroup"));
        result.setType(rs.getString("DetentionType"));
        result.setDept(rs.getString("Department"));
        result.setReason(rs.getString("Reason"));
        return result;
    }

    /**
     * Finds the highest unique ID number from the Detentions table.
     * @param detentions an array list of detentions that is searched
     * @return the highest ID number
     */
    private int findHighestIDNumber(ArrayList<DetentionDTO> detentions)
    {
        int idMax = 0;       
        for(DetentionDTO det : detentions)
        {
            //Goes through each detention ID and compares each object to find the highest number.
            if(idMax <= det.getId()){
                idMax = det.getId();
            }
        }
        return idMax;   
    }
}