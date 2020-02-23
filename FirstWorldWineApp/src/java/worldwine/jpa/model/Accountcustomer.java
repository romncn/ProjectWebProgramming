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
@Table(name = "ACCOUNTCUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accountcustomer.findAll", query = "SELECT a FROM Accountcustomer a")
    , @NamedQuery(name = "Accountcustomer.findByCustomerid", query = "SELECT a FROM Accountcustomer a WHERE a.customerid = :customerid")
    , @NamedQuery(name = "Accountcustomer.findByFirstname", query = "SELECT a FROM Accountcustomer a WHERE a.firstname = :firstname")
    , @NamedQuery(name = "Accountcustomer.findByLastname", query = "SELECT a FROM Accountcustomer a WHERE a.lastname = :lastname")
    , @NamedQuery(name = "Accountcustomer.findByUsername", query = "SELECT a FROM Accountcustomer a WHERE a.username = :username")
    , @NamedQuery(name = "Accountcustomer.findByPassword", query = "SELECT a FROM Accountcustomer a WHERE a.password = :password")
    , @NamedQuery(name = "Accountcustomer.findByEmail", query = "SELECT a FROM Accountcustomer a WHERE a.email = :email")})
public class Accountcustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CUSTOMERID")
    private Integer customerid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Size(max = 100)
    @Column(name = "LASTNAME")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PASSWORD")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;
    @OneToMany(mappedBy = "customerid")
    private List<Orderlist> orderlistList;

    public Accountcustomer() {
    }

    public Accountcustomer(Integer customerid) {
        this.customerid = customerid;
    }

    public Accountcustomer(Integer customerid, String firstname, String lastname, String username, String password, String email) {
        this.customerid = customerid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Accountcustomer(String firstname, String lastname, String username, String password, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<Orderlist> getOrderlistList() {
        return orderlistList;
    }

    public void setOrderlistList(List<Orderlist> orderlistList) {
        this.orderlistList = orderlistList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerid != null ? customerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accountcustomer)) {
            return false;
        }
        Accountcustomer other = (Accountcustomer) object;
        if ((this.customerid == null && other.customerid != null) || (this.customerid != null && !this.customerid.equals(other.customerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "worldwine.jpa.model.Accountcustomer[ customerid=" + customerid + " ]";
    }
    
}
