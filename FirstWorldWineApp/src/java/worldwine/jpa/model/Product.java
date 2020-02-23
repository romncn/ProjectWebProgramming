/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldwine.jpa.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Chutikan
 */
@Entity
@Table(name = "PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findByProductcode", query = "SELECT p FROM Product p WHERE p.productcode = :productcode")
    , @NamedQuery(name = "Product.findByProductname", query = "SELECT p FROM Product p WHERE p.productname = :productname")
    , @NamedQuery(name = "Product.findByType", query = "SELECT p FROM Product p WHERE p.type = :type")
    , @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price")
    , @NamedQuery(name = "Product.findByGrape", query = "SELECT p FROM Product p WHERE p.grape = :grape")
    , @NamedQuery(name = "Product.findByVintage", query = "SELECT p FROM Product p WHERE p.vintage = :vintage")
    , @NamedQuery(name = "Product.findByCapacity", query = "SELECT p FROM Product p WHERE p.capacity = :capacity")
        , @NamedQuery(name = "Product.findByAllnonYear", query = "SELECT p FROM Product p WHERE lower(p.productname) LIKE lower(:productname)"
            + "AND  lower(p.type) LIKE lower(:type)"
            + "AND p.price >= :start AND p.price <= :end")

    , @NamedQuery(name = "Product.findByProductAll", query = "SELECT p FROM Product p WHERE lower(p.productname) LIKE lower(:productname)"
            + "AND lower(p.type) LIKE lower(:type)"
            + "AND p.vintage = :vintage "
            + "AND p.price >= :start AND p.price <= :end")

    , @NamedQuery(name = "Product.findByAllnonYearPrice", query = "SELECT p FROM Product p WHERE lower(p.productname) LIKE lower(:productname)"
            + "AND lower(p.type) LIKE lower(:type)")

    , @NamedQuery(name = "Product.findByAllnonPrice", query = "SELECT p FROM Product p WHERE lower(p.productname) LIKE lower(:productname)"
            + "AND lower(p.type) LIKE lower(:type)"
            + "AND  p.vintage = :vintage ")
//, @NamedQuery(name = "Product.findByAll", query = "SELECT p FROM Product p WHERE lower(p.productname) LIKE lower(:productname)"
//            + "AND lower(p.type) LIKE lower(:type) ")
//            + "AND lower(p.vintage) = lower(:vintage) "
//            + "AND lower(p.capacity) = lower(:capacity) ")
})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRODUCTCODE")
    private Integer productcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "PRODUCTNAME")
    private String productname;
    @Size(max = 15)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private double price;
    @Size(max = 40)
    @Column(name = "GRAPE")
    private String grape;
    @Column(name = "VINTAGE")
    private Integer vintage;
    @Column(name = "CAPACITY")
    private Integer capacity;
    @OneToMany(mappedBy = "productcode")
    private List<Orderdetail> orderdetailList;

    public Product() {
    }

    public Product(Integer productcode) {
        this.productcode = productcode;
    }

    public Product(Integer productcode, String productname, double price) {
        this.productcode = productcode;
        this.productname = productname;
        this.price = price;
    }

    public Integer getProductcode() {
        return productcode;
    }

    public void setProductcode(Integer productcode) {
        this.productcode = productcode;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGrape() {
        return grape;
    }

    public void setGrape(String grape) {
        this.grape = grape;
    }

    public Integer getVintage() {
        return vintage;
    }

    public void setVintage(Integer vintage) {
        this.vintage = vintage;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @XmlTransient
    public List<Orderdetail> getOrderdetailList() {
        return orderdetailList;
    }

    public void setOrderdetailList(List<Orderdetail> orderdetailList) {
        this.orderdetailList = orderdetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productcode != null ? productcode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productcode == null && other.productcode != null) || (this.productcode != null && !this.productcode.equals(other.productcode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "worldwine.jpa.model.Product[ productcode=" + productcode + " ]";
    }
    
}
