package au.edu.uts.aip.detentiontracker.domain;

/**
 * This is the Detention Tracker Bean that is used throughout the project.
 */
import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Stateless
public class DetentionTrackerBean {

    @PersistenceContext
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Detention> findAllDetetionsOnUsername(String username) {
        Login managed = em.find(Login.class, username);
        return managed.getDetentions();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Detention findDetentionOnID(int detentionID) {
        return em.find(Detention.class, detentionID);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateDetention(Detention currentDetention) {
        em.merge(currentDetention);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteDetention(int detentionID) {
        Detention detention = em.find(Detention.class, detentionID);
        em.remove(detention);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createDetention(Detention detention) {
        em.persist(detention);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addLogin(Detention detention, Login login) {
        try {
            detention.setLogin(login);
            createDetention(detention);

        // next we gonna add it to our login.
        // set the list of detentions 
            //also this is where we could restrict.
            login.getDetentions().add(detention);

        // NO FORGETTING THE CLOSE ALSO TRANSACTIONS
            //em.close();
            em.merge(login);

        } catch (EJBException e) {
            System.out.println(e);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteDetentionFromLogin(int detentionID, String username) {
        Detention detManaged = em.find(Detention.class, detentionID);
        Login managed = em.find(Login.class, username);
        managed.getDetentions().remove(detManaged);
        em.remove(detManaged);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Login> allLoginsByPayingAccountType() {
        TypedQuery<Login> query;
        query = em.createQuery("SELECT l FROM Login l WHERE l.accountType <> :free AND l.accountType <> :administrator AND l.token is not NULL ", Login.class);
        query.setParameter("free", AccountType.Free);
        query.setParameter("administrator", AccountType.Administrator);
        return query.getResultList();
    }

    //RENAME TO allLoginsThatAreNotAdmin
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Login> allLoginsThatAreNotAdmin() {
        TypedQuery<Login> query;
        query = em.createQuery("SELECT l FROM Login l WHERE l.accountType <> :administrator", Login.class);
        query.setParameter("administrator", AccountType.Administrator);
        return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public boolean userExists(String username) {
        TypedQuery<Login> query = em.createQuery("SELECT l FROM Login l WHERE l.username =:name", Login.class);
        query.setParameter("name", username);
        return query.getResultList().size() == 1;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createInitialLogin(Login login) throws NoSuchAlgorithmException {
        try {
            login.setPassword(EncryptionUtility.hash256(login.getPassword()));
            em.persist(login);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Login getLogin(String username) {
        Login loginToReturn = em.find(Login.class, username);
        return loginToReturn;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateLogin(Login login) {
        List<Detention> managedDet = findAllDetetionsOnUsername(login.getUsername());
        List<Receipt> managedRec = findAllReceiptsForLogin(login.getUsername());
        login.setDetentions(managedDet);
        login.setReceipts(managedRec);
        em.merge(login);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Receipt> findAllReceiptsForLogin(String username) {
        Login managed = em.find(Login.class, username);
        return managed.getReceipts();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Receipt> findAllReceipts() {
        TypedQuery<Receipt> query;
        query = em.createQuery("SELECT r FROM Receipt r ", Receipt.class);
        return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Object> findCountOfStudentsExistingDetentionsOnUsername(String username) {
        Query query;
        query = em.createQuery("SELECT COUNT(d.detentionID) as total, d.firstName, d.lastName, d.yearType FROM Detention d WHERE d.login.username = :user GROUP BY d.lastName, d.firstName, d.yearType ORDER BY total DESC", Detention.class);
        query.setParameter("user", username);
        List result = query.getResultList();
        return result;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateReceipt(Receipt currentReceipt) {
        em.merge(currentReceipt);
    }

    // this will probs be deleted.
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createReceipt(Receipt receipt) {
        em.persist(receipt);
    }

    public void addReceiptToLogin(Receipt receipt, Login login) {
        try {
            //detention has it's login set and created.
            receipt.setLogin(login);
            createReceipt(receipt);
        // next we gonna add it to our login.
        // set the list of detentions 
            //also this is where we could restrict.
            login.getReceipts().add(receipt);
        // NO FORGETTING THE CLOSE ALSO TRANSACTIONS
            //em.close();
            em.merge(login);
        } catch (EJBException e) {
            System.out.println(e);
        }
    }
}
