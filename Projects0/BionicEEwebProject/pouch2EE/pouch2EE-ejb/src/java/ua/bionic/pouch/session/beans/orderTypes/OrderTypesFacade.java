/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.session.beans.orderTypes;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import ua.bionic.pouch.entities.Accounts;
import ua.bionic.pouch.entities.OrderTypes;
import ua.bionic.pouch.entities.Orders;
import ua.bionic.pouch.session.beans.AbstractFacade;

/**
 *
 * @author romanrudenko
 */
@Stateless
public class OrderTypesFacade extends AbstractFacade<OrderTypes> implements OrderTypesFacadeLocal {

    @PersistenceContext(unitName = "pouch2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderTypesFacade() {
        super(OrderTypes.class);
    }

    @Override
    public OrderTypes findByType(String type) {
        TypedQuery<OrderTypes> query = em.createNamedQuery("OrderTypes.findByType", OrderTypes.class);
        query.setParameter("type", type);
        OrderTypes orderType = query.getSingleResult();
        return orderType;
    }


}
