/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;

/**
 *
 * @author romanrudenko
 */
@Entity
@Table(name = "orders")
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findById", query = "SELECT o FROM Orders o WHERE o.id = :id"),
    @NamedQuery(name = "Orders.findByDate", query = "SELECT o FROM Orders o WHERE o.date = :date"),
    @NamedQuery(name = "Orders.findByAmount", query = "SELECT o FROM Orders o WHERE o.amount = :amount"),
    @NamedQuery(name = "Orders.findByConfirmed", query = "SELECT o FROM Orders o WHERE o.confirmed = :confirmed"),
    @NamedQuery(name = "Orders.findByConfirmedAndAccountId", query = "SELECT o FROM Orders o WHERE o.confirmed = :confirmed AND o.accountId = :accountId"),
    @NamedQuery(name = "Orders.findByAccountId", query = "SELECT o FROM Orders o WHERE o.accountId = :accountId")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "confirmed")
    private boolean confirmed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<Transactions> transactionsCollection;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users userId;
    @JoinColumn(name = "order_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private OrderTypes orderTypeId;
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Accounts accountId;

    public Orders() {
    }

    public Orders(Integer id) {
        this.id = id;
    }

    public Orders(Integer id, Date date, BigDecimal amount, boolean confirmed) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.confirmed = confirmed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Collection<Transactions> getTransactionsCollection() {
        return transactionsCollection;
    }

    public void setTransactionsCollection(Collection<Transactions> transactionsCollection) {
        this.transactionsCollection = transactionsCollection;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public OrderTypes getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(OrderTypes orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public Accounts getAccountId() {
        return accountId;
    }

    public void setAccountId(Accounts accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Orders orders = (Orders) o;

        if (confirmed != orders.confirmed) {
            return false;
        }
        if (accountId != null ? !accountId.equals(orders.accountId) : orders.accountId != null) {
            return false;
        }
        if (!amount.equals(orders.amount)) {
            return false;
        }
        if (date != null ? !date.equals(orders.date) : orders.date != null) {
            return false;
        }
        if (id != null ? !id.equals(orders.id) : orders.id != null) {
            return false;
        }
        if (orderTypeId != null ? !orderTypeId.equals(orders.orderTypeId) : orders.orderTypeId != null) {
            return false;
        }
        if (transactionsCollection != null ? !transactionsCollection.equals(orders.transactionsCollection) : orders.transactionsCollection != null) {
            return false;
        }
        if (userId != null ? !userId.equals(orders.userId) : orders.userId != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + amount.hashCode();
        result = 31 * result + (confirmed ? 1 : 0);

        return result;
    }

    @Override
    public String toString() {
        return "Order #" + id + ", Type " + orderTypeId + ", Amount " + amount;
    }

}
