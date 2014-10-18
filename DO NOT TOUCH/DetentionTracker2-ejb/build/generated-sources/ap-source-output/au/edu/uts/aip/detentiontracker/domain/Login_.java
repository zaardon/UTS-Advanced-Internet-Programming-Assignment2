package au.edu.uts.aip.detentiontracker.domain;

import au.edu.uts.aip.detentiontracker.domain.AccountType;
import au.edu.uts.aip.detentiontracker.domain.Detention;
import au.edu.uts.aip.detentiontracker.domain.Receipt;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-18T16:44:14")
@StaticMetamodel(Login.class)
public class Login_ { 

    public static volatile ListAttribute<Login, Receipt> receipts;
    public static volatile SingularAttribute<Login, String> password;
    public static volatile ListAttribute<Login, Detention> detentions;
    public static volatile SingularAttribute<Login, AccountType> accountType;
    public static volatile SingularAttribute<Login, String> username;

}