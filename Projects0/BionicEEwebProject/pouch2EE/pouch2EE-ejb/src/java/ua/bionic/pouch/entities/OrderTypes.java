/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.bionic.pouch.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

/**
 *
 * @author romanrudenko
 */
@Entity
@Table(name = "order_types")
@NamedQueries({
    @NamedQuery(name = "OrderTypes.findAll", query = "SELECT o FROM OrderTypes o"),
    @NamedQuery(name = "OrderTypes.findById", query = "SELECT o FROM OrderTypes o WHERE o.id = :id"),
    @NamedQuery(name = "OrderTypes.findByType", query = "SELECT o FROM OrderTypes o WHERE o.type = :type")})
public class OrderTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderTypeId")
    private Collection<Orders> ordersCollection;

    public OrderTypes() {
    }

    public OrderTypes(Integer id) {
        this.id = id;
    }

    public OrderTypes(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderTypes that = (OrderTypes) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ordersCollection != null ? !ordersCollection.equals(that.ordersCollection) : that.ordersCollection != null)
            return false;
        if (!type.equals(that.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return type;
    }

    
    
}
