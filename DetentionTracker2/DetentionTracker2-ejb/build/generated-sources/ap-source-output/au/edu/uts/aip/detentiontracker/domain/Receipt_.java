package au.edu.uts.aip.detentiontracker.domain;

import au.edu.uts.aip.detentiontracker.domain.Login;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-18T16:19:06")
@StaticMetamodel(Receipt.class)
public class Receipt_ { 

    public static volatile SingularAttribute<Receipt, Integer> amount;
    public static volatile SingularAttribute<Receipt, Date> dateOfPurchase;
    public static volatile SingularAttribute<Receipt, String> description;
    public static volatile SingularAttribute<Receipt, Login> login;
    public static volatile SingularAttribute<Receipt, Integer> receiptID;
    public static volatile SingularAttribute<Receipt, String> token;

}