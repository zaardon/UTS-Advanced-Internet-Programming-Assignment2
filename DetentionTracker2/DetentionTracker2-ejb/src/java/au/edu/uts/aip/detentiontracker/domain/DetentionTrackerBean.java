package au.edu.uts.aip.detentiontracker.domain;

/**
 * This is the Detention Tracker Bean that is used throughout the project.
 */
import au.edu.uts.aip.detentiontracker.domain.enums.AccountType;
import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Stateless
public class DetentionTrackerBean {

    @PersistenceContext
    private EntityManager em;

    /**
     * Returns a list of ALL the detentions belong to a username
     *
     * @param username the username that is to be searched
     * @return a list of detentions
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Detention> findAllDetetionsOnUsername(String username) {
        Login managed = em.find(Login.class, username);
        return managed.getDetentions();
    }

    /**
     * Returns a detention based on a provided detention ID
     *
     * @param detentionID to be used in the search
     * @return a detention
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Detention findDetentionOnID(int detentionID) {
        return em.find(Detention.class, detentionID);
    }

    /**
     * Updates a detention with the values of a new detention object
     *
     * @param currentDetention the new detention
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateDetention(Detention currentDetention) {
        em.merge(currentDetention);
    }

    /**
     * Deletes a detention with the provided detention ID
     *
     * @param detentionID the ID for the detention to be deleted
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteDetention(int detentionID) {
        Detention detention = em.find(Detention.class, detentionID);
        em.remove(detention);
    }

    /**
     * Creates a detention based on a provided detention object
     *
     * @param detention the detention that is to be created
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createDetention(Detention detention) {
        em.persist(detention);
    }

    /**
     * This maps a detention object to a login object.
     *
     * @param detention the user's detention
     * @param login the user's login object
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addDetentionToLogin(Detention detention, Login login) {
            detention.setLogin(login);
            createDetention(detention);
            login.getDetentions().add(detention);
            em.merge(login);
    }

    /**
     * Deletes a detention object from a login object
     *
     * @param detentionID of the detention to be deleted
     * @param username of the login object to be deleted
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteDetentionFromLogin(int detentionID, String username) {
        Detention detManaged = em.find(Detention.class, detentionID);
        Login managed = em.find(Login.class, username);
        managed.getDetentions().remove(detManaged);
        em.remove(detManaged);
    }

    /**
     * Returns a list of logins that currently subscribe to paying account
     * types. Does not include users who have invalid customer tokens
     *
     * @return a list of paying customers
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Login> allLoginsByPayingAccountType() {
        TypedQuery<Login> query;
        query = em.createQuery("SELECT l FROM Login l WHERE l.accountType <> :free AND l.accountType <> :administrator AND l.token is not NULL ", Login.class);
        query.setParameter("free", AccountType.Free);
        query.setParameter("administrator", AccountType.Administrator);
        return query.getResultList();
    }

    /**
     * Provides a list of all accounts that are no administrators
     *
     * @return a list of accounts
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Login> allLoginsThatAreNotAdmin() {
        TypedQuery<Login> query;
        query = em.createQuery("SELECT l FROM Login l WHERE l.accountType <> :administrator", Login.class);
        query.setParameter("administrator", AccountType.Administrator);
        return query.getResultList();
    }

    /**
     * Checks if a user exists with a given username
     *
     * @param username to be checked
     * @return true or false
     */
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public boolean userExists(String username) {
        TypedQuery<Login> query = em.createQuery("SELECT l FROM Login l WHERE l.username =:name", Login.class);
        query.setParameter("name", username);
        return query.getResultList().size() == 1;
    }

    /**
     * Creates the initial login object with a password (that uses encryption)
     *
     * @param login is the user object
     * @throws NoSuchAlgorithmException when the encryption fails
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createInitialLogin(Login login) throws NoSuchAlgorithmException {
        try {
            login.setPassword(EncryptionUtility.hash256(login.getPassword()));
            em.persist(login);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
    }

    /**
     * Returns a login object based on a provided username
     *
     * @param username of the login to be searched
     * @return a login
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Login getLogin(String username) {
        Login loginToReturn = em.find(Login.class, username);
        return loginToReturn;
    }

    /**
     * Updates a login object with a new provided object
     *
     * @param login is the new login object
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateLogin(Login login) {
        List<Detention> managedDet = findAllDetetionsOnUsername(login.getUsername());
        List<Receipt> managedRec = findAllReceiptsForLogin(login.getUsername());
        login.setDetentions(managedDet);
        login.setReceipts(managedRec);
        em.merge(login);
    }

    /**
     * Provides a list of receipts for a given username
     *
     * @param username of the receipts
     * @return a list of receipts
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Receipt> findAllReceiptsForLogin(String username) {
        Login managed = em.find(Login.class, username);
        return managed.getReceipts();
    }

    /**
     * Returns all the receipts in the system
     *
     * @return a list of receipts
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Receipt> findAllReceipts() {
        TypedQuery<Receipt> query;
        query = em.createQuery("SELECT r FROM Receipt r ", Receipt.class);
        return query.getResultList();
    }

    /**
     * Returns a list of generic objects, that represent a student's name and
     * the COUNT of detentions they currently have
     *
     * @param username belonging to the customer
     * @return a list of objects representing students
     */
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Object> findCountOfStudentsExistingDetentionsOnUsername(String username) {
        Query query;
        query = em.createQuery("SELECT COUNT(d.detentionID) as total, d.firstName, d.lastName, d.yearType "
                + "FROM Detention d "
                + "WHERE d.login.username = :user "
                + "GROUP BY d.lastName, d.firstName, d.yearType "
                + "ORDER BY total DESC", Detention.class);
        query.setParameter("user", username);
        List result = query.getResultList();
        return result;
    }

    /**
     * Updates a receipt object with a given new receipt object
     *
     * @param currentReceipt the new receipt object
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateReceipt(Receipt currentReceipt) {
        em.merge(currentReceipt);
    }

    /**
     * Creates a receipt with a given receipt object
     *
     * @param receipt a new receipt
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createReceipt(Receipt receipt) {
        em.persist(receipt);
    }

    /**
     * Maps a receipt object to a given login object
     *
     * @param receipt belonging to the login object
     * @param login that requires a receipt
     */
    public void addReceiptToLogin(Receipt receipt, Login login) {
        try {
            receipt.setLogin(login);
            createReceipt(receipt);
            login.getReceipts().add(receipt);
            em.merge(login);
        } catch (EJBException e) {
            System.out.println(e);
        }
    }
}
