/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.pouch.session.beans.transactions;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import ua.bionic.pouch.entities.Accounts;
import ua.bionic.pouch.entities.Orders;
import ua.bionic.pouch.entities.Transactions;
import ua.bionic.pouch.session.beans.AbstractFacade;

/**
 *
 * @author romanrudenko
 */
@Stateless
public class TransactionsFacade extends AbstractFacade<Transactions> implements TransactionsFacadeLocal {

    @PersistenceContext(unitName = "pouch2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransactionsFacade() {
        super(Transactions.class);
    }

    @Override
    public List<Transactions> findByAccountId(Accounts accountId) {
        TypedQuery<Transactions> query = em.createNamedQuery("Transactions.findByAccountId", Transactions.class);
        query.setParameter("accountId", accountId);
        List<Transactions> transactions = null;
        transactions = query.getResultList();
        return transactions;
    }
}
