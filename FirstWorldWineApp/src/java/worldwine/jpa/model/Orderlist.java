/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldwine.jpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import worldwine.model.Cart;

/**
 *
 * @author Chutikan
 */
@Entity
@Table(name = "ORDERLIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderlist.findAll", query = "SELECT o FROM Orderlist o")
    , @NamedQuery(name = "Orderlist.findByOrderid", query = "SELECT o FROM Orderlist o WHERE o.orderid = :orderid")
    , @NamedQuery(name = "Orderlist.findByOrderdate", query = "SELECT o FROM Orderlist o WHERE o.orderdate = :orderdate")})
public class Orderlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ORDERID")
    private Integer orderid;
    @Column(name = "ORDERDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderdate;
    @JoinColumn(name = "CUSTOMERID", referencedColumnName = "CUSTOMERID")
    @ManyToOne
    private Accountcustomer customerid;
    @OneToMany(mappedBy = "orderid")
    private List<Orderdetail> orderdetailList;

    public Orderlist(Integer orderid, Date orderdate, Accountcustomer customerid) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.customerid = customerid;
    }

    public Orderlist(Date orderdate, Accountcustomer customerid) {
        this.orderdate = orderdate;
        this.customerid = customerid;
    }
    

    public Orderlist(Integer orderid, Date orderdate, Accountcustomer customerid, List<Orderdetail> orderdetailList) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.customerid = customerid;
        this.orderdetailList = orderdetailList;
    }

    public Orderlist(Accountcustomer customerid, List<Orderdetail> orderdetailList) {
        this.orderdate = new Date();
        this.customerid = customerid;
        this.orderdetailList = orderdetailList;
    }

    public Orderlist(Date orderdate, Accountcustomer customerid, List<Orderdetail> orderdetailList) {
        this.orderdate = orderdate;
        this.customerid = customerid;
        this.orderdetailList = orderdetailList;
    }
    

    public Orderlist() {
    }

    public Orderlist(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Accountcustomer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Accountcustomer customerid) {
        this.customerid = customerid;
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
        hash += (orderid != null ? orderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderlist)) {
            return false;
        }
        Orderlist other = (Orderlist) object;
        if ((this.orderid == null && other.orderid != null) || (this.orderid != null && !this.orderid.equals(other.orderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "worldwine.jpa.model.Orderlist[ orderid=" + orderid + " ]";
    }
    
}
