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
    
}
