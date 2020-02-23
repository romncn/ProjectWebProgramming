package worldwine.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import worldwine.jpa.model.Orderlist;
import worldwine.jpa.model.Product;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-20T02:06:50")
@StaticMetamodel(Orderdetail.class)
public class Orderdetail_ { 

    public static volatile SingularAttribute<Orderdetail, Product> productcode;
    public static volatile SingularAttribute<Orderdetail, Integer> orderdetailid;
    public static volatile SingularAttribute<Orderdetail, Integer> unitsordered;
    public static volatile SingularAttribute<Orderdetail, Orderlist> orderid;
    public static volatile SingularAttribute<Orderdetail, Double> priceeach;

}