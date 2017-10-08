/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.session.beans.orders;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import ua.bionic.pouch.entities.Accounts;
import ua.bionic.pouch.entities.Orders;
import ua.bionic.pouch.session.beans.AbstractFacade;

/**
 *
 * @author romanrudenko
 */
@Stateless
public class OrdersFacade extends AbstractFacade<Orders> implements OrdersFacadeLocal {

    @PersistenceContext(unitName = "pouch2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }

    @Override
    public List<Orders> findByAccountId(Accounts accountId) {
        TypedQuery<Orders> query = em.createNamedQuery("Orders.findByAccountId", Orders.class);
        query.setParameter("accountId", accountId);
        List<Orders> orders = null;
        orders = query.getResultList();
        return orders;
    }

    @Override
        public List<Orders> findByAccountIdAndConfirmed(Accounts accountId, boolean confirmed) {
        TypedQuery<Orders> query = em.createNamedQuery("Orders.findByConfirmedAndAccountId", Orders.class); 
        query.setParameter("confirmed", confirmed);
        query.setParameter("accountId", accountId);
        List<Orders> orders = null;
        orders = query.getResultList();
        return orders;
    }

}
