package worldwine.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import worldwine.jpa.model.Orderlist;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-20T02:06:50")
@StaticMetamodel(Accountcustomer.class)
public class Accountcustomer_ { 

    public static volatile SingularAttribute<Accountcustomer, String> firstname;
    public static volatile SingularAttribute<Accountcustomer, String> password;
    public static volatile SingularAttribute<Accountcustomer, Integer> customerid;
    public static volatile SingularAttribute<Accountcustomer, String> email;
    public static volatile SingularAttribute<Accountcustomer, String> lastname;
    public static volatile SingularAttribute<Accountcustomer, String> username;
    public static volatile ListAttribute<Accountcustomer, Orderlist> orderlistList;

}