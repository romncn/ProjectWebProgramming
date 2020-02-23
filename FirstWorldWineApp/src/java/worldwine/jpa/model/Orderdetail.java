/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldwine.jpa.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Chutikan
 */
@Entity
@Table(name = "ORDERDETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderdetail.findAll", query = "SELECT o FROM Orderdetail o")
    , @NamedQuery(name = "Orderdetail.findByOrderdetailid", query = "SELECT o FROM Orderdetail o WHERE o.orderdetailid = :orderdetailid")
    , @NamedQuery(name = "Orderdetail.findByUnitsordered", query = "SELECT o FROM Orderdetail o WHERE o.unitsordered = :unitsordered")
    , @NamedQuery(name = "Orderdetail.findByPriceeach", query = "SELECT o FROM Orderdetail o WHERE o.priceeach = :priceeach")
    ,@NamedQuery(name = "Orderdetail.findByOrderId", query = "SELECT o FROM Orderdetail o WHERE o.orderid = :orderid")
})
public class Orderdetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ORDERDETAILID")
    private Integer orderdetailid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UNITSORDERED")
    private int unitsordered;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICEEACH")
    private double priceeach;
    @JoinColumn(name = "ORDERID", referencedColumnName = "ORDERID")
    @ManyToOne
    private Orderlist orderid;
    @JoinColumn(name = "PRODUCTCODE", referencedColumnName = "PRODUCTCODE")
    @ManyToOne
    private Product productcode;

    public Orderdetail() {
    }

    public Orderdetail(Integer orderdetailid) {
        this.orderdetailid = orderdetailid;
    }

    public Orderdetail(Integer orderdetailid, int unitsordered, double priceeach) {
        this.orderdetailid = orderdetailid;
        this.unitsordered = unitsordered;
        this.priceeach = priceeach;
    }

    public Integer getOrderdetailid() {
        return orderdetailid;
    }

    public void setOrderdetailid(Integer orderdetailid) {
        this.orderdetailid = orderdetailid;
    }

    public int getUnitsordered() {
        return unitsordered;
    }

    public void setUnitsordered(int unitsordered) {
        this.unitsordered = unitsordered; 
    }

    public double getPriceeach() {
        return priceeach;
    }

    public void setPriceeach(double priceeach) {
        this.priceeach = priceeach;
    }

    public Orderlist getOrderid() {
        return orderid;
    }

    public void setOrderid(Orderlist orderid) {
        this.orderid = orderid;
    }

    public Product getProductcode() {
        return productcode;
    }

    public void setProductcode(Product productcode) {
        this.productcode = productcode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderdetailid != null ? orderdetailid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderdetail)) {
            return false;
        }
        Orderdetail other = (Orderdetail)                                                                                             object;
        if ((this.orderdetailid == null && other.orderdetailid != null) || (this.orderdetailid != null && !this.orderdetailid.equals(other.orderdetailid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "worldwine.jpa.model.Orderdetail[ orderdetailid=" + orderdetailid + " ]";
    }
    
}
