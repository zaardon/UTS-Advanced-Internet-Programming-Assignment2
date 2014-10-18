/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker.domain;

/**
 *
 * @author Alex
 */
import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
public class DetentionTrackerBean {
    
    @PersistenceContext
    private EntityManager em;
        
    public void addSampleData(){
        
    }
    
    public List<Login> findAllPeople()
    {
        
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Login> query = builder.createQuery(Login.class);
        return em.createQuery(query).getResultList();
    }
    
    public List<Detention> findAllDetetions(String username)
    {
        Login managed = em.find(Login.class, username);
        return managed.getDetentions();
        
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<Detention> query = builder.createQuery(Detention.class);
//        return em.createQuery(query).getResultList();
    }
    
    public Detention findDetention(int detentionID)
    {
        return em.find(Detention.class, detentionID);
    }
    
    public void updateDetention(Detention currentDetention)
    {
        em.merge(currentDetention);
        
    }
    
    public void deleteDetention(int detentionID)
    {
        Detention detention = em.find(Detention.class, detentionID);
        em.remove(detention);
    }
    
    public void createDetention(Detention detention)
    {
        em.persist(detention);
    }
    
    public void addLogin(Detention detention, Login login)
    {
        Detention managed = em.find(Detention.class, detention.getDetentionID());
        
        managed.setLogin(login); //singular setting
        
        // set the list of detentions 
        //also this is where we could restrict.
        login.getDetentions().add(managed);
        

 
        
        em.persist(login);
        // NO FORGETTING THE CLOSE ALSO TRANSACTIONS
        //em.close();
    }
    public void deleteLogin(Login login, Detention detention)
    {
        Login managed = em.find(Login.class, login.getUsername());
        
        managed.getDetentions().remove(detention);
        
        em.remove(detention);
    }
}
