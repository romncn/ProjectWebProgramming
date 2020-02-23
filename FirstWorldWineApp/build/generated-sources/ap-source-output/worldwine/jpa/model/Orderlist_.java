package worldwine.jpa.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import worldwine.jpa.model.Accountcustomer;
import worldwine.jpa.model.Orderdetail;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-20T02:06:50")
@StaticMetamodel(Orderlist.class)
public class Orderlist_ { 

    public static volatile SingularAttribute<Orderlist, Integer> orderid;
    public static volatile ListAttribute<Orderlist, Orderdetail> orderdetailList;
    public static volatile SingularAttribute<Orderlist, Accountcustomer> customerid;
    public static volatile SingularAttribute<Orderlist, Date> orderdate;

}