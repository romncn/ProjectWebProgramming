package worldwine.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import worldwine.jpa.model.Orderdetail;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-20T02:06:50")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Integer> productcode;
    public static volatile SingularAttribute<Product, Double> price;
    public static volatile SingularAttribute<Product, Integer> vintage;
    public static volatile ListAttribute<Product, Orderdetail> orderdetailList;
    public static volatile SingularAttribute<Product, String> productname;
    public static volatile SingularAttribute<Product, String> grape;
    public static volatile SingularAttribute<Product, String> type;
    public static volatile SingularAttribute<Product, Integer> capacity;

}