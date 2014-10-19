package au.edu.uts.aip.detentiontracker.domain;

import au.edu.uts.aip.detentiontracker.domain.AccountType;
import au.edu.uts.aip.detentiontracker.domain.Detention;
import au.edu.uts.aip.detentiontracker.domain.Receipt;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-19T22:01:15")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-19T03:12:58")
>>>>>>> origin/master
@StaticMetamodel(Login.class)
public class Login_ { 

    public static volatile ListAttribute<Login, Receipt> receipts;
    public static volatile SingularAttribute<Login, String> password;
    public static volatile ListAttribute<Login, Detention> detentions;
    public static volatile SingularAttribute<Login, AccountType> accountType;
    public static volatile SingularAttribute<Login, String> username;

}