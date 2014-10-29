package au.edu.uts.aip.detentiontracker.domain;

import au.edu.uts.aip.detentiontracker.domain.AccountType;
import au.edu.uts.aip.detentiontracker.domain.Detention;
import au.edu.uts.aip.detentiontracker.domain.Receipt;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-10-29T20:14:28")
@StaticMetamodel(Login.class)
public class Login_ { 

    public static volatile ListAttribute<Login, Receipt> receipts;
    public static volatile SingularAttribute<Login, String> password;
    public static volatile ListAttribute<Login, Detention> detentions;
    public static volatile SingularAttribute<Login, AccountType> accountType;
    public static volatile SingularAttribute<Login, String> email;
    public static volatile SingularAttribute<Login, String> username;
    public static volatile SingularAttribute<Login, String> token;

}