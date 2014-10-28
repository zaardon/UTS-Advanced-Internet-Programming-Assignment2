package au.edu.uts.aip.detentiontracker.domain;

import au.edu.uts.aip.detentiontracker.domain.DepartmentType;
import au.edu.uts.aip.detentiontracker.domain.DetentionType;
import au.edu.uts.aip.detentiontracker.domain.Login;
import au.edu.uts.aip.detentiontracker.domain.YearType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-10-29T08:33:16")
@StaticMetamodel(Detention.class)
public class Detention_ { 

    public static volatile SingularAttribute<Detention, String> reason;
    public static volatile SingularAttribute<Detention, String> lastName;
    public static volatile SingularAttribute<Detention, String> firstName;
    public static volatile SingularAttribute<Detention, DetentionType> detentionType;
    public static volatile SingularAttribute<Detention, Integer> detentionID;
    public static volatile SingularAttribute<Detention, Login> login;
    public static volatile SingularAttribute<Detention, DepartmentType> departmentType;
    public static volatile SingularAttribute<Detention, YearType> yearType;

}